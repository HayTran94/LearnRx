package com.haytran.learnrx;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

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
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete ");
            }
        };
    }

}