package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;

public class BetterRadar {

    private final int rocketCount;
    private final PatriotBattery battery;
    private final ExecutorService executorService;

    public BetterRadar(PatriotBattery battery, int rocketCount, ExecutorService executorService) {
        this.battery = battery;
        this.rocketCount = rocketCount;
        this.executorService = executorService;
    }

    public void notice(Scud enemyMissile) {
        launchPatriot(enemyMissile, rocketCount);
    }

    private void launchPatriot(Scud enemyMissile, int rocketCount) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocketCount; i++) {
                battery.launchPatriot(enemyMissile);
            }
        };
        executorService.submit(launchPatriotTask);
    }
}
