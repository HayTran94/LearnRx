package com.haytran.learnrx.operator.create;

import com.haytran.learnrx.BaseTest;
import com.haytran.learnrx.Movie;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

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
        observable
                .doOnNext((o) -> System.out.println("doOnNext " + o))
                .doAfterNext((o) -> System.out.println("doAfterNext " + o))
                .doOnComplete(() -> System.out.println("doOnComplete " ))
                .doOnSubscribe((o) -> System.out.println("doOnSubscribe " + o))
                .doOnTerminate(() -> System.out.println("doOnTerminate " ))
                .subscribe(getObserver());
    }

    @Test
    public void test3() {
        int[] ints = new int[]{1, 2, 3, 4};
        Observable observable = Observable.just(ints);
        observable.subscribe(getObserver());
    }

}
