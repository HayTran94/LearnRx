package com.haytran.learnrx.operator.create;

import com.haytran.learnrx.BaseTest;
import com.haytran.learnrx.Movie;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;


public class DeferTest extends BaseTest {

    @Test
    public void test1() {
        Movie movie = new Movie("Batmen");
        Observable observable = Observable.defer(() -> Observable.just(movie));
        observable.subscribe(getObserver());
    }


    @Test
    public void test2() {
        Movie movie = new Movie("Batmen");
        Observable observable = Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return null;
            }
        });
        observable.subscribe(getObserver());
    }
}
