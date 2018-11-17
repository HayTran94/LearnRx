package com.haytran.learnrx.operator.create;

import com.haytran.learnrx.BaseTest;
import com.haytran.learnrx.Movie;

import org.junit.Test;

import io.reactivex.Observable;

public class JustTest extends BaseTest {

    @Test
    public void test1() {
        Movie movie = new Movie("Batmen");
        Observable observable = Observable.just(movie);
        movie = new Movie("Avenger");
        observable.subscribe(getObserver());
    }

    @Test
    public void test2() {
        Observable observable = Observable.just(1, 2, 3, 4);
        observable.subscribe(getObserver());
    }

    @Test
    public void test3() {
        int[] ints = new int[]{1,2,3,4};
        Observable observable = Observable.just(ints);
        observable.subscribe(getObserver());
    }

}
