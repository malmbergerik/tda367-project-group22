package td_game.model.modelnit;

public class GameLoop implements IGameLoop, Runnable{
    private  IUpdatable target;
    private final int FPS = 144;
    private final long FRAME_TIME = 1_000_000_000 /FPS;
    private boolean running =false;
    private boolean paused =false;
    private Thread thread;

    public GameLoop(IUpdatable target){
        this.target = target;
    }

    public void start(){
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

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

    public void stop(){
        running = false;
    }
}
