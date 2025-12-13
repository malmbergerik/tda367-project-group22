package td_game.model.towers.factory;

import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.*;

import java.util.HashMap;
import java.util.Map;

public class TowerFactory  {

    private final Map<String, ITowerFactory> factories = new HashMap<>();
    private final ProjectileManager projectileManager;

    public TowerFactory(ProjectileManager projectileManager) {
        this.projectileManager = projectileManager;
        registerDefults();
    }

    private void registerDefults() {
        register("CanonTower", (x, y) -> new CanonTower(x, y, projectileManager));
        register("SniperTower", (x, y) -> new SniperTower(x, y, projectileManager));
        register("FlameThrowerTower", (x, y) -> new FlameThrowerTower(x, y, projectileManager));
        register("FireTackTower", (x, y) -> new FireTackTower(x, y, projectileManager));
    }

    public void register(String name, ITowerFactory factory) {
        factories.put(name, factory);
    }

    public ATower createTower(String name, int x, int y) {
        ITowerFactory factory = factories.get(name);
        if (factory == null) {
            System.err.println("No factory found for tower: " + name);
            return null;
        }
        return factory.createTower(x, y);
    }

    public boolean isValidTower(String name) {
        return factories.containsKey(name);
    }
}
