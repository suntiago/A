package com.suntiago.a;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjunwei
 * @desc 线程处理
 * @date 2019/4/29
 */
public final class ThreadPoolUtil {

    private static ExecutorService cachedThreadPool;
    private static ScheduledExecutorService scheduledThreadPool;
    /**
     * 循环执行的默认初始化时间，第一次执行的初始化时间
     */
    private static int default_delay = 500;


    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     *
     * @param command
     */
    public static Future<String> executeCachedThread(final Runnable command) {
        if (null == cachedThreadPool) {
            cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<>());
        }
        Callable<String> callable = () -> {
            command.run();
            return "true";
        };
        return cachedThreadPool.submit(callable);
    }


    /**
     * 调度型线程池，这个池子里的线程可以按schedule依次delay执行，
     *
     * @param command 执行线程
     * @param delay   延迟时间 ((毫秒 ))
     * @return Future<String>
     */
    public static Future<String> executeScheduledThread(final Runnable command, long delay) {
        if (null == scheduledThreadPool) {
            scheduledThreadPool = new ScheduledThreadPoolExecutor(getDefaultThreadPoolSize(8));
        }
        Callable<String> callable = () -> {
            command.run();
            return "true";
        };
        // 计时单位, 秒：TimeUnit.SECONDS 毫秒：TimeUnit.MILLISECONDS
        return scheduledThreadPool.schedule(callable, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 周期执行
     *
     * @param command 执行线程
     * @param delay   初始化延时 ((毫秒 ))
     * @param period  前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间） ((毫秒 ))
     * @see ** 与releaseThreadPool(scheduledThreadPool)联合使用;<BR/>
     * 在调用此方法的界面必须进行释放,一般使用在 onResume()中启用周期执行，在onPause()中停止循环,
     * <p/>
     * 步骤1、调用此方法得到一个ScheduledExecutorService对象返回值
     * <BR/>
     * 步骤2、在onPause()中调用方法releaseThreadPool(scheduledThreadPool);停止
     * <p/>
     * 可参考ModSpotStyle1Fragment 中使用
     */
    public static ScheduledExecutorService executeScheduleAtFixedRate(Runnable command,
                                                                      long delay, long period) {
        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(1);
        // 计时单位, 秒：TimeUnit.SECONDS 毫秒：TimeUnit.MILLISECONDS
        scheduledThreadPool.scheduleAtFixedRate(command, delay, period,
                TimeUnit.MILLISECONDS);
        return scheduledThreadPool;
    }

    /**
     * @param command
     * @param period  循环执行时间间隔
     * @return
     */
    public static ScheduledExecutorService executeScheduleAtFixedRate(Runnable command, long period) {
        return executeScheduleAtFixedRate(command, default_delay, period);
    }

    /**
     * 停止线程
     */
    public static void releaseThreadPool(Future<String> future) {
        if (null != future) {
            future.cancel(true);
            future = null;
        }
    }


    /**
     * 停止周期执行的pool并且释放
     */
    public static void releaseThreadPool(ScheduledExecutorService scheduledThreadPool) {
        if (null != scheduledThreadPool) {
            scheduledThreadPool.shutdownNow();
        }
    }

    /**
     * 获得默认线程池最大连接数
     *
     * @param max 自定义设置最大连接数
     * @return
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
}

