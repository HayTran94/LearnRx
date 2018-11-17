package com.haytran.learnrx.operator.create;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CreateTest extends BaseTest {
    @Test
    public void test1() {
        Observable<String> observable = Observable.create((e)-> {
            for (int i = 0 ; i < 10000000; i ++) {
                e.onNext(i + "");
            }
            e.onComplete();
        });

        observable.subscribe(getObserver());
    }

    @Test
    public void test2() {
        Observable<String> observable = Observable.create((e)-> {
            for (int i = 0 ; i < 10; i ++) {
                e.onNext(i + "");
                if (i == 5) {
                    e.onError(new IllegalAccessError("i = 5"));
                }
            }
            e.onComplete();
        });

        observable.subscribe(getObserver());
    }


    @Test
    public void test3() {
        Observable<String> observable = Observable.create((e)-> {
            for (int i = 0 ; i < 10; i ++) {
                e.onNext(i + "");
                if (i == 5) {
                    e.onError(new IllegalAccessError("i = 5"));
                }
            }
            e.onComplete();
        });

        observable.subscribe(getObserver());
    }


    @Test(timeout = 10000)
    public void test4() {
        Observable<String> observable = Observable.create((e)-> {
            for (int i = 0 ; i < 10; i ++) {
                e.onNext(i + "");
                if (i == 5) {
                    e.onError(new IllegalAccessError("i = 5"));
                }
            }
            e.onComplete();
        });

        observable.subscribe(getObserver());
    }
}
