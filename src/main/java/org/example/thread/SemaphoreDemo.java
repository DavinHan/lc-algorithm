package org.example.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanhanf
 * @date 2024/8/12
 * @since 1.0
 */
public class SemaphoreDemo {

    private void fun1() {
        Semaphore semaphore = new Semaphore(3, true);
        ReentrantLock lock = new ReentrantLock();
        new Thread(new MyRunnable1(1, semaphore, lock), "Thread A1").start();
        new Thread(new MyRunnable1(2, semaphore, lock), "Thread B1").start();
        new Thread(new MyRunnable1(1, semaphore, lock), "Thread C1").start();

    }

    class MyRunnable1 implements Runnable {
        private int n;
        private Semaphore semaphore;
        private ReentrantLock lock;

        public MyRunnable1(int n, Semaphore semaphore, ReentrantLock lock) {
            this.n = n;
            this.lock = lock;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(n);
                /**
                 * 死锁
                 * 因为 drainPermit是先获取剩余的全部许可证，再使用
                 */
                System.out.println("drain permit: " + semaphore.drainPermits());
                System.out.println(Thread.currentThread().getName() + " have resolved...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(n);
            }
        }
    }

    private void fun2() {
        Semaphore semaphore = new Semaphore(3, true);
        ReentrantLock lock = new ReentrantLock();
        new Thread(new MyRunnable2(1, semaphore, lock), "Thread A2").start();
        new Thread(new MyRunnable2(2, semaphore, lock), "Thread B2").start();
        new Thread(new MyRunnable2(1, semaphore, lock), "Thread C2").start();

    }

    class MyRunnable2 implements Runnable {
        private int n;
        private Semaphore semaphore;
        private ReentrantLock lock;

        public MyRunnable2(int n, Semaphore semaphore, ReentrantLock lock) {
            this.n = n;
            this.lock = lock;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(n);
                System.out.println("available permit: " + semaphore.availablePermits());
                System.out.println(Thread.currentThread().getName() + " have resolved...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(n);
            }
        }
    }

    private void fun3() {
        Semaphore semaphore = new Semaphore(3, true);
        System.out.println("There is 3 computers in Internet Bar.");
        Thread a = new Thread(new MyRunnable3(1, semaphore), "YY");
        Thread b = new Thread(new MyRunnable3(2, semaphore), "Bo Liu, Top Dragon");
        Thread c = new Thread(new MyRunnable3(1, semaphore), "Wei Zhang");

        a.start();
        b.start();
        c.start();

        b.interrupt();
    }

    class MyRunnable3 implements Runnable {
        private int n;
        private Semaphore semaphore;

        public MyRunnable3(int n, Semaphore semaphore) {
            this.n = n;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                if (semaphore.availablePermits() < n) {
                    System.out.println(Thread.currentThread().getName() + " goes to Internet Bar, and he/they will wait.");
                }
                semaphore.acquire(n);
                System.out.println(Thread.currentThread().getName() + " get a good seats, and left " + semaphore.availablePermits() + ".");
                //模拟上网时长
                int internetBarTime = ThreadLocalRandom.current().nextInt(1, 6);
                TimeUnit.SECONDS.sleep(internetBarTime);
                System.out.println(Thread.currentThread().getName() + " cost all money to spend " + internetBarTime + " hours on the Internet.");
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " was persuaded to go by webmaster.");
            } finally {
                semaphore.release(n);
                System.out.println(Thread.currentThread().getName() + " has went, and left " + semaphore.availablePermits() + ".");
            }
        }
    }


    private void fun4() {
        Semaphore semaphore = new Semaphore(3, true);
        System.out.println("There is 3 computers in Internet Bar.");
        Thread a = new Thread(new MyRunnable4(1, semaphore), "YY");
        Thread b = new Thread(new MyRunnable4(2, semaphore), "Bo Liu, Top Dragon");
        Thread c = new Thread(new MyRunnable4(1, semaphore), "Wei Zhang");

        a.start();
        b.start();
        c.start();

        b.interrupt();
    }

    class MyRunnable4 implements Runnable {
        private int n;
        private Semaphore semaphore;

        public MyRunnable4(int n, Semaphore semaphore) {
            this.n = n;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            if (semaphore.availablePermits() < n) {
                System.out.println(Thread.currentThread().getName() + " goes to Internet Bar, and he/they will wait.");
            }


            try {
                semaphore.acquire(n);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " was persuaded to go by webmaster.");
                return;
            }

            try {
                System.out.println(Thread.currentThread().getName() + " get a good seats, and left " + semaphore.availablePermits() + ".");
                //模拟上网时长
                int internetBarTime = ThreadLocalRandom.current().nextInt(1, 6);
                TimeUnit.SECONDS.sleep(internetBarTime);
                System.out.println(Thread.currentThread().getName() + " cost all money to spend " + internetBarTime + " hours on the Internet.");
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " was persuaded to go by webmaster.");
            } finally {
                semaphore.release(n);
                System.out.println(Thread.currentThread().getName() + " has went, and left " + semaphore.availablePermits() + ".");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo s = new SemaphoreDemo();
        s.fun4();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("===========================");
        s.fun3();
//        s.fun2();
//        System.out.println("===========================");
//        s.fun1();
    }
}
