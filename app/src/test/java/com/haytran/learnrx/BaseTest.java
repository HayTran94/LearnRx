package com.haytran.learnrx;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(20);

    CountDownLatch countDownLatch;

    @Before
    public void setUp() {
        countDownLatch = new CountDownLatch(1);
    }

    public void await() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        countDownLatch.countDown();
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Observer getObserver() {
        return new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe, Disposable = " + d);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext = " + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError " + e);
                stop();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete at thread " + Thread.currentThread().getName());
                stop();
            }
        };
    }

}
