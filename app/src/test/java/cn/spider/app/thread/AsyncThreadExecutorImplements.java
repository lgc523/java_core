package cn.spider.app.thread;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Slf4j
public class AsyncThreadExecutorImplements implements AutoCloseable {

    private static final int DEFAULT_QUEUE_SIZE = 1000;
    private static final int DEFAULT_POOL_SIZE = 10;
    @Setter
    private int queueSize = DEFAULT_QUEUE_SIZE;
    @Setter
    private int poolSize = DEFAULT_POOL_SIZE;


    //周期性监控线程池运行状态

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(
            new BasicThreadFactory.Builder().namingPattern("async thread executor monitor").build());

    //定义线程池

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSize, poolSize,
            0, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(queueSize),
            new BasicThreadFactory.Builder().namingPattern("async-thread-%d").build(),
            (r, executor) -> log.info("the async executor pool is full!!!"));


    private final ExecutorService executorService = threadPoolExecutor;

    @PostConstruct
    public void init() {
        System.out.println("init");
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long taskCount = threadPoolExecutor.getTaskCount();
            long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            long largestPoolSize = threadPoolExecutor.getLargestPoolSize();
            long poolSize = threadPoolExecutor.getPoolSize();
            long activeCount = threadPoolExecutor.getActiveCount();
            log.info("async-executor monitor.taskCount:{},completedTaskCount:{},largestPoolSize:{},poolSize:{},activeCount:{}",
                    taskCount, completedTaskCount, largestPoolSize, poolSize, activeCount);

        }, 0, 3, TimeUnit.SECONDS);

    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }

    @Override
    public void close() {
        executorService.shutdown();
    }
}
