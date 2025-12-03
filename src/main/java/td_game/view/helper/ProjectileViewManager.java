package td_game.view.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ProjectileViewManager {
    private final Map<String, BufferedImage> projectileImages;

    public ProjectileViewManager() {
        projectileImages = new HashMap<>();
        loadProjectileImages();
    }

    private void loadProjectileImages() {
        projectileImages.put("Projectile", loadImage("assets/projectiles/Projectile.png"));
        projectileImages.put("Bullet", loadImage("assets/projectiles/Bullet.png"));
        projectileImages.put("Fireball", loadImage("assets/projectiles/Fireball.png"));
        // Lägg till fler projektiltyper här
    }

    private BufferedImage loadImage(String path) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
            if (stream == null) {
                throw new IOException("Could not find resource: " + path);
            }
            return ImageIO.read(stream);
        } catch (IOException e) {
            System.err.println("Failed to load projectile image: " + path);
            return null;
        }
    }

    public BufferedImage getProjectileImage(String name) {
        return projectileImages.get(name);
    }
}
