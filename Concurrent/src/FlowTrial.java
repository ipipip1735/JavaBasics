import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2019/10/22 14:10.
 */
public class FlowTrial {
    public static void main(String[] args) {

        FlowTrial flowTrial = new FlowTrial();

//        flowTrial.custom();//自定义发布者
        flowTrial.SubmissionPublisherTrial();//使用JDK自带的默认发布者


    }

    private void SubmissionPublisherTrial() {

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

                System.out.println("item is " + item);
                n--;

                if (n == 1) {
                    n = 5;
                    subscription.request(n);
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
        Stream.of("aa", "bb", "cc").forEach(publisher::submit);

        System.out.println("ok");



        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void custom() {

        Subscriber Subscriber1 = new Subscriber("one");
        Subscriber Subscriber2 = new Subscriber("two");

        Publish publish = new Publish();
        publish.subscribe(Subscriber1);
        publish.subscribe(Subscriber2);

        Stream.of("aa", "bb", "cc").forEach(publish::dispatch);

    }


    public class Subscriber implements Flow.Subscriber<String> {
        String tag;

        public Subscriber(String tag) {
            this.tag = tag;
        }

        @Override
        public void onSubscribe(java.util.concurrent.Flow.Subscription subscription) {
            System.out.println("~~" + getClass().getSimpleName() + ".onSubscribe~~");
            System.out.println("subscription is " + subscription);
        }

        @Override
        public void onNext(String item) {
            System.out.println("~~" + getClass().getSimpleName() + tag + ".onNext~~");
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("~~" + getClass().getSimpleName() + tag + ".onError~~");
        }

        @Override
        public void onComplete() {
            System.out.println("~~" + getClass().getSimpleName() + tag + ".onComplete~~");
        }
    };



    public class Publish implements Flow.Publisher<String>{
        List<Flow.Subscriber> list = new ArrayList<>();

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


        public void dispatch(String item) {

            for (Flow.Subscriber subscriber : list) subscriber.onNext(item);
        }

    }



}
