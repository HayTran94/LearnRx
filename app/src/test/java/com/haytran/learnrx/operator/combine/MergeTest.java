package com.haytran.learnrx.operator.combine;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MergeTest  extends BaseTest{
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

        Observable observableMerge = Observable.merge(observable1, observable2);

        observableMerge
                .observeOn(Schedulers.newThread())
                .subscribe(getObserver());
        await();
    }


    @Test
    public void mergeWithNoDelayError() {

        Observable observable1 = Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("observable1 1");
            Thread.sleep(3000);
            subscriber.onNext("observable1 2");
            subscriber.onNext("observable1 3");
            subscriber.onNext("observable1 4");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("observable2 1");
            Thread.sleep(500);
            subscriber.onNext("observable2 2");
            subscriber.onError(new Exception("Error"));
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io());

        Observable observableMerge = Observable.merge(observable1, observable2);

        observableMerge
                .observeOn(Schedulers.newThread())
                .subscribe(getObserver());
        await();
    }

    @Test
    public void mergeWithDelayError() {

        Observable observable1 = Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("observable1 1");
            Thread.sleep(3000);
            subscriber.onNext("observable1 2");
            subscriber.onNext("observable1 3");
            subscriber.onNext("observable1 4");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create((subscriber) -> {
            System.out.println("observable1 Thread name " + Thread.currentThread().getName());
            subscriber.onNext("observable2 1");
            Thread.sleep(500);
            subscriber.onNext("observable2 2");
            subscriber.onError(new Exception("Error"));
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io());

        Observable observableMerge = Observable.mergeDelayError(observable1, observable2);

        observableMerge
                .observeOn(Schedulers.newThread())
                .subscribe(getObserver());
        await();
    }
}
