package edu.iis.mto.multithread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;
    @Mock
    private ExecutorService executorServiceMock;

    @BeforeEach
    void setup() {

        when(executorServiceMock.submit(any(Runnable.class))).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });
    }

    @RepeatedTest(5)
    void setSendingOneRocketShouldInvokeLaunchPatriotOneTime() {

        int rocketCount = 1;
        BetterRadar radar = new BetterRadar(batteryMock, rocketCount, executorServiceMock);
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);

        verify(batteryMock, times(rocketCount)).launchPatriot(enemyMissile);
    }

    @RepeatedTest(5)
    void setSendingZeroRocketsShouldInvokeLaunchPatriotZeroTimes() {

        int rocketCount = 0;
        BetterRadar radar = new BetterRadar(batteryMock, rocketCount, executorServiceMock);
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);

        verify(batteryMock, times(rocketCount)).launchPatriot(enemyMissile);
    }

    @RepeatedTest(5)
    void setSendingTenRocketsShouldInvokeLaunchPatriotTenTimes() {

        int rocketCount = 10;
        BetterRadar radar = new BetterRadar(batteryMock, rocketCount, executorServiceMock);
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);

        verify(batteryMock, times(rocketCount)).launchPatriot(enemyMissile);
    }
    
}
