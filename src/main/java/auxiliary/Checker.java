package auxiliary;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Checker {

    private long lastTime;

    private void ttt() {
        throw new NullPointerException();
    }

    public void testFileUpdate() {
        File file = new File("cityCollection");

        lastTime = file.lastModified();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (file.lastModified() > lastTime) {
                    System.out.println("file update! time : " + file.lastModified());
                    lastTime = file.lastModified();
                    ttt();
                }//else new Messager().println("[eq", true);
            }
        }, 0, 1, TimeUnit.SECONDS);


        try {
            Thread.sleep(1000 * 60 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}