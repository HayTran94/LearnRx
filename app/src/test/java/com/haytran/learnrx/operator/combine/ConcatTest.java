package com.haytran.learnrx.operator.combine;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ConcatTest  extends BaseTest {
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

        Observable observableConcat = Observable.concat(observable1, observable2);

        observableConcat
                .observeOn(Schedulers.newThread())
                .subscribe(getObserver());
        await();
    }

    @Test
    public void test2() {

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

        Observable observableConcat = Observable.concat(observable1, observable2);

        observableConcat
                .observeOn(Schedulers.newThread())
                .subscribe(getObserver());
        await();
    }
}