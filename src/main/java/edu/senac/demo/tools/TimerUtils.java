package edu.senac.demo.tools;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public class TimerUtils {
    private Timer timer;
    private boolean isCounting;

    public TimerUtils() {
        timer = new Timer();
        isCounting = false;
    }

    public CompletableFuture<Boolean> startTimer() {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        if (!isCounting) {
            timer.schedule(new TimerTask() {
                int counter = 60 * 5;

                @Override
                public void run() {
                    counter--;

                    if (counter <= 0) {
                        isCounting = false;
                        future.complete(false);
                        cancel();
                    }
                }
            }, 0, 1000); // Schedule task to run every 1 second

            isCounting = true;
        } else {
            future.complete(false);
        }

        return future;
    }

    public boolean isTimerCounting() {
        return isCounting;
    }
}
