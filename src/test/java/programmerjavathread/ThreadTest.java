package programmerjavathread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread() {
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread(){
        Runnable runnable = () -> {
            System.out.println("Hello from thread : " + Thread.currentThread().getName());
        };
        //runnable.run();
        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Program Selesai");
   }
   @Test
    void threadSleep() throws InterruptedException{
       Runnable runnable = () -> {
       try {
           Thread.sleep(2000L);
           System.out.println("Hello from thread : " + Thread.currentThread().getName());
       } catch (InterruptedException e) {
           e.printStackTrace();
       }


        };
        //runnable.run();
        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Program Selesai");

        Thread.sleep(3000);
    }
    @Test
    void threadJoin() throws InterruptedException{
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000L);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        };
        //runnable.run();
        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Program menunggu");
        thread.join();
        System.out.println("Program Selesai");

    }
    @Test
    void threadInterrupt() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++){
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

        };
        //runnable.run();
        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println("Program menunggu");
        thread.join();
        System.out.println("Program Selesai");

    }
    @Test
    void threadInterruptCoorect() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++){
                if(Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    return;
                }

            }

        };
        //runnable.run();
        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println("Program menunggu");
        thread.join();
        System.out.println("Program Selesai");

    }

    @Test
    void threadName() {
        var thread = new Thread(()->{
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Eko");
        thread.start();
    }
    @Test
    void threadState() throws InterruptedException {
        var thread = new Thread(()->{
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Eko");
        System.out.println(thread.getState());

        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
