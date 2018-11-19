package com.haytran.learnrx.operator.combine;

import android.util.Log;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SequenceTaskTest extends BaseTest{

    @Test
    public void test1() {
        getSingle().flatMapCompletable((str) -> {
            System.out.println("flatMapCompletable " + str);
            return getCompletable();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(getCompletableObserver());
        await();
    }

    private Single getSingle() {
        return Single.create((sub) -> {
            System.out.println("getSingle create " + Thread.currentThread().getName());
            sub.onSuccess("hay");
        });
    }

    private Completable getCompletable() {
        return Completable.create((sub) -> {
            sub.onComplete();
        });
    }

    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe thread " + Thread.currentThread().getName());
                System.out.println("onSubscribe " + d);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete thread " + Thread.currentThread().getName());
                stop();
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError " + e);
            }
        };
    }
}
