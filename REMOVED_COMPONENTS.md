# Removed Components for Lean Bot Client

This document details all components that have been removed from VitaLite to create a leaner build optimized for running bot clients.

## Removed Components Summary

### 1. Profiler System (43 files removed)
**Location**: `base-api/src/main/java/com/tonic/services/profiler/`

Complete JVM profiling suite including:
- ProfilerWindow.java - Main GUI window with tabs
- ProfilerServer.java - HTTP profiling server  
- MethodProfiler.java - Method-level performance recording
- ResourceMetricsCollector.java - CPU, memory, thread metrics
- SamplingTab.java - Stack trace sampling and flame graphs
- RecordingTab.java - Performance recording and playback
- JIT Compiler access and JVMTI integration
- Memory leak detection (4 files)
- GC analysis (3 files)
- Timeline visualization (9 files)
- Sampling infrastructure (10 files)

**Also removed**:
- `base-api/src/main/java/com/tonic/util/Profiler.java`

### 2. Code Evaluation Plugin (6 files + resources removed)
**Location**: `plugins/src/main/java/com/tonic/plugins/codeeval/`

Java REPL with syntax highlighting including:
- CodeEvalPlugin.java - Plugin integration
- CodeEvalFrame.java - Main editor window
- IsolatedRSyntaxTextArea.java - Syntax-highlighted editor
- SimpleCodeEvaluator.java - Code compilation and execution
- VitaCompletionProvider.java - Intelligent code completion
- TypeInference.java - Static type analysis

**Also removed**:
- `plugins/src/main/resources/com/tonic/plugins/codeeval/` - Plugin resources

### 3. Injector & Mixin System (100+ files removed)
**Locations**:
- `src/main/java/com/tonic/injector/` - Injector engine (15+ files)
- `src/main/java/com/tonic/mixins/` - GamePack mixins (27 files)
- `src/main/java/com/tonic/rlmixins/` - RuneLite mixins (15 files)
- `src/main/resources/com/tonic/injector/mappings.json` - 2.2MB obfuscation mappings

**Injector Components Removed**:
- Injector.java - Main transformation engine
- RLInjector.java - RuneLite-specific injector
- OSGlobalMixin.java, RLGlobalMixin.java
- 17 annotation classes (@Inject, @Shadow, @Mixin, etc.)
- 10+ transformer classes (InjectTransformer, ShadowTransformer, etc.)
- Expression editor and bytecode manipulation utilities

**Mixin Files Removed**:
- 27 GamePack mixins (TClientMixin, TMouseHandlerMixin, etc.)
- 15 RuneLite mixins (PluginManagerMixin, EventBusMixin, etc.)

**Note**: Pre-generated patches in `patches.zip` are KEPT as they're needed for runtime.

### 4. Pathfinder UI (12 files removed)
**Location**: `api/src/main/java/com/tonic/services/pathfinder/ui/`

Visual editors and overlays:
- TransportEditorFrame.java - GUI editor for transport routes
- TransportOverlay.java - In-game path visualization
- RequirementsEditorPanel.java - Path requirements editor
- ToolbarPanel.java, TransportDetailPanel.java, TransportListPanel.java
- 3 utility files (JsonFileManager, ValidationUtils, etc.)

**Note**: Core pathfinding logic is KEPT, only UI components removed.

### 5. Mouse Recording & Visualization (22 files removed)
**Location**: `base-api/src/main/java/com/tonic/services/mouserecorder/`

Mouse movement recording and analysis:
- MouseRecorderAPI.java - Recording API
- MouseMovementBuffer.java, MouseMovementSequence.java
- MovementVisualization.java - Visual rendering
- MousePacketEncoder.java, MousePacketDecoder.java
- 11 trajectory analysis files (TrajectoryAnalyzer, DynamicTimeWarping, etc.)
- 2 UI files (TrajectorySettingsPanel, TrajectoryTrainerMonitor)

**Also removed**:
- `base-api/src/main/java/com/tonic/model/ui/DistanceDebugger.java`

### 6. Hot Swapper (4 files removed)
**Locations**:
- `base-api/src/main/java/com/tonic/services/hotswapper/` (1 file)
- `api/src/main/java/com/tonic/services/hotswapper/` (3 files)

Live plugin reloading system:
- PluginClassLoader.java - Custom class loader
- PluginReloader.java - Hot reload engine
- PluginContext.java - Plugin isolation
- CycleButton.java - UI button

**Replacement**: Modified `Install.java` to use standard `URLClassLoader` for plugin loading.

### 7. IPC System (4 files removed)
**Location**: `base-api/src/main/java/com/tonic/services/ipc/`

Inter-process communication framework:
- Channel.java - Communication channel
- ChannelBuilder.java - Channel factory
- Message.java - Message format
- MessageHandler.java - Message processing

### 8. Heavy Dependencies Removed

#### From main build.gradle.kts:
```kotlin
// Removed RSyntaxTextArea (~2MB)
implementation("com.fifesoft:rsyntaxtextarea:3.1.2")

// Removed Autocomplete (~500KB)
implementation("com.fifesoft:autocomplete:3.1.1")

// Removed JavaParser (~3MB)
implementation("com.github.javaparser:javaparser-symbol-solver-core:3.25.5")
```

#### From base-api/build.gradle.kts:
```kotlin
// Removed Netty (~4MB) - was used by IPC and profiler server
implementation("io.netty:netty-all:5.0.0.Alpha2")

// Removed RSyntaxTextArea
implementation("com.fifesoft:rsyntaxtextarea:3.1.2")

// Removed Autocomplete
implementation("com.fifesoft:autocomplete:3.1.1")

// Removed ANTLR (~600KB)
implementation("org.antlr:antlr4:4.13.1")

// Removed JGraphX (~2MB) - was used by pathfinder UI
implementation("com.github.vlsi.mxgraph:jgraphx:4.2.2")

// Removed JFreeChart (~2MB) - was used by profiler
implementation("org.jfree:jfreechart:1.5.4")
```

#### From plugins/build.gradle.kts:
```kotlin
// Removed all code eval dependencies
implementation("com.fifesoft:rsyntaxtextarea:3.1.2")
implementation("com.fifesoft:autocomplete:3.1.1")
compileOnly("com.github.javaparser:javaparser-symbol-solver-core:3.25.5")
```

**Total dependency savings**: ~15-20MB in JAR files

### 9. Code Changes

#### Modified Files:

**src/main/java/com/tonic/vitalite/Main.java**:
- Removed imports for `Injector`, `RLInjector`, `PatchGenerator`
- Removed `-runInjector` code path (injector execution)
- Simplified to only call `PatchApplier.applyPatches()`
- Removed `MappingProvider.getMappings().clear()` call

**src/main/java/com/tonic/runelite/Install.java**:
- Removed dependency on `PluginClassLoader`
- Replaced with standard `URLClassLoader` 
- Inline implementation of plugin filtering logic
- Uses Google Common's `ClassPath` for discovery

**build.gradle.kts**:
- Updated `shadowJar` task with additional exclusions
- Updated `shadowJarRelease` task with additional exclusions
- Removed heavy dependencies

**plugins/build.gradle.kts**:
- Removed code eval dependencies

**base-api/build.gradle.kts**:
- Removed Netty, JFreeChart, JGraphX, ANTLR, RSyntaxTextArea dependencies

### 10. Build Exclusions Updated

Both `shadowJar` and `shadowJarRelease` now exclude:
```kotlin
exclude("com/tonic/services/profiler/**")
exclude("com/tonic/services/pathfinder/ui/**")
exclude("com/tonic/services/mouserecorder/**")
exclude("com/tonic/services/hotswapper/**")
exclude("com/tonic/services/ipc/**")
exclude("com/tonic/model/ui/DistanceDebugger.class")
exclude("com/tonic/injector/**")
exclude("com/tonic/mixin/**")
exclude("com/tonic/rlmixin/**")
exclude("com/tonic/patch/PatchGenerator.class")
exclude("**/mappings.json")
exclude("com/tonic/plugins/codeeval/**")
```

## What Was Kept

### Essential Runtime Components:
- ✅ **PatchApplier** - Applies pre-generated patches at startup
- ✅ **patches.zip** - Pre-generated bytecode patches (3.3MB, required for runtime)
- ✅ **Core pathfinding** - All pathfinding logic except UI
- ✅ **Patch utilities** - BytecodePatcher, PatternBuilder (needed for applying patches)
- ✅ **ASM libraries** - Still needed for patch application
- ✅ **All bot functionality** - Click management, automation APIs, etc.
- ✅ **All RuneLite plugins** - Profiles, break handler, bank valuer, etc.

### Still Available for Development:
Users can still access the removed components by:
1. Checking out the commit before this change
2. Using the `-runInjector` flag (removed, but patches can be regenerated from source)

## Impact Summary

### File Count:
- **Total files removed**: ~200+ Java files
- **Profiler**: 43 files
- **Code Eval**: 6 files + resources
- **Injector/Mixins**: 100+ files + 2.2MB mappings
- **Pathfinder UI**: 12 files
- **Mouse Recording**: 22 files
- **Hot Swapper**: 4 files
- **IPC**: 4 files

### Size Reduction:
- **Source code removed**: ~5-6MB
- **Dependencies removed**: ~15-20MB
- **Total estimated savings**: ~20-25MB in final JAR
- **Memory savings**: ~50-150MB RAM at runtime

### Performance Impact:
- ✅ **Faster startup** - No profiler initialization, no dev tools loading
- ✅ **Lower memory usage** - No profiler buffers, metrics collectors, or UI components
- ✅ **Smaller binary** - Easier to distribute
- ✅ **Same bot functionality** - All automation features preserved

## Migration Notes

### For Bot Developers:
No changes needed - all bot APIs are unchanged.

### For Plugin Developers:
- If you imported profiler classes: Remove those imports
- If you imported code eval classes: Remove those imports
- If you imported IPC classes: Remove those imports
- All other APIs remain unchanged

### For Users:
- Use the release build: `./gradlew buildRelease`
- All functionality for running bots is preserved
- Reduced memory footprint and faster startup

## Command-Line Flags

### Removed:
- ❌ `-runInjector` - No longer functional (injector removed)

### Still Available:
- ✅ `-noPlugins` - Disable core plugins
- ✅ `-min` - Minimal mode
- ✅ `-noMusic` - No music loading
- ✅ `-incognito` - Display as RuneLite
- ✅ All other flags unchanged

## Technical Notes

### Why Remove the Injector but Keep Patches?

The injector is a **development tool** that:
1. Loads 17,000+ classes into memory (~500MB RAM)
2. Applies ASM transformations (~20 seconds)
3. Generates binary patches

The **patches** are **runtime files** that:
1. Load quickly (~2 seconds)
2. Use minimal memory (~50MB)
3. Provide the same modifications

**For bot clients**, patches are sufficient. The injector is only needed when:
- Creating new mixins
- Updating existing mixins
- Debugging bytecode transformations

### Can Patches Be Regenerated?

Not without the injector. To regenerate patches:
1. Checkout the commit before this change
2. Run with `-runInjector` flag
3. Copy the generated `patches.zip`

Or keep a development branch with the injector for mixin development.

## Conclusion

This change removes **200+ files** and **15-20MB of dependencies** that are only needed for development, debugging, and profiling. The result is a **leaner, faster VitaLite** optimized for running bot clients with the same functionality but reduced memory footprint and startup time.

All core bot functionality, automation APIs, and RuneLite plugins remain fully functional.
