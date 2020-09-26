package com.cz;

/**
 * @author : zhang.cao
 * @date : 2020/9/22 19:47
 */
public class MyLockTest {

    private static Integer tag = 0;

    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        Thread thread1 = new Thread(new Thread1(myLock));
        Thread thread2 = new Thread(new Thread1(myLock));
        thread1.start();
        thread2.start();
    }


    static class Thread1 implements Runnable {
        private MyLock myLock;

        public Thread1(MyLock myLock) {
            this.myLock = myLock;
        }

        @Override
        public void run() {
            boolean b = myLock.tryAcquire(1);
            if(!b){
                return;
            }
            for (int i = 0; i < 100000; i++) {
                tag++;
            }
            myLock.unlock();
            System.out.println(tag);
        }
    }

}
