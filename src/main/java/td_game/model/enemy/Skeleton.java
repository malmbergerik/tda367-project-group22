package td_game.model.enemy;

public class Skeleton extends ABaseEnemy{

    public Enemy1(double health, double speed, Path path){
        this.health = health;
        this.speed = speed;
        this.path = path;

        if (path != null && path.length() > 0) {
            Waypoint firstWaypoint = path.get(0); // Get the first waypoint (starting position)
            this.posX = firstWaypoint.getX();
            this.posY = firstWaypoint.getY();

            this.currentWaypointIndex = 1; // Set the next waypoint index
        }
        else {
            this.posX = 0;
            this.posY = 0;
        }
    }

    @Override
    public double getX() {
        return posX;
    }

    @Override
    public double getY() {
        return posY;
    }

    @Override
    public void takeDamage(double amount) {
        health -= amount;
    }

    @Override
    public void move() {
        if (!hasReachedEnd()) {
           Waypoint targetWaypoint = path.get(currentWaypointIndex);
           double nextX = targetWaypoint.getX();
           double nextY = targetWaypoint.getY();

           double deltaX = nextX - posX;
           double deltaY = nextY - posY;


        }
    }
}