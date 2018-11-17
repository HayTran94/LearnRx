package com.haytran.learnrx.operator.transform;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;

public class MapTest extends BaseTest {

    @Test
    public void test1() {
        Observable<Integer> observable = Observable.create((subscriber) -> {
            subscriber.onNext(1);
            subscriber.onNext(4);
            subscriber.onNext(2);
            subscriber.onNext(5);
            subscriber.onNext(3);
        });
        observable.map((integer) -> {
             switch (integer) {
                 case 1:
                     return "Một";
                 case 2:
                     return "Hai";
                 case 3:
                     return "Ba";
                 default:
                     return "Nhiều";
             }
        }).subscribe(getObserver());
    }
}
