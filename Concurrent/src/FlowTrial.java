import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

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
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSubscribe~~");

                System.out.println("subscription is "+ subscription);
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

        publisher.submit("ok");
        publisher.subscribe(stringSubscriber);



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

                System.out.println("subscription is "+ subscription);
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
            @Override
            public void subscribe(Flow.Subscriber<? super String> subscriber) {
                System.out.println("~~" + getClass().getSimpleName() + ".subscribe~~");

                System.out.println("subscriber is " + subscriber);



            }
        };


        publisher.subscribe(stringSubscriber);
    }
}
