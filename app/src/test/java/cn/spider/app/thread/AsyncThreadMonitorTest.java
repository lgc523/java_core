package cn.spider.app.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncThreadMonitorTest {
    @Test
    public void testAsyncThreadMonitor() throws InterruptedException {
        AsyncThreadExecutorImplements executor = new AsyncThreadExecutorImplements();
        executor.init();
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("hello");
        };
        executor.execute(runnable);
        TimeUnit.SECONDS.sleep(10);

    }
}
