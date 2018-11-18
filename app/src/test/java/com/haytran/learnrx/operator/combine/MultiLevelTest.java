package com.haytran.learnrx.operator.combine;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;

public class MultiLevelTest extends BaseTest {

    @Test
    public void test1() {
        Observable<String> observableObservable = Observable.just("Hay");
        observableObservable.subscribe(getObserver());
        await();
    }


    @Test
    public void test2() {
        Observable<Observable<String>> observableObservable = Observable.just(Observable.just("1"));
        observableObservable.subscribe(getObserver());
        await();
    }

    @Test
    public void test3() {
        Observable<Observable<Observable>> observableObservable = Observable.just(Observable.just(Observable.just("1")));
        observableObservable.subscribe(getObserver());
        await();
    }
}
