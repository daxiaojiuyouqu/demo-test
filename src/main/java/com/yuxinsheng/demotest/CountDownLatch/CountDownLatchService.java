package com.yuxinsheng.demotest.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchService implements Runnable {

    static final CountDownLatch latch = new CountDownLatch(10);
    static final CountDownLatchService demo = new CountDownLatchService();

    @Override
    public void run() {
        // 模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");

            //计数减一
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i=0; i<10; i++){
            exec.submit(demo);
        }

        // 等待检查
        latch.await();

        // 发射火箭
        System.out.println("Fire!");
        // 关闭线程池
        exec.shutdown();
    }
}
