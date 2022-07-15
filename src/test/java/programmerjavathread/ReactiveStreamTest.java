package programmerjavathread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveStreamTest {

    @Test
    void publish() throws InterruptedException {
        var publisher = new SubmissionPublisher<String>();
        var subscriber = new PrintSubscriber();
        publisher.subscribe(subscriber);


        var executor = Executors.newFixedThreadPool(10);
        executor.execute(()->{
            for (int i = 0; i < 100; i++) {

            publisher.submit("Eko" + i);
            System.out.println("Send Eko-" + i);
            }
        });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void processor() {
        var publisher = new SubmissionPublisher<String>();
        var processors = new HelloProcessor();
        publisher.subscribe(processors);

       // var subcriber = new PrintSubscriber("A", 1000L)
    }

    public static class PrintSubscriber implements Flow.Subscriber<String>{

        private Flow.Subscription subscription;


        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);


        }

        @Override
        public void onNext(String item) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " : " + item);
                this.subscription.request(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();

        }

        @Override
        public void onComplete() {
            System.out.println(Thread.currentThread().getName() + " : DONE");

        }
    }
    public static class HelloProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String>{

        private Flow.Subscription subscription;
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);

        }

        @Override
        public void onNext(String item) {
            var value = "Hello " + item;
            submit(value);
            this.subscription.request(1);

        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            close();

        }
    }

}
