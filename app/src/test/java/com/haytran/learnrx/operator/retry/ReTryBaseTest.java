package com.haytran.learnrx.operator.retry;

import android.util.Log;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ReTryBaseTest extends BaseTest {

    @Test
    public void test1() {
        int maxCount = 3;
        int interval = 5;
        Observable.create(source -> {
            System.out.println(BaseTest.getHeaderLog() + "create");
            source.onError(new Exception("Exki"));
        })
                .retryWhen(errors -> errors
                        .zipWith(Observable.range(1, maxCount + 1), (n, i) -> i)
                        .flatMap(retryCount -> {
                            System.out.println(BaseTest.getHeaderLog() + "flatMap retryCount = " + retryCount);
                            if (retryCount > maxCount) {
                                throw new Exception("Timeout");
                            } else {
                                return Observable.timer(interval, TimeUnit.SECONDS);
                            }
                        }))
                .subscribe(getObserver());

        await();
    }

    @Test
    public void test2() {
        Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("value 1");
            Thread.sleep(1000);
            subscriber.onNext("value 2");
            Thread.sleep(1000);
            subscriber.onComplete();
        }).repeat(3).subscribe(getObserver());

        await();
    }

    @Test
    public void test3() {
        Observable.interval(1, TimeUnit.SECONDS).take(5).subscribe(getObserver());

        await();
    }

    @Test
    public void test4() {
        Observable.just(1).flatMap(i -> Observable.timer(3, TimeUnit.SECONDS)).subscribe(getObserver());
        await();
    }

    @Test
    public void test5() {
    }
}
