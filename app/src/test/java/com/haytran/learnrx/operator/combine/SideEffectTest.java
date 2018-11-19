package com.haytran.learnrx.operator.combine;

import android.util.Log;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SideEffectTest extends BaseTest {

    @Test
    public void test1() {
        Observable.just(1, 2, 3, 4)
                .doOnSubscribe((d) -> System.out.println("doOnSubscribe: " + d + ", Thread " + Thread.currentThread().getName()))
                .doOnNext((str) -> System.out.println("doOnNext: " + str + ", Thread " + Thread.currentThread().getName()))
                .doOnComplete(() -> System.out.println("doOnComplete: " + ", Thread " + Thread.currentThread().getName()))
                .doOnTerminate(() -> {
                    System.out.println("doOnTerminate: " + ", Thread " + Thread.currentThread().getName());
                    stop();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + ", Thread " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete: " + ", Thread " + Thread.currentThread().getName());
                    }
                });
        await();
    }
}
