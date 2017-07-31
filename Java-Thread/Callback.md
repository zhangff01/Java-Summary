用Callback这个接口会实现的多线程会返回线程执行的结果

示例：
```java
package com.souche.finance.price.core.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Create by zhangfeifei on 2017/7/31
 */
public class CallbackTest {

    public static void main(String[] wasd) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Future<String> result = executorService.submit(new TestCallback(i));
            try {
                result.get(100, TimeUnit.MILLISECONDS);//超过0.1s报错
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
                result.cancel(true);//超时取消任务
            }
        }
        executorService.shutdown();
        System.out.println("This is a test log");
    }
}

class TestCallback implements Callable<String> {

    private int number;

    public TestCallback(int number) {
        this.number = number;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Thread is " + this.number);
        return this.number + "";
    }
}
```
