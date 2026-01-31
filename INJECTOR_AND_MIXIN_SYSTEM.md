# VitaLite Injector and Mixin System

## Overview

The **Injector and Mixin System** is VitaLite's core bytecode transformation framework that modifies Java classes at runtime. It enables VitaLite to extend and customize both the OSRS GamePack and RuneLite client without modifying their original source code.

### What is it?

A **mixin** is a design pattern where you write transformation instructions as annotated Java classes. The **Injector** reads these mixin classes and uses ASM (Java bytecode manipulation library) to inject new methods, fields, and behavior into target classes at runtime.

Think of it as **compile-time code generation meets runtime patching** - you write what looks like normal Java code with special annotations, and VitaLite automatically weaves it into the target classes before they're loaded.

## Architecture

VitaLite has two parallel injection systems:

### 1. GamePack Injector (`Injector.java`)
- **Target**: OSRS GamePack (obfuscated game client classes)
- **Mixins Location**: `src/main/java/com/tonic/mixins/` (27 files)
- **Purpose**: Add custom functionality to game classes like Client, MouseHandler, etc.

### 2. RuneLite Injector (`RLInjector.java`)
- **Target**: RuneLite client classes
- **Mixins Location**: `src/main/java/com/tonic/rlmixins/` (15 files)
- **Purpose**: Customize RuneLite behavior (plugin loading, UI changes, etc.)

## How It Works

### Phase 1: Development Mode (With `-runInjector` Flag)

When you run VitaLite with the `-runInjector` command-line flag:

```
./gradlew run --args="-runInjector"
```

**What happens:**

1. **Load Classes**: All GamePack and RuneLite classes are loaded into memory as ASM ClassNode objects
2. **Scan Mixins**: The injector finds all mixin classes (marked with `@Mixin` annotation)
3. **Apply Transformations**: For each mixin, the injector:
   - Locates the target class using mappings.json
   - Injects new methods/fields marked with `@Inject`
   - Wraps existing methods with hooks (`@MethodHook`)
   - Replaces methods entirely (`@Replace`)
   - Creates field access hooks (`@FieldHook`)
4. **Generate Patches**: Compares original vs modified bytecode and creates binary diffs
5. **Save Patches**: Writes `patches.zip` to `src/main/resources/com/tonic/`

### Phase 2: Production Mode (Default)

When you run VitaLite normally (without `-runInjector`):

1. **Skip Full Injection**: The expensive ASM transformation is skipped
2. **Apply Pre-Generated Patches**: Loads `patches.zip` and applies the binary diffs
3. **Much Faster Startup**: Reduces startup time from ~20 seconds to ~2 seconds

**This is why the injector is "bloat" for production** - once patches are generated, the full ASM pipeline is unnecessary.

## Mixin Annotations

### Core Annotations

#### `@Mixin(value = "ClassName")`
Marks a class as a mixin targeting a specific class.

```java
@Mixin("Client")
public abstract class TClientMixin implements TClient {
    // Mixin code here
}
```

#### `@Inject`
Injects a new method or field into the target class.

```java
@Inject
@Override
public int getShipHeading() {
    return shipHeading;
}
```

#### `@Shadow(value = "fieldName")`
Creates a reference to an existing field/method in the target class without modifying it.

```java
@Shadow("packetWriter")
private static TPacketWriter packetWriter;

@Shadow("getPacketBufferNode")
public abstract TPacketBufferNode getPacketBufferNode(...);
```

#### `@FieldHook(value = "fieldName")`
Hooks field access operations to intercept reads/writes with conditional logic.

```java
@FieldHook("MouseHandler_idleCycles")
public static boolean onIdleCycleSet(int value) {
    // Return false to prevent the field assignment
    return !Static.getVitaConfig().shouldNeverLog();
}
```

#### `@MethodHook(value = "methodName")`
Injects code at the beginning of a target method.

```java
@MethodHook("updatePlayer")
public void onPlayerUpdate() {
    // Called before original updatePlayer runs
}
```

#### `@Replace(value = "methodName")`
Completely replaces a target method's implementation.

```java
@Replace("checkMouseIdle")
public static boolean checkMouseIdle() {
    // Completely overrides original logic
    return CustomMouseHandler.checkIdle();
}
```

#### `@Insert`
Advanced annotation for precise bytecode injection at specific points.

```java
@Insert(
    method = "loadCorePlugins",
    at = @At(value = AtTarget.STORE, local = 2),
    ordinal = -1,
    raw = true
)
public static void loadCorePlugins(MethodNode method, AbstractInsnNode insertionPoint) {
    // Direct bytecode manipulation
}
```

#### `@Construct(value = "ClassName")`
Creates constructor wrappers for instantiating obfuscated classes.

```java
@Construct("ClientPacket")
public abstract TClientPacket newClientPacket(int id, int length);
```

#### `@Disable(value = "methodName")`
Disables a method by making it immediately return.

#### `@MethodOverride(value = "methodName")`
Overrides a method while preserving original signature.

## Example: TClientMixin

Let's analyze a real mixin to see how it works:

```java
@Mixin("Client")
public abstract class TClientMixin implements TClient
{
    // Shadow creates a reference to the real field in the Client class
    @Shadow("packetWriter")
    private static TPacketWriter packetWriter;

    @Shadow("heading")
    private static int shipHeading;

    // Inject adds a NEW method to the Client class
    @Inject
    @Override
    public int getShipHeading() {
        return shipHeading;  // Accesses the shadowed field
    }

    @Inject
    @Override
    public void setShipHeading(int heading) {
        shipHeading = heading;  // Modifies the shadowed field
    }

    // Construct creates a wrapper for creating obfuscated ClientPacket objects
    @Override
    @Construct("ClientPacket")
    public abstract TClientPacket newClientPacket(int id, int length);

    // FieldHook intercepts field assignments
    @FieldHook("MouseHandler_idleCycles")
    public static boolean onIdleCycleSet(int value) {
        // Return false to block the assignment (prevent logout)
        return !Static.getVitaConfig().shouldNeverLog();
    }
}
```

**What this does:**

1. **Adds API methods** to the obfuscated Client class for reading/writing ship heading
2. **Creates a constructor wrapper** for ClientPacket (obfuscated class)
3. **Hooks idle cycle assignment** to prevent auto-logout

## Example: PluginManagerMixin (RuneLite)

```java
@Mixin("net/runelite/client/plugins/PluginManager")
public class PluginManagerMixin {
    
    @Insert(
        method = "loadCorePlugins",
        at = @At(value = AtTarget.STORE, local = 2),
        ordinal = -1,
        raw = true
    )
    public static void loadCorePlugins(MethodNode method, AbstractInsnNode insertionPoint) {
        // Direct bytecode manipulation
        InsnList code = BytecodeBuilder.create()
            .invokeStatic("com/tonic/Static", "getInjector", "()Lcom/google/inject/Injector;")
            .pushClass("com/tonic/runelite/Install")
            .invokeInterface("com/google/inject/Injector", "getInstance", ...)
            .build();
        
        method.instructions.insert(insertionPoint, code);
    }
}
```

**What this does:**

- Injects custom plugin loading logic into RuneLite's PluginManager
- Adds VitaLite-specific plugins to the loading list
- Filters out unwanted RuneLite plugins (Discord, Twitch, etc.)

## Technical Details

### Mappings (`mappings.json`)

The OSRS GamePack is heavily obfuscated:
- Class names: `Client` → `client.aq`
- Field names: `packetWriter` → `av`
- Method names: `updatePlayer` → `bc`

VitaLite uses **mappings.json** (94,360 lines, 2.2MB) to translate human-readable names to obfuscated names. This file is generated by RuneLite's deobfuscator.

**Example mapping:**
```json
{
  "name": "Client",
  "obfuscatedName": "client",
  "fields": [
    {
      "name": "packetWriter",
      "obfuscatedName": "av",
      "descriptor": "Lclient$av;"
    }
  ]
}
```

### Patch Generation

The injector generates binary patches using a diffing algorithm:

1. **Store Original**: Keep original bytecode before transformation
2. **Transform**: Apply all mixins via ASM
3. **Diff**: Compare original vs modified bytecode
4. **Compress**: Store only the differences (much smaller than full classes)
5. **Package**: Bundle all diffs into `patches.zip`

**Result:** Instead of shipping 17,000+ modified classes, VitaLite ships a 3.3MB patches.zip file.

### Patch Application (Production)

In production, `PatchApplier.java` replaces the full injection:

```java
public static void applyPatches() {
    // Load patches.zip from resources
    Map<String, byte[]> patches = loadPatchesZip();
    
    // For each class with a patch
    for (String className : patches.keySet()) {
        byte[] original = getOriginalClass(className);
        byte[] patch = patches.get(className);
        
        // Apply binary diff
        byte[] modified = applyBinaryPatch(original, patch);
        
        // Replace in classloader
        replaceClass(className, modified);
    }
}
```

## Transformers (Pipeline)

The injector uses specialized transformers for each annotation:

| Transformer | Purpose |
|-------------|---------|
| `InjectTransformer` | Injects methods/fields into target classes |
| `ShadowTransformer` | Creates references to existing members |
| `MethodHookTransformer` | Wraps methods with pre/post hooks |
| `FieldHookTransformer` | Intercepts field access |
| `ReplaceTransformer` | Replaces entire methods |
| `InsertTransformer` | Inserts bytecode at precise locations |
| `MethodOverrideTransformer` | Overrides methods while preserving signatures |
| `DisableTransformer` | Disables methods |
| `ConstructTransformer` | Creates constructor wrappers |
| `ClassModTransformer` | Modifies class metadata |

Each transformer:
1. Reads the annotation parameters
2. Locates the injection point using ASM
3. Generates the appropriate bytecode
4. Inserts it into the target method/class

## Memory Optimization

The injector is highly optimized to handle 17,000+ classes:

```java
// Pre-sized HashMap saves 20-30MB during resize
public static HashMap<String, ClassNode> gamepack = new HashMap<>(17500, 0.75f);

// Selective frame expansion: Only mixin targets get full frames
boolean needsFrames = mixinTargets.contains(name);
gamepack.put(name, ClassNodeUtil.toNode(bytes, needsFrames));

// Process one-by-one and immediately clear to reduce memory pressure
for (String name : classNames) {
    ClassNode classNode = gamepack.remove(name);
    // ... transform ...
    classNode = null;  // Help GC
}

// Clear string pools
ClassNodeUtil.clearPools();
```

**Optimization Results:**
- Saves 200-300MB RAM by skipping frame expansion on non-target classes
- Reduces peak memory from ~800MB to ~500MB
- Processes classes incrementally instead of keeping all in memory

## When Is This Used?

### Development (Always)

Developers use the injector constantly:

```bash
# Run with injector to update patches after changing mixins
./gradlew run --args="-runInjector"
```

**Use cases:**
- Adding new API methods to game classes
- Hooking game events for plugin access
- Modifying RuneLite behavior
- Debugging bytecode transformations

### Production (Never)

End users never need the injector because:
1. **Pre-Generated Patches**: The release build includes `patches.zip`
2. **Faster Startup**: Patch application is 10x faster than full injection
3. **No Dependencies**: ASM, mappings.json, and mixin classes can be excluded
4. **Smaller Binary**: Removing injector saves 20-30MB

The release build (via `shadowJarRelease` task) excludes:
```kotlin
exclude("com/tonic/injector/**")      // Injector engine
exclude("com/tonic/mixin/**")         // Gamepack mixins
exclude("com/tonic/rlmixin/**")       // RuneLite mixins
exclude("**/mappings.json")           // Obfuscation mappings
```

## Comparison to Other Systems

### Mixin Libraries (Fabric, Sponge, Forge)

VitaLite's mixin system is similar to:
- **Fabric Mixin**: Used by Minecraft modding
- **Sponge Mixin**: Used by Minecraft server plugins
- **ForgeGradle**: Used by Minecraft Forge mods

**Key Differences:**
- VitaLite targets obfuscated code (requires mappings)
- VitaLite generates patches for offline application
- VitaLite has dual injection (GamePack + RuneLite)

### AspectJ (Aspect-Oriented Programming)

VitaLite mixins are similar to AspectJ aspects:
- Both inject code at join points
- Both use annotations to specify transformations
- Both operate on bytecode

**Key Differences:**
- AspectJ uses load-time weaving (javaagent)
- VitaLite uses pre-launch transformation
- VitaLite focuses on game client modification

## Why Is It Complex?

The injector is complex because:

1. **Obfuscation**: OSRS code is heavily obfuscated and changes every update
2. **No Source Access**: Can't modify RuneLite source directly (3rd party)
3. **Runtime Constraints**: Must work with loaded classes (no recompilation)
4. **Performance**: Must transform 17K classes in <20 seconds
5. **Precision**: One bytecode error crashes the entire client

## Benefits

Despite the complexity, the mixin system provides:

### For Developers
- ✅ **Type-Safe**: Write Java, not raw bytecode
- ✅ **Maintainable**: Mixins are easy to read and update
- ✅ **Modular**: Each mixin is independent
- ✅ **Powerful**: Full access to game internals

### For Users
- ✅ **Fast Startup**: Pre-generated patches load quickly
- ✅ **Stable**: Tested transformations are reliable
- ✅ **Small Size**: Binary patches are tiny vs full classes
- ✅ **No Impact**: Patches applied once at startup

## Files and Sizes

| Component | Location | Size | Purpose |
|-----------|----------|------|---------|
| **Injector Engine** | `src/main/java/com/tonic/injector/` | ~50KB source | Transformation engine |
| **GamePack Mixins** | `src/main/java/com/tonic/mixins/` | ~80KB source | OSRS game modifications |
| **RuneLite Mixins** | `src/main/java/com/tonic/rlmixins/` | ~60KB source | RuneLite customizations |
| **Mappings** | `src/main/resources/.../mappings.json` | 2.2MB | Obfuscation mappings |
| **Patches** | `src/main/resources/.../patches.zip` | 3.3MB | Pre-generated diffs |
| **Annotations** | `src/main/java/.../annotations/` | ~15KB source | Mixin annotations |
| **Transformers** | `src/main/java/.../pipeline/` | ~100KB source | Annotation processors |

**Total Bloat (Development Only):** ~5.6MB

## Performance Impact

### Development Mode (`-runInjector`)

```
Startup Time: ~18-25 seconds
Memory Usage: ~500-800MB peak
CPU Usage: 100% (all cores) during injection
```

**Breakdown:**
- Load 17K classes: 3-5s
- Apply mixins: 10-15s
- Generate patches: 2-3s
- Write patches.zip: 1-2s

### Production Mode (Patches)

```
Startup Time: ~2-3 seconds
Memory Usage: ~200-300MB peak
CPU Usage: ~20% during patch application
```

**Breakdown:**
- Load patches.zip: 0.5s
- Apply patches: 1.5-2s
- Load classes: 0.5s

**Speedup:** 8-10x faster startup in production!

## Command-Line Control

From `README.md`:

| Flag | Effect |
|------|--------|
| `-runInjector` | Enable full injection mode (dev only) |
| `-noPlugins` | Skip plugin loading (reduces memory) |
| `-min` | Minimal mode (fewer plugins, faster startup) |

Example:
```bash
# Development: Generate new patches
java -jar VitaLite.jar -runInjector

# Production: Normal startup (uses patches)
java -jar VitaLite.jar

# Lightweight: Minimal memory usage
java -jar VitaLite.jar -min
```

## Summary

The **Injector and Mixin System** is VitaLite's bytecode transformation framework that:

1. **Modifies game and client classes** at runtime without source access
2. **Uses annotated Java classes** (mixins) to specify transformations
3. **Generates binary patches** for production use
4. **Provides type-safe API access** to obfuscated code
5. **Enables powerful customization** of both OSRS and RuneLite

**Key Insight:** The injector is essential for development (creating patches) but unnecessary for production (applying patches). This is why it's considered "bloat" for end users running bot clients - they only need the pre-generated patches, not the transformation machinery.

## See Also

- **BLOAT_ANALYSIS.md** - Details on removing development tools for production
- **README.md** - Command-line flags and build instructions
- **SDK-DOCS.md** - API documentation for plugin developers
