package td_game.model.modelnit;

/**
 * The GameLoop class orchestrates the continuous update cycle of the game model.
 * It implements the Runnable interface to execute on a separate thread.
 * Uses a fixed time-step loop to ensure consistent game logic updates regardless of system performance.
 */
public class GameLoop implements IGameLoop, Runnable{
    private  IUpdatable target;
    private final int FPS = 144;
    private final long FRAME_TIME = 1_000_000_000 /FPS;
    private boolean running =false;
    private boolean paused =false;
    private Thread thread;

    /**
     * Constructs a new GameLoop with the specified update target.
     *
     * @param target The IUpdatable object (usually the GameModel) that receives update calls.
     */
    public GameLoop(IUpdatable target){
        this.target = target;
    }

    /**
     * Starts the game loop execution.
     * Initializes the thread if it is not already running.
     */
    public void start(){
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * The main execution method of the game loop thread.
     * Handles the timing accumulation to trigger updates at the fixed frame rate.
     *
     * DISCLAIMER: If the game is paused, the loop sleeps to reduce CPU usage until resumed.
     * This ensures the accumulator does not build up a massive backlog of updates during a pause.
     */
    public void run(){
        long last = System.nanoTime();
        long accumulator = 0;

        while(running){

            if (paused) {
                try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                last = System.nanoTime();
                continue;
            }

            long now = System.nanoTime();
            accumulator += now -last;
            last = now;
            while (accumulator >= FRAME_TIME){
                target.update();  //Updates model
                accumulator -= FRAME_TIME;
            }
            try{Thread.sleep(1);} catch (Exception ignored) {}

        }
    }

    /**
     * Stops the game loop execution.
     * Sets the running flag to false, allowing the thread to terminate naturally.
     */
    public void stop(){
        running = false;
    }
}