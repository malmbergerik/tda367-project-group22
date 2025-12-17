# tda367-project-group22
# Dungeon Defence
## Table Of Contents
 - [System requirments & Build instructions](#system-requirments--build-instructions)
 - [About the Game](#about-the-game)
 - [Game Features](#game-features)
 - [Architecture & Design Patterns](#architecture--design-patterns)
 - [Game Tutorial](#game-tutorial)

## System requirments & Build instructions

### 📋 System requirments

* **Java Development Kit (JDK):** Version 17 or later.
  * *Check version:* `java -version`
* **Apache Maven:** Version 3.6.0 or later
  * *Check version:* `mvn-version`
* **Git:** To clone the project
   * *Check version:* `git --version`

### 🛠️ Buld instruction

1. #### Clone the repo:
   ```bash
   git clone https://github.com/malmbergerik/tda367-project-group22
   cd tda267-project-group22 
   ```

2. #### Build the project:
   This will install required libraries and compile the code.
   ```bash
   mvn clean install
   ```

3. #### Run the game:
   Run the following command in the project root-folder.
   ```bash
   mvn exec:java
   ```

## About the Game
Dungeon Defence is a fast-paced, tile-based Tower Defence game with a dark dungeon theme. The primary purpose of this product is to serve as a micro-break for students, offering a stimulating yet brief moment of relaxation and fun during or between classes. Our core vision with the game was to offer an engaging experience where players construct a robust defence to fend off incoming enemy waves. It is a refreshing challenge that is instant to start and easy to pause, to ensure it respects the demands of a student’s schedule.

## Game Features

### Wave System
* **Configurable Waves**: Enemy waves are defined in an external file (`waves.txt`), allowing for easy balancing and modification of game difficulty without changing modyfing.
* **Grouping**: Waves consist of grouops of enemies that spawn with specific intervals and delays.

### Towers
The game includes diverse towers, each serving a specific tactical role:
* **Canon Tower**: Standard single-target damage.
* **Sniper Tower**: High range and damage, slow fire rate.
* **Flame Thrower**: Constant strea of projectiles (fire).
* **Fire Tack**: Rapid-fire tower in all directions.

### Enemies
Enemies have different stats (health, speed, width, height, damageamount) that are defined in the constructor. The initial objective was to create different special abilities (e.g slime to split into more slimes) for the enemies, however as of now, they all have the same abilities, which is that they all are a 'PathFollowingEnemy'.
* **Slime**: Slow-moving enemy with high health.
* **Skeleton**: Fast-moving enemy with low health.
* **Bat**: Extremely fast enemy with very low health.
* **BabyOrc**: Fast enemy with high health.
* **Golem**: Moderately paced enemy with very high health.

## Architecture & Design Patterns

The project is structured around several software design patterns to maintain clean and extensible code:

### 1. MVC Pattern (Model-View-Controller)
* **Model**: Contains all game data and logic (e.g., `Player`, `WaveManager`, `GridMap`). It communicates only with the Controller.
* **View**: Handles the rendering of the game state (`GamePanel`, `TowerRenderer`). It communicates only with the Controller.
* **Controller**: Acts as the intermediary that works with both the Model and the View, processing user inputs (`InputController`, `MouseController`) and updating the system accordingly.
  
### 2. Observer Pattern
Used extensively to decouple the Model from the View.
* **Player Stats**: The UI (`StatsPanel`) observes `Player` to automatically update health and money displays without the Model knowing about the UI.
* **Game Events**: `IGameObserver` is used to trigger audio or visual effects when specific game events occur (e.g., enemy death, wave completion).

### 3. Factory Pattern
Used to encapsulate the creation logic of complex objects.
* **EnemyFactory**: Creates different enemy types (`Slime`, `Bat`, `Golem`).
* **TowerFactory**: Handles the instantiation of towers, ensuring they are initialized with the correct attributes.

### 4. Strategy Pattern
Used to define interchangeable behaviors for game entities.
* **Tower Targeting**: Towers use strategies (e.g., `TargetingFirst`, `TargetingStrongest`) to decide which enemy to attack.
* **Projectile Movement**: Different projectiles (bullets, fireballs) use movement strategies to define how they travel across the map.

---


## Game Tutorial

### 1. Main Menu

1. Start the game.
2. The **Main Menu** appears.
3. Choose one of the following:
   - **Play** → starts the game.
   - **Exit** → closes the game.
4. *(Planned features: map selection and difficulty settings.)*

---

### 2. 🎮 How to Play the Game

#### 🎯 Game Objective
Your goal is to **stop enemies from reaching the end of the path** by placing towers along the map.

---

#### 📘 Step-by-Step Gameplay

#### Step 1: Starting the Game
1. The game begins with **100 HP** and **$100**.
2. Enemies arrive in **waves**.
3. There are **15 waves** in total.

---

####  Step 2: Buying a Tower
1. Look at the **right sidebar**.
2. Click on one of the **four available towers**.
3. Move your cursor over the map.
4. Hover over an **empty tile**.
5. Click to place the tower.

⚠️ Pay attention to the tower’s **range** so it can hit enemies on the path.

---

#### Step 3: Managing Money
1. Towers cost different amounts of money.
2. You earn money by **defeating enemies**.
3. You can buy towers:
   - Before the first wave starts
   - During a wave
   - After a wave ends

---

####  Step 4: Selling a Tower (Optional)
1. Click on a tower already placed on the map.
2. A **Sell** button appears in the action bar.
3. Click **Sell** to remove the tower.
4. You receive **50% of the tower’s original cost** back.

---

#### Step 5: Enemy Waves
1. Each wave becomes more difficult:
   - More enemies spawn
   - New enemy types are introduced
2. Stronger enemies:
   - Have more health
   - Deal more damage if they reach the end

---

####  Step 6: Health and Losing
1. If an enemy reaches the end of the path:
   - You lose HP.
2. The amount of HP lost depends on the enemy’s strength.
3. If your HP reaches **0**, the game is **over**.

---

####  Winning the Game
- Successfully complete **all 15 waves** to win.

---

### 🗺️ Central Game Area and Action Bar
* **Map View**: Occupying the majority of the screen is the game board. This is where the action takes place: the map is rendered here, enemies spawn and follow the path, and the player places their towers to defend.
* **Info & Bottom Sidebar**: Directly beneath the map is an action bar. When a tower on the map is clicked, this area displays its specific details (name and image) and provides a **Sell** button to remove the tower and recover money.

---

### 📊 Right Sidebar
* **Stats Panel**: Located at the very top of the sidebar, this panel displays critical player information. It shows the current Health Points (HP) in red and the available money.
* **Tower Shop**: Below the stats is the tower selection grid. This area functions as a shop containing buttons for available towers (e.g., Canon, Sniper, Flame Thrower). Each button displays the tower's icon, name, and cost. Clicking a button selects that tower for placement on the map.
* **Starting Waves**: At the bottom of the sidebar, there is a button used for starting the next wave once the current wave is finished.
