怎么使用CountDownLatch

我的理解 使某一线程在一定数量的线程执行完之后再执行
```java
import java.util.concurrent.CountDownLatch;

/**
 * Create by zhangfeifei on 2017/7/14
 */
public class Test {

    private static final CountDownLatch count = new CountDownLatch(2);

    public static void main(String[] agrs) {

        Thread delayThread = new Thread(new DelayThread(1,count));
        Thread sample1 = new Thread(new Sample(1,count));
        Thread sample2 = new Thread(new Sample(2,count));
        delayThread.start();
        sample1.start();
        sample2.start();
    }
}

class Sample implements Runnable {

    private int i;
    private CountDownLatch count;

    public Sample(int i, CountDownLatch count) {
        this.i = i;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("thread " + i + " run");
        count.countDown();
    }
}

class DelayThread implements Runnable {

    private int i;
    private CountDownLatch count;

    public DelayThread(int i, CountDownLatch count) {
        this.i = i;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            count.await();
            System.out.println("delay thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
执行结果
```javasecript
thread 1 run
thread 2 run
delay thread run
```
