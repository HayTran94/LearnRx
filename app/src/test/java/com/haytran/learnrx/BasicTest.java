package com.haytran.learnrx;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BasicTest {

    CountDownLatch countDownLatch;


    @Before
    public void setUp() {
        countDownLatch = new CountDownLatch(1);
    }


    private void await() {
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        countDownLatch.countDown();
    }

    @Test
    public void example1() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(100);
                e.onNext(200);
                e.onNext(300);
                e.onNext(900);
                e.onNext(1000);
                e.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("example1 subscribed to observable - on subscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("example1 - onNext " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("example1 - onError " + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("example1 - on complete");
            }
        };

        observable.subscribe(observer);
    }

    @Test
    public void example2() {
        Observable<String> observable = Observable.just("java", "spring", "hibernate", "android");
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("example2 - onNext value " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("example2 - onComplete");
            }
        });

    }


    @Test
    public void example3() {
        Observable<Long> observable = Observable.intervalRange(1, 30, 200, 500, TimeUnit.MILLISECONDS, Schedulers.computation());
        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("example3 - onNext value " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("example3 - onComplete ");
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
    }


    @Test
    public void example4() {
        Observable<String> observable = Observable.just("java", "spring", "hibernate", "android");
        Observable<String> observableRepeat = observable.repeat(5);
        observableRepeat.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("example4 - onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("example4 - onComplete ");
            }
        });
    }

    @Test
    public void example5() {
        //create observable and subscribe on computation scheduler
        //so main thread will continue execution
        Observable.create(subscriber -> {
            subscriber.onNext("Hello World! - on Thread: " + Thread.currentThread().getId());
            Thread.sleep(500);
            subscriber.onNext("Hello World! after sleep - on Thread:" + Thread.currentThread().getId());
            subscriber.onComplete();
        }).subscribeOn(Schedulers.computation())
                .subscribe(System.out::println);

        //create range observable and subscribe on computation scheduler
        //so main thread will continue execution, but items get emitted on thread created by computation scheduler
        Observable.range(1, 5)
                .subscribeOn(Schedulers.newThread())
                .subscribe(i -> System.out.println("Range of numbers: " + i + " - on thread : " + Thread.currentThread().getId()));

        Observable.create(subscriber -> {
            subscriber.onNext("Hello Universe! - on Thread: " + Thread.currentThread().getId());
            subscriber.onComplete();
        }).subscribe(s -> System.out.println("" + s));

        try {
            System.out.println("main thread waiting for other threads to finish - on Thread : " + Thread.currentThread().getId());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
    }
}


