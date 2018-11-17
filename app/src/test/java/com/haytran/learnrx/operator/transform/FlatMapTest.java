package com.haytran.learnrx.operator.transform;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.schedulers.TestScheduler;


public class FlatMapTest extends BaseTest {
    Integer[] ints1 = new Integer[] {1,2,3,4};
    Integer[] ints2 = new Integer[] {4,5,6,7};
    Integer[] ints3 = new Integer[] {8,9,10,11};

    @Test
    public void test1() {
        Observable<Integer[]> observable = Observable.create((subscriber) -> {
            subscriber.onNext(ints1);
            subscriber.onNext(ints2);
            subscriber.onNext(ints3);
        });
        observable
                .flatMap(new Function<Integer[], ObservableSource<?>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer[] ints) throws Exception {
                        return Observable.fromArray(ints);
                    }
                })
                .subscribe(getObserver());
    }

}
