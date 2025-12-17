package td_game.model.towers.factory;

import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The TowerFactory class handles the creation of tower instances based on string identifiers.
 * It uses a registry pattern to map tower names to specific creation logic (lambdas).
 * This allows the game to instantiate towers dynamically without hardcoded switch statements.
 */
public class TowerFactory  {

    private final Map<String, ITowerFactory> factories = new HashMap<>();
    private final ProjectileManager projectileManager;

    /**
     * Constructs a new TowerFactory.
     * Initializes the manager reference and registers the default set of game towers.
     *
     * @param projectileManager The manager required by towers to spawn projectiles.
     */
    public TowerFactory(ProjectileManager projectileManager) {
        this.projectileManager = projectileManager;
        registerDefults();
    }

    /**
     * Registers the default towers (Canon, Sniper, FlameThrower, FireTack) into the factory registry.
     */
    private void registerDefults() {
        register("CanonTower", (x, y) -> new CanonTower(x, y, projectileManager));
        register("SniperTower", (x, y) -> new SniperTower(x, y, projectileManager));
        register("FlameThrowerTower", (x, y) -> new FlameThrowerTower(x, y, projectileManager));
        register("FireTackTower", (x, y) -> new FireTackTower(x, y, projectileManager));
    }

    /**
     * Registers a new tower type with the factory.
     *
     * @param name    The unique string identifier for the tower type.
     * @param factory The functional interface (lambda) used to create the tower instance.
     */
    public void register(String name, ITowerFactory factory) {
        factories.put(name, factory);
    }

    /**
     * Creates a new tower instance corresponding to the provided name.
     *
     * @param name The name of the tower to create.
     * @param x    The x-coordinate for the new tower.
     * @param y    The y-coordinate for the new tower.
     * @return A new ATower instance, or null if the tower name is not registered.
     */
    public ATower createTower(String name, int x, int y) {
        ITowerFactory factory = factories.get(name);
        if (factory == null) {
            System.err.println("No factory found for tower: " + name);
            return null;
        }
        return factory.createTower(x, y);
    }

    /**
     * Verifies if a tower type exists in the registry.
     *
     * @param name The name of the tower to check.
     * @return true if a factory exists for the name, false otherwise.
     */
    public boolean isValidTower(String name) {
        return factories.containsKey(name);
    }
}