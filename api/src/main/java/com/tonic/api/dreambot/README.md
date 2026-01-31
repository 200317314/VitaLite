# DreamBot-Style API Wrapper for VitaLite

This package provides a DreamBot-style API wrapper for VitaLite, making it easier for users familiar with DreamBot to transition to VitaLite.

## Implemented APIs

### ✅ NPCs (`com.tonic.api.dreambot.npc.NPCs`)
- `getAll()` - Get all NPCs
- `getAll(filter)` - Get NPCs matching filter
- `getAll(names...)` - Get NPCs by name
- `getAll(ids...)` - Get NPCs by ID
- `closest()` - Get closest NPC
- `closest(filter)` - Get closest NPC matching filter
- `closest(names...)` - Get closest NPC by name
- `closest(ids...)` - Get closest NPC by ID
- `closestThatContains(text)` - Get closest NPC whose name contains text
- `closest(location)` - Get closest NPC at location
- `closestWithAction(action)` - Get closest NPC with action
- `interact(npc, action)` - Interact with NPC
- `interact(npc, actionIndex)` - Interact with NPC by index
- `interact(name, action)` - Find and interact with NPC by name
- `interact(id, action)` - Find and interact with NPC by ID
- `query()` - Create advanced query

### ✅ GameObjects (`com.tonic.api.dreambot.gameobject.GameObjects`)
- `getAll()` - Get all game objects
- `getAll(filter)` - Get objects matching filter
- `getAll(names...)` - Get objects by name
- `getAll(ids...)` - Get objects by ID
- `closest()` - Get closest object
- `closest(filter)` - Get closest object matching filter
- `closest(names...)` - Get closest object by name
- `closest(ids...)` - Get closest object by ID
- `closestThatContains(text)` - Get closest object whose name contains text
- `closest(location)` - Get closest object at location
- `closestWithAction(action)` - Get closest object with action
- `getObjectAt(location, name)` - Get object at location by name
- `getObjectAt(location, id)` - Get object at location by ID
- `interact(object, action)` - Interact with object
- `interact(object, actionIndex)` - Interact with object by index
- `interact(name, action)` - Find and interact with object by name
- `interact(id, action)` - Find and interact with object by ID
- `query()` - Create advanced query

### ✅ Bank (`com.tonic.api.dreambot.bank.Bank`)
- `isOpen()` - Check if bank is open
- `close()` - Close bank
- `contains(id)` - Check if bank contains item
- `contains(ids...)` - Check if bank contains items
- `contains(name)` - Check if bank contains item by name
- `count(id)` - Get count of item
- `count(name)` - Get count of item by name
- `getAll()` - Get all bank items
- `getItem(id)` - Get item by ID
- `getItem(name)` - Get item by name
- `withdraw(id, amount)` - Withdraw item
- `withdraw(name, amount)` - Withdraw item by name
- `withdrawNoted(id, amount)` - Withdraw item as noted
- `withdrawNoted(name, amount)` - Withdraw item as noted by name
- `withdrawAll(id)` - Withdraw all of item
- `withdrawAll(name)` - Withdraw all of item by name
- `deposit(id, amount)` - Deposit item
- `deposit(name, amount)` - Deposit item by name
- `depositAll(id)` - Deposit all of item
- `depositAll(name)` - Deposit all of item by name
- `depositAllItems()` - Deposit all inventory items
- `depositAllEquipment()` - Deposit all equipment
- `setWithdrawMode(noted)` - Set withdraw mode
- `isWithdrawAsNote()` - Check withdraw mode
- `query()` - Create advanced query

### ✅ Inventory (`com.tonic.api.dreambot.inventory.Inventory`)
- `getAll()` - Get all inventory items
- `getAll(filter)` - Get items matching filter
- `getAll(names...)` - Get items by name
- `getAll(ids...)` - Get items by ID
- `getItem(id)` - Get item by ID
- `getItem(name)` - Get item by name
- `getItem(filter)` - Get item matching filter
- `contains(id)` - Check if inventory contains item
- `contains(ids...)` - Check if inventory contains all items
- `containsAny(ids...)` - Check if inventory contains any items
- `contains(name)` - Check if inventory contains item by name
- `contains(names...)` - Check if inventory contains all items by name
- `containsAny(names...)` - Check if inventory contains any items by name
- `count(id)` - Get count of item
- `count(ids...)` - Get count of items
- `count(name)` - Get count of item by name
- `count(names...)` - Get count of items by name
- `isFull()` - Check if inventory is full
- `isEmpty()` - Check if inventory is empty
- `getEmptySlots()` - Get number of empty slots
- `interact(item, action)` - Interact with item
- `interact(id, action)` - Interact with item by ID
- `interact(name, action)` - Interact with item by name
- `drop(id)` - Drop item
- `drop(name)` - Drop item by name
- `dropAll(id)` - Drop all items of type
- `dropAll(name)` - Drop all items by name
- `dropAll(ids...)` - Drop all items of types
- `useItemOn(item, object)` - Use item on game object
- `useItemOn(item, groundItem)` - Use item on ground item
- `useItemOn(item, npc)` - Use item on NPC
- `useItemOn(item, player)` - Use item on player
- `useItemOn(item, target)` - Use item on another item
- `dragItem(item, toSlot)` - Drag item to slot
- `query()` - Create advanced query

### ✅ GroundItems (`com.tonic.api.dreambot.grounditem.GroundItems`)
- `getAll()` - Get all ground items
- `getAll(filter)` - Get items matching filter
- `getAll(names...)` - Get items by name
- `getAll(ids...)` - Get items by ID
- `closest()` - Get closest ground item
- `closest(filter)` - Get closest item matching filter
- `closest(names...)` - Get closest item by name
- `closest(ids...)` - Get closest item by ID
- `closestThatContains(text)` - Get closest item whose name contains text
- `closest(location)` - Get closest item at location
- `getItemAt(location, name)` - Get item at location by name
- `getItemAt(location, id)` - Get item at location by ID
- `getAllAt(location)` - Get all items at location
- `interact(item, action)` - Interact with item
- `interact(item, actionIndex)` - Interact with item by index
- `take(item)` - Take (pick up) item
- `take(name)` - Find and take item by name
- `take(id)` - Find and take item by ID
- `interact(name, action)` - Find and interact with item by name
- `interact(id, action)` - Find and interact with item by ID
- `contains(name)` - Check if item exists on ground
- `contains(id)` - Check if item exists on ground
- `query()` - Create advanced query

## Remaining APIs to Implement

The following APIs from DreamBot would be useful additions to complete the wrapper:

### 🔲 Players
- Methods for finding and interacting with other players
- Similar to NPCs but for players

### 🔲 Skills
- Getting skill levels, experience
- Calculating experience to next level
- Skill tracking

### 🔲 Combat
- Check if in combat
- Get target
- Auto-retaliate settings
- Special attack methods

### 🔲 Magic
- Casting spells
- Checking rune requirements
- Autocast settings

### 🔲 Prayer
- Activating/deactivating prayers
- Checking prayer points
- Quick prayers

### 🔲 Equipment
- Getting equipped items
- Equipping/unequipping items
- Checking equipment slots

### 🔲 Walking/Movement
- Walking to tiles
- Path finding
- Checking if moving
- Getting destination

### 🔲 Camera
- Setting camera angle/zoom
- Rotating camera
- Pitch control

### 🔲 Widgets
- Generic widget interaction
- Dialog handling
- Chat options

### 🔲 GrandExchange
- Buying/selling items
- Checking offer status
- Getting prices

### 🔲 Trade
- Accepting/declining trades
- Adding/removing items
- Trade state

### 🔲 Shop
- Buying/selling to NPCs
- Checking stock

### 🔲 Tabs
- Switching tabs
- Checking current tab

### 🔲 World
- World hopping
- Checking world properties
- Getting world list

### 🔲 Client
- FPS, tick count
- Game state
- Login state

### 🔲 Calculations
- Distance calculations
- Area checks
- Tile validity

### 🔲 Mouse
- Mouse position
- Click methods (if needed)

### 🔲 Keyboard
- Key press methods (if needed)

### 🔲 Script
- Sleep methods
- Conditional waits
- Random generators

## Usage

Import the classes you need:

```java
import com.tonic.api.dreambot.npc.NPCs;
import com.tonic.api.dreambot.gameobject.GameObjects;
import com.tonic.api.dreambot.bank.Bank;
import com.tonic.api.dreambot.inventory.Inventory;
import com.tonic.api.dreambot.grounditem.GroundItems;
```

Or use the main entry point:

```java
import com.tonic.api.dreambot.DreamBotAPI;
```

Then use the static methods as you would in DreamBot:

```java
// Find and talk to a banker
NPCs.interact("Banker", "Bank");

// Open bank and withdraw items
if (Bank.isOpen()) {
    Bank.depositAllItems();
    Bank.withdraw("Logs", 28);
}

// Drop all items of a type
Inventory.dropAll("Logs");

// Pick up ground items
GroundItems.take("Coins");

// Interact with game objects
GameObjects.interact("Door", "Open");
```

## Architecture

This wrapper is built on top of VitaLite's existing APIs:
- `NPCs` wraps `NpcAPI`
- `GameObjects` wraps `TileObjectAPI`
- `Bank` wraps `BankAPI`
- `Inventory` wraps `InventoryAPI`
- `GroundItems` wraps `TileItemAPI`

The wrapper maintains a thin layer that translates DreamBot-style method calls into VitaLite API calls, making it easy to maintain and extend.
