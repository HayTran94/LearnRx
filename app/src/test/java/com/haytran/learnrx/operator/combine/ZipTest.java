package com.haytran.learnrx.operator.combine;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ZipTest  extends BaseTest {
    @Test
    public void test1() {

        Observable observable1 = Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("observable1 1");
            Thread.sleep(3000);
            subscriber.onNext("observable1 2");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("observable2 1");
            Thread.sleep(500);
            subscriber.onNext("observable2 2");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io());

        Observable observableMerge = Observable.zip(observable1, observable2, (first, last) -> first + " " + last);

        observableMerge
                .observeOn(Schedulers.newThread())
                .subscribe(getObserver());
        await();
    }
}