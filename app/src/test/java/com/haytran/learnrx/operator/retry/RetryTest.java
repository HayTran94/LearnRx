package com.haytran.learnrx.operator.retry;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RetryTest extends BaseTest {

    @Test
    public void test1() {
        Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("value 1");
            Thread.sleep(1000);
            subscriber.onNext("value 2");
            Thread.sleep(1000);
            subscriber.onError(new Exception("Test"));
            Thread.sleep(1000);
            subscriber.onNext("value 3");
            subscriber.onComplete();
        }).retryWhen(attempts -> attempts.delay(3, TimeUnit.SECONDS))
                .subscribe(getObserver());
        await();
    }

    @Test
    public void test2() {
//        Observable<Integer> observable = Observable.create((subscriber) -> {
//            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
//            subscriber.onNext(1);
//            Thread.sleep(1000);
//            subscriber.onNext(2);
//            Thread.sleep(1000);
//            subscriber.onNext(3);
//            Thread.sleep(1000);
//            subscriber.onError(new Exception("Test"));
//            Thread.sleep(1000);
//            subscriber.onNext(4);
//            subscriber.onComplete();
//        });

        Observable.interval(1, TimeUnit.SECONDS)
                .map(i -> {
                    if (i == 2) {
                        throw new Exception("test");
                    } else {
                        return i;
                    }
                })
                .takeUntil(a -> a >= 3)
                .retry(2)
                .subscribe(getObserver());

        await();
    }
}
