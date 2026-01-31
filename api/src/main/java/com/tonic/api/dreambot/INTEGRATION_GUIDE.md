# DreamBot API Integration Guide for VitaLite

This guide explains how to use the DreamBot-style API wrapper in your VitaLite plugins or scripts.

## Quick Start

### Import the APIs you need

```java
import com.tonic.api.dreambot.npc.NPCs;
import com.tonic.api.dreambot.gameobject.GameObjects;
import com.tonic.api.dreambot.bank.Bank;
import com.tonic.api.dreambot.inventory.Inventory;
import com.tonic.api.dreambot.grounditem.GroundItems;
```

### Basic Usage Patterns

#### Pattern 1: Direct Interaction (One-liner)
```java
// Most common - find and interact in one call
NPCs.interact("Banker", "Bank");
GameObjects.interact("Door", "Open");
GroundItems.take("Coins");
```

#### Pattern 2: Find Then Interact (Two steps)
```java
// When you need to check if something exists first
NpcEx banker = NPCs.closest("Banker");
if (banker != null) {
    NPCs.interact(banker, "Bank");
}
```

#### Pattern 3: Advanced Filtering (Using predicates)
```java
// For complex conditions
NpcEx target = NPCs.closest(npc -> 
    npc.getName().contains("Goblin") && 
    !npc.isInteracting() &&
    npc.getHealthRatio() < 100
);
```

#### Pattern 4: Query Builder (For advanced needs)
```java
// Use the query() method for complex filtering
var nearbyNpcs = NPCs.query()
    .withName("Guard", "Knight")
    .withinDistance(10)
    .keepIf(npc -> !npc.isInteracting())
    .sortNearest()
    .collect();
```

## Common Script Patterns

### Banking Script
```java
public void bank() {
    // Walk to bank first (using movement API when available)
    
    // Open bank
    if (!Bank.isOpen()) {
        NPCs.interact("Banker", "Bank");
        // Add wait for bank to open
        return;
    }
    
    // Manage inventory
    Bank.depositAllItems();
    Bank.withdraw("Logs", 28);
    Bank.close();
}
```

### Resource Gathering
```java
public void gather() {
    // Check if inventory is full
    if (Inventory.isFull()) {
        // Go bank
        return;
    }
    
    // Find and interact with resource
    TileObjectEx tree = GameObjects.closest("Tree");
    if (tree != null && !isPlayerAnimating()) {
        GameObjects.interact(tree, "Chop down");
    }
}
```

### Combat with Looting
```java
public void combatLoop() {
    // Check for loot first
    TileItemEx loot = GroundItems.closest(item -> isValuableLoot(item));
    if (loot != null && !Inventory.isFull()) {
        GroundItems.take(loot);
        return;
    }
    
    // If not in combat, find target
    if (!isInCombat()) {
        NpcEx target = NPCs.closest(npc -> 
            npc.getName().equals("Goblin") &&
            !npc.isInteracting()
        );
        if (target != null) {
            NPCs.interact(target, "Attack");
        }
    }
}

private boolean isValuableLoot(TileItemEx item) {
    String name = item.getName();
    return name.equals("Coins") || 
           name.contains("Rune") || 
           name.contains("Dragon");
}
```

### Skill Training with Banking
```java
public void skillLoop() {
    if (Inventory.isEmpty()) {
        // Get supplies from bank
        if (!Bank.isOpen()) {
            NPCs.interact("Banker", "Bank");
            return;
        }
        Bank.withdraw("Raw lobster", 28);
        Bank.close();
    } else if (Inventory.isFull() || !Inventory.contains("Raw lobster")) {
        // Bank finished products
        if (!Bank.isOpen()) {
            NPCs.interact("Banker", "Bank");
            return;
        }
        Bank.depositAllItems();
    } else {
        // Train skill
        TileObjectEx range = GameObjects.closest("Cooking range");
        if (range != null) {
            Inventory.interact("Raw lobster", "Use");
            // Then click range
            GameObjects.interact(range, "Cook");
        }
    }
}
```

## API Comparison: DreamBot vs VitaLite Wrapper

| Operation | DreamBot | VitaLite Wrapper | VitaLite Native |
|-----------|----------|------------------|-----------------|
| Find closest NPC | `NPCs.closest("Banker")` | `NPCs.closest("Banker")` | `NpcAPI.search().withName("Banker").sortNearest().first()` |
| Interact with NPC | `npc.interact("Bank")` | `NPCs.interact(npc, "Bank")` | `NpcAPI.interact(npc, "Bank")` |
| Check bank open | `Bank.isOpen()` | `Bank.isOpen()` | `BankAPI.isOpen()` |
| Withdraw items | `Bank.withdraw("Logs", 28)` | `Bank.withdraw("Logs", 28)` | `BankAPI.withdraw("Logs", 28, false)` |
| Find objects | `GameObjects.closest("Door")` | `GameObjects.closest("Door")` | `TileObjectAPI.search().withNames("Door").sortNearest().first()` |
| Pick up items | `GroundItems.take("Coins")` | `GroundItems.take("Coins")` | `TileItemAPI.interact(item, "Take")` |

## Tips for Migration from DreamBot

### 1. Method Names Are Similar But Not Identical
- Most method names match DreamBot exactly
- Some methods have slight variations due to VitaLite's architecture
- Check the JavaDoc for each method when in doubt

### 2. Return Types May Differ
- DreamBot uses their own wrapper classes (`NPC`, `GameObject`, etc.)
- VitaLite uses `NpcEx`, `TileObjectEx`, `TileItemEx`, `ItemEx`
- These classes have similar methods and can be used interchangeably

### 3. Query API is Available
- Use `.query()` on any API class to access the full query builder
- This gives you access to VitaLite's powerful filtering system

### 4. Boolean Returns vs Void
- Most interact methods return `boolean` indicating success
- In VitaLite, you may need to add your own validation logic

### 5. No Built-in Waiting
- DreamBot has built-in waiting for many actions
- You'll need to implement your own waiting logic (see Script utilities section when implemented)

## Best Practices

### 1. Always Check for Null
```java
// Good
NpcEx npc = NPCs.closest("Banker");
if (npc != null) {
    NPCs.interact(npc, "Bank");
}

// Bad - can cause NullPointerException
NPCs.interact(NPCs.closest("Banker"), "Bank");
```

### 2. Use IDs When Possible
```java
// More reliable - IDs don't change
Bank.withdraw(1511, 28); // Logs
NPCs.interact(1234, "Attack");

// Less reliable - names can change with game updates
Bank.withdraw("Logs", 28);
```

### 3. Prefer Predicate Filtering for Complex Logic
```java
// Good - readable and maintainable
NpcEx target = NPCs.closest(npc -> {
    return npc.getName().contains("Goblin") &&
           npc.getHealthRatio() > 0 &&
           !npc.isInteracting();
});

// Avoid - hard to maintain
NpcEx target = NPCs.closest("Goblin");
// Then manually check conditions
```

### 4. Cache Frequently Used Queries
```java
// If you're checking the same thing repeatedly in a loop
private List<Integer> VALUABLE_ITEM_IDS = Arrays.asList(995, 1514, 1516);

public void lootLoop() {
    TileItemEx loot = GroundItems.closest(item -> 
        VALUABLE_ITEM_IDS.contains(item.getId())
    );
    // ... rest of logic
}
```

## Troubleshooting

### "Method not found" errors
- Make sure you imported the correct class
- Check if you're using the wrapper class vs native VitaLite class
- Verify the method name matches the documentation

### Interactions not working
- Ensure the game object/NPC/item exists before interacting
- Check that the action name is correct (case-sensitive)
- Verify you're logged in and the game is loaded

### Performance issues
- Avoid calling `.getAll()` in tight loops
- Use `.closest()` or `.first()` instead when possible
- Cache results when appropriate

## Future Additions

The following APIs are planned for future implementation:
- Players API
- Skills API  
- Combat API
- Magic/Prayer APIs
- Equipment API
- Walking/Movement API
- Camera API
- And more (see README.md for full list)

## Getting Help

1. Check the JavaDoc comments on each method
2. Review the examples in `DreamBotAPIExamples.java`
3. Consult the VitaLite documentation for underlying APIs
4. Refer to DreamBot documentation for expected behavior

## Contributing

If you'd like to help implement additional APIs:
1. Follow the existing pattern of thin wrapper methods
2. Add comprehensive JavaDoc comments
3. Include usage examples
4. Test thoroughly before submitting
