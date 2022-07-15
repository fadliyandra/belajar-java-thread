package programmerjavathread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlokingQueueTest {
    @Test
    void arrayBlokingQueue() throws InterruptedException {
        final var queue = new ArrayBlockingQueue<String>(5);
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0;i<10;i++){
            executor.execute(()->{
                try {
                    queue.put("Data");
                    System.out.println("Finish Put Data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("Recibe data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void LinkedBlockingQueue() throws InterruptedException {
        final var queue = new LinkedBlockingQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0;i<10;i++){
            executor.execute(()->{
                try {
                    queue.put("Data");
                    System.out.println("Finish Put Data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("Recibe data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void priorityBlokingQueue() throws InterruptedException {
        final var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0;i<10;i++){
            final var index = i;
            executor.execute(()->{
                queue.put(index);
                System.out.println("Finish Put Data");
            });
        }

        executor.execute(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("Recibe data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void delayedQueue() throws InterruptedException {
        final var queue = new DelayQueue<ScheduledFuture<String>>();

        final var executor = Executors.newFixedThreadPool(20);
        final var executorScheduled = Executors.newFixedThreadPool(10);

        for (int i = 0;i<10;i++){
            final var index = i;
          //  queue.put(executorScheduled.);
        }

        executor.execute(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("Recibe data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void synchronousQueue() throws InterruptedException {
        final var queue = new SynchronousQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0;i<10;i++){
            final var index = i;
            executor.execute(()->{
                try {
                    queue.put("Data-" + index);
                    System.out.println("Finish Put Data : " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        executor.execute(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("Recibe data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void blokingDeque() throws InterruptedException {
        final var queue = new LinkedBlockingQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0;i<10;i++){
            final var index = i;
            executor.execute(()->{
                try {
                    queue.put("Data-" + index);
                    System.out.println("Finish Put Data : " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        executor.execute(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("Recibe data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
