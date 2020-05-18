package yuntongxun.ytx.fast.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 线程池
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017/9/16 22:20
 */
public class YTXThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
//        showThreadPoolInfo("1. do execute");
        super.execute(task);
        showThreadPoolInfo();
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
//        showThreadPoolInfo("2. do execute");
        super.execute(task, startTimeout);
        showThreadPoolInfo();
    }

    @Override
    public Future<?> submit(Runnable task) {
//        showThreadPoolInfo("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
//        showThreadPoolInfo("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
//        showThreadPoolInfo("1. do submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(task);
    }

    private void showThreadPoolInfo(){
        logger.info("当前活动线程数："+ this.getActiveCount());
        logger.info("核心线程数："+ this.getCorePoolSize());
        logger.info("总线程数："+ this.getPoolSize());
        logger.info("最大线程池数量"+this.getMaxPoolSize());
        logger.info("线程处理队列长度"+this.getThreadPoolExecutor().getQueue().size());
    }

}
