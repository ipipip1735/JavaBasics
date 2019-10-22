import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.LongStream;

/**
 * Created by Administrator on 2019/10/22 14:10.
 */
public class FlowTrial {
    public static void main(String[] args) {


//        custom();

        SubmissionPublisherTrial();


    }

    private static void SubmissionPublisherTrial() {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        Flow.Subscriber<String> stringSubscriber = new Flow.Subscriber<>() {

            Flow.Subscription subscription;
            private int n = 5;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSubscribe~~");

                System.out.println("subscription is " + subscription);

                this.subscription = subscription;
                subscription.request(n);

            }

            @Override
            public void onNext(String item) {
                System.out.println("~~" + getClass().getSimpleName() + ".onNext~~");
                System.out.println("get " + n);

                if (n-- == 1) {
                    n = 5;
                    subscription.request(n);
                    System.out.println("request " + n);
                }

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("~~" + getClass().getSimpleName() + ".onError~~");

            }

            @Override
            public void onComplete() {
                System.out.println("~~" + getClass().getSimpleName() + ".onComplete~~");

            }
        };

        publisher.subscribe(stringSubscriber);

        for (int i = 0; i < 15; i++) {
            publisher.submit("ok");
        }


        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void custom() {
        Flow.Subscriber<String> stringSubscriber = new Flow.Subscriber<>() {

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSubscribe~~");
                System.out.println("subscription is " + subscription);

                subscription.request(5);
            }

            @Override
            public void onNext(String item) {
                System.out.println("~~" + getClass().getSimpleName() + ".onNext~~");

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("~~" + getClass().getSimpleName() + ".onError~~");

            }

            @Override
            public void onComplete() {
                System.out.println("~~" + getClass().getSimpleName() + ".onComplete~~");

            }
        };


        Flow.Publisher<String> publisher = new Flow.Publisher<String>() {

            List list = new ArrayList<>();

            @Override
            public void subscribe(Flow.Subscriber<? super String> subscriber) {
                System.out.println("~~" + getClass().getSimpleName() + ".subscribe~~");
                System.out.println("subscriber is " + subscriber);


                if (list.contains(subscriber))
                    subscriber.onError(new IllegalStateException());
                else {

                    list.add(subscriber);
                    Flow.Subscription subscription = new Flow.Subscription() {
                        @Override
                        public void request(long n) {
                            System.out.println("~~" + getClass().getSimpleName() + ".request~~");
                            System.out.println("n is " + n);
                        }

                        @Override
                        public void cancel() {
                            System.out.println("~~" + getClass().getSimpleName() + ".cancel~~");

                        }
                    };

                    subscriber.onSubscribe(subscription);

                }


            }
        };


        publisher.subscribe(stringSubscriber);
    }
}
