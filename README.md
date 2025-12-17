# tda367-project-group22
# Dungeon Defence
## Table Of Contents
 - [About the Game](#about-the-game)
 - [Software requirments & Build instructions](#software-requirments--build-instructions)
 - [Game Features](#game-features)
 - [Architecture & Design Patterns](#architecture--design-patterns)
 - [Useful commands](#useful-commands)
 - [Tutorial - How to Play](#tutorial---how-to-play)

## About the Game
Dungeon Defence is a fast-paced, tile-based Tower Defence game with a dark dungeon theme. The primary purpose of this product is to serve as a micro-break for students, offering a stimulating yet brief moment of relaxation and fun during or between classes. Our core vision with the game was to offer an engaging experience where players construct a robust defence to fend off incoming enemy waves. It is a refreshing challenge that is instant to start and easy to pause, to ensure it respects the demands of a student’s schedule.

## Software requirments & Build instructions

## Game Features

### Wave System
* **Configurable Waves**: Enemy waves are defined in an external file (`waves.txt`), allowing for easy balancing and modification of game difficulty without changing modyfing.
* **Grouping**: Waves consist of grouops of enemies that spawn with specific intervals and delays.

### Towers
The game includes diverse towers, each serving a specific tactical role:
* **Canon Tower**: Standard single-target damage.
* **Sniper Tower**: High range and damage, slow fire rate.
* **Flame Thrower**: Deals Area of Effect (AOE) damage.
* **Fire Tack**: Rapid-fire tower in all directions.

### Enemies
Enemies have different stats (health, speed, width, height, damageamount) that are defined in the constructor of 'GameModel'. The initial objective was to create different special abilities (e.g slime to split into more slimes) for the enemies, however as of now, they all have the same abilities which is that they are 'PathFollowingEnemies".
* **Slime**
* **Skeleton**:
* **Golem**
* **Bat**

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

## Useful commands
Because this is a Java Maven program these are the useful maven commands:
* **Compile code**: 'mvn compile' or 'mvn clean compile'.
* **Run the program**: 'mvn exec:java'.
* **Run the tests**: 'mvn test'.


## Tutorial - How to play

### 1. Main menu
When you load the game you are met with a main menu page. Here you have two alternatives to play the game or to exit (close down the game). Future implementations are to add different maps and difficulties.

### 2. Game 
#### Game Objective
The goal of the game is to prevent the enemies to from reaching the end of the path. You do this by placing towers provided on the right sidebar on the maptiles. You cannot place more than one tower on the same tile and and on the lava tiles. Make sure to pay attention to the range of 

#### Central Game Area
* **Map View**: Occupying the majority of the screen is the game board. This is where the action takes place: the map is rendered here, enemies spawn and follow the path, and the player places their towers to defend.
* **Info & Action Bar**: Directly beneath the map is a sidebar. When a tower on the map is clicked, this area displays its specific details (name and image) and provides a "Sell" button to remove the tower and recover money.

#### Right Sidebar
* **Stats Panel**: Located at the very top of the sidebar, this panel displays critical player information. It shows the current Health Points (hp) in red and the available money.
* **Tower Shop**: Below the stats is the tower selection grid. This area functions as a shop containing buttons for available towers (e.g., Canon, Sniper, Flame Thrower). Each button displays the tower's icon, name, and cost. Clicking a button selects that tower for placement on the map.
* **Starting waves**: At the bottom of the sidebar, there is a button used for starting the waves once a wave is finished. 
  
