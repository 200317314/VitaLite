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

## Thread Safety

**All interact methods in the DreamBot API wrapper are thread-safe.**

The wrapper delegates to VitaLite's native APIs, which use `Static.invoke()` to ensure all game actions are executed on the proper client thread. This follows the RuneLite/OSRS bot development pattern for safe interaction.

### How it works:

1. When you call `NPCs.interact("Banker", "Bank")`, the wrapper delegates to `NpcAPI.interact()`
2. `NpcAPI.interact()` wraps the packet writing in `Static.invoke()`:
   ```java
   Static.invoke(() -> {
       ClickManager.click(ClickType.ACTOR);
       client.getPacketWriter().npcActionPacket(option, npcIndex, false);
   });
   ```
3. `Static.invoke()` checks if the current thread is the client thread:
   - If yes: executes immediately
   - If no: schedules execution on the client thread and waits for completion

### What this means for you:

- ✅ You can safely call interact methods from any thread (script threads, event handlers, UI threads)
- ✅ No need to manually wrap calls in `Static.invoke()` or `ClientThread.invoke()`
- ✅ Thread synchronization is handled automatically
- ✅ No race conditions or threading issues when interacting with game entities

### Example:

```java
// All of these are thread-safe, regardless of which thread you're on:
NPCs.interact("Banker", "Bank");              // Thread-safe ✅
GameObjects.interact("Door", "Open");         // Thread-safe ✅  
Inventory.interact("Logs", "Drop");           // Thread-safe ✅
GroundItems.take("Coins");                    // Thread-safe ✅
Bank.withdraw("Logs", 28);                    // Thread-safe ✅
```

For more detailed examples of thread safety, see `examples/ThreadSafetyExample.java`.

## NEW: Additional API Wrappers (Extended Coverage)

The following APIs have been added to provide comprehensive DreamBot-style coverage:

### ✅ Players (`com.tonic.api.dreambot.player.Players`)
- `getAll()` / `getAll(filter)` / `getAll(names...)` - Get players
- `closest()` / `closest(filter)` / `closest(names...)` - Get closest player
- `closestWithinDistance(distance)` - Get closest player within distance
- `getLocal()` - Get local player
- `interact(player, actions...)` - Interact with player
- `interact(name, action)` - Find and interact with player
- `query()` - Create advanced query
- `isIdle()` / `isAnimating()` / `isMoving()` - Check player state
- `getLocation()` - Get player location

### ✅ Skills (`com.tonic.api.dreambot.skills.Skills`)
- `getLevel(skill)` - Get base level of skill
- `getBoostedLevel(skill)` - Get current (boosted) level
- `getExperience(skill)` - Get experience in skill
- `getExperienceToNextLevel(skill)` - Get XP needed for next level
- `getTotalLevel()` - Get total level across all skills
- `getBoostedTotalLevel()` - Get total boosted level
- `getTotalExperience()` - Get total experience
- `getExperienceAt(level)` - Get XP required for level
- `getLevelAt(experience)` - Get level for XP amount

### ✅ Equipment (`com.tonic.api.dreambot.equipment.Equipment`)
- `getAll()` - Get all equipped items
- `isEquipped(id/name/filter)` - Check if item is equipped
- `getItem(id/name/filter)` - Get equipped item
- `getItemInSlot(slot)` - Get item in specific slot
- `equip(id/name)` - Equip item from inventory
- `unequip(slot/item)` - Unequip item
- `unequipAll()` - Unequip all items
- `getCount(id)` - Get count of equipped item
- `interact(item, action)` - Interact with equipped item
- `query()` - Create advanced query

### ✅ Combat (`com.tonic.api.dreambot.combat.Combat`)
- `isInCombat()` - Check if in combat
- `isUnderAttack()` - Check if under attack
- `getTarget()` - Get current combat target
- `getAttacker()` - Get current attacker
- `isAutoRetaliateEnabled()` - Check auto-retaliate status
- `getSpecialAttackEnergy()` - Get special attack energy (0-100)
- `isSpecialAttackEnabled()` - Check if special attack is enabled
- `getWildernessLevel()` - Get wilderness level
- `isInWilderness()` - Check if in wilderness

### ✅ Walking (`com.tonic.api.dreambot.walking.Walking`)
- `walk(destination)` - Walk to world point
- `walk(x, y, plane)` - Walk to coordinates
- `isMoving()` - Check if player is moving
- `getDestination()` - Get movement destination
- `getRunEnergy()` - Get run energy (0-100)
- `isRunEnabled()` - Check if run is enabled
- `toggleRun()` - Toggle run mode
- `isStaminaActive()` - Check if stamina is active
- `enableRun()` / `disableRun()` - Set run mode

### ✅ Magic (`com.tonic.api.dreambot.magic.Magic`)
- `cast(spell)` - Cast spell
- `cast(spell, target)` - Cast spell on NPC/Player/Item/Object/GroundItem
- `isAutoCasting()` - Check if autocasting
- `isAutoCastUsable()` - Check if autocast is usable
- `setBestAutoCast()` - Set best available autocast
- `isHomeTeleportOnCooldown()` - Check home teleport cooldown
- `isDefensiveCasting()` - Check defensive casting mode

### ✅ Prayer (`com.tonic.api.dreambot.prayer.Prayer`)
- `isAnyActive()` - Check if any prayer is active
- `isActive(prayer)` - Check if specific prayer is active
- `activate(prayer)` / `deactivate(prayer)` - Activate/deactivate prayer
- `toggle(prayer)` - Toggle prayer
- `deactivateAll()` - Deactivate all prayers
- `isQuickPrayerEnabled()` - Check quick prayer status
- `toggleQuickPrayer()` - Toggle quick prayer
- `turnOnQuickPrayers()` / `turnOffQuickPrayers()` - Set quick prayer state
- `setQuickPrayer(prayers...)` - Configure quick prayers
- `hasLevelFor(prayer)` - Check if player has required level
- `getPrayerLevel()` / `getPrayerPoints()` - Get prayer info

### ✅ Camera (`com.tonic.api.dreambot.camera.Camera`)
- `getYaw()` / `getPitch()` - Get camera angles
- `setYaw(yaw)` / `setPitch(pitch)` - Set camera angles
- `turnToNorth()` / `turnToSouth()` / `turnToEast()` / `turnToWest()` - Turn camera
- `getX()` / `getY()` / `getZ()` - Get camera position

### ✅ Dialogue (`com.tonic.api.dreambot.dialogue.Dialogue`)
- `isOpen()` - Check if dialogue is open
- `getHeader()` - Get dialogue header (speaker name)
- `getText()` - Get dialogue text
- `canContinue()` - Check if can continue
- `continueDialogue()` - Continue dialogue
- `hasOptions()` - Check if options available
- `getOptions()` - Get dialogue options
- `selectOption(text)` - Select option by text
- `selectOptionIndex(index)` - Select option by index
- `completeDialogue()` - Complete entire dialogue
- `isNPCDialogue()` / `isPlayerDialogue()` - Check dialogue type

### ✅ Tabs (`com.tonic.api.dreambot.tabs.Tabs`)
- `open(tab)` - Open specific tab
- `isOpen(tab)` - Check if tab is open
- `openCombat()` / `openSkills()` / `openQuests()` - Quick access methods
- `openInventory()` / `openEquipment()` / `openPrayer()` / `openMagic()` - More quick methods
- `openFriends()` / `openAccount()` / `openLogout()` / `openSettings()` - Additional tabs
- `openEmotes()` / `openMusic()` - Even more tabs

### ✅ GrandExchange (`com.tonic.api.dreambot.grandexchange.GrandExchange`)
- `isOpen()` - Check if GE is open
- `close()` - Close GE interface
- `canCollect()` - Check if items can be collected
- `collect()` - Collect completed offers
- `getOffers()` - Get all active offers
- `abortOffer(itemId)` - Abort offer
- `isOfferComplete(itemId)` - Check if offer is complete
- `getFirstEmptySlot()` - Get first empty slot

### ✅ Trade (`com.tonic.api.dreambot.trade.Trade`)
- `isOpen()` - Check if trade window is open
- `isOnMainScreen()` / `isOnConfirmationScreen()` - Check trade screen
- `getOfferingItems()` / `getReceivingItems()` - Get trade items
- `offer(id/name, amount)` - Offer items
- `remove(id/name, amount)` - Remove items from trade
- `accept()` / `decline()` - Accept or decline trade
- `hasAccepted()` / `hasOtherAccepted()` - Check acceptance status

### ✅ Shop (`com.tonic.api.dreambot.shop.Shop`)
- `isOpen()` - Check if shop is open
- `close()` - Close shop interface
- `buy(id/name, amount)` - Buy items
- `sell(id/name, amount)` - Sell items
- `getShopItems()` - Get all shop items
- `getShopItem(id/name)` - Get specific shop item
- `contains(id/name)` - Check if shop contains item

### ✅ Game (`com.tonic.api.dreambot.game.Game`)
- `isLoggedIn()` - Check if player is logged in
- `isOnLoginScreen()` - Check if on login screen
- `getWildernessLevel()` - Get wilderness level
- `isInWilderness()` - Check if in wilderness
- `logout()` - Logout of game
- `getCurrentWorld()` - Get current world number
- `getGameTick()` - Get game tick count
- `getUsername()` - Get player username
- `getFPS()` - Get current FPS

### ✅ Worlds (`com.tonic.api.dreambot.world.Worlds`)
- `getCurrentWorld()` - Get current world
- `hopRandomMembers()` / `hopRandomF2P()` - Hop to random world
- `hopNextMembers()` / `hopPreviousMembers()` - Hop to next/previous members world
- `hopNextF2P()` / `hopPreviousF2P()` - Hop to next/previous F2P world
- `hop(world)` / `hop(worldId)` - Hop to specific world
- `query()` / `query(includeCurrentWorld)` - Create world query
- `getCurrentWorldNumber()` - Get current world number

### ✅ Widgets (`com.tonic.api.dreambot.widgets.Widgets`)
- `get(groupId, childId)` / `get(packedId)` - Get widget
- `isVisible(widget)` / `isVisible(groupId, childId)` - Check visibility
- `interact(widget, actions...)` - Interact with widget
- `getText(widget)` / `getText(groupId, childId)` - Get widget text
- `query()` - Create widget query
- `isTextVisible(text)` - Check if text is visible

### ✅ DepositBox (`com.tonic.api.dreambot.depositbox.DepositBox`)
- `isOpen()` - Check if deposit box is open
- `close()` - Close deposit box
- `depositAll()` - Deposit all items
- `depositWornItems()` - Deposit all worn items
- `depositLootingBag()` - Deposit looting bag
- `deposit(id/name, amount)` - Deposit specific items
- `depositAllExcept(ids.../names...)` - Deposit all except specified

### ✅ MiniMap (`com.tonic.api.dreambot.minimap.MiniMap`)
- `drawPath(graphics, path, color)` - Draw path on minimap
- `drawPoint(graphics, point, color)` - Draw point on minimap

### ✅ Calculations (`com.tonic.api.dreambot.calculations.Calculations`)
- `distance(p1, p2)` - Calculate distance between points
- `distanceToPlayer(point)` - Calculate distance to player
- `onSamePlane(p1, p2)` - Check if points on same plane
- `planeDifference(p1, p2)` - Get plane difference
- `isWithinDistance(p1, p2, maxDistance)` - Check if within distance
- `isWithinDistanceOfPlayer(point, maxDistance)` - Check if within distance of player

## Summary

The DreamBot API wrapper now includes **24 API classes** with **270+ convenience methods** covering:
- Entity interaction (NPCs, Players, GameObjects, GroundItems)
- Inventory management (Inventory, Equipment, Bank, DepositBox)
- Combat systems (Combat, Skills, Magic, Prayer)
- Movement & Interaction (Walking, Camera, Dialogue, Tabs)
- Trading & Commerce (GrandExchange, Trade, Shop)
- Utilities (Game, Worlds, Widgets, MiniMap, Calculations)

All APIs follow DreamBot naming conventions and are fully thread-safe.
