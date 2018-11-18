package com.haytran.learnrx.operator.combine;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class SwitchOnNext extends BaseTest{

    @Test
    public void test0() {
        Observable observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(ticks -> Observable
                        .interval(150, TimeUnit.MILLISECONDS)
                        .map(innerInterval -> "outer: " + ticks + "- inner " + innerInterval));
        observable.subscribe(getObserver());
        await();
    }


    @Test
    public void test1() {
        Observable observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(ticks -> Observable
                        .interval(150, TimeUnit.MILLISECONDS)
                        .map(innerInterval -> "outer: " + ticks + "- inner " + innerInterval));
        Observable.switchOnNext(observable).subscribe(getObserver());
        await();
    }

}
