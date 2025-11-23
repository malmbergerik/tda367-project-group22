package td_game.model.enemy;

import java.lang.Math;

public class Skeleton extends ABaseEnemy{

    public Skeleton(double health, double speed, int width, int height, Path path){
        this.health = health;
        this.speed = speed;
        this.width = width;
        this.height = height;
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
    public void takeDamage(double amount) {
        health -= amount;
    }

    @Override
    public void move() {
        if (!hasReachedEnd()) {
           Waypoint targetWaypoint = path.get(currentWaypointIndex);
           double nextX = targetWaypoint.getX();
           double nextY = targetWaypoint.getY();

           double dx = nextX - posX;
           double dy = nextY - posY;

           //pythagorean theorem to calculate distance
           double distanceToWaypoint = Math.sqrt(dx * dx + dy * dy);

           if (distanceToWaypoint <= speed) {
               //reach the waypoint
               posX = nextX;
               posY = nextY;
               currentWaypointIndex++;

               // Immediately move towards the next waypoint if it exists
               if (!hasReachedEnd()) {
                   move();
               }
           }

           else {
               //move towards the waypoint
               double ratio = speed / distanceToWaypoint;
               posX += dx * ratio;
               posY += dy * ratio;
           }
        }
    }
}