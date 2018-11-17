package com.haytran.learnrx.operator.combine;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CombineLatestTest extends BaseTest {
    @Test
    public void test1() {
        Observable observable1 = Observable.interval(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io());
        Observable observable2 = Observable.interval(50, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io());
        Observable observableCombineLatest = Observable.combineLatest(observable1, observable2, (first, last) -> first + " " + last);
        observableCombineLatest.subscribe(getObserver());
        await();
    }
}
