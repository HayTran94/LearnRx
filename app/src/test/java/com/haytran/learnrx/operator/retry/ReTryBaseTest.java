package com.haytran.learnrx.operator.retry;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ReTryBaseTest extends BaseTest {

    @Test
    public void test1() {
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(i -> {
                    if (i == 2) {
                        throw new Exception("test");
                    } else {
                        return i;
                    }
                })
                .retryWhen(new RetryWithDelay(3, 2000))
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
}
