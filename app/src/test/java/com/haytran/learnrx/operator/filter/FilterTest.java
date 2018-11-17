package com.haytran.learnrx.operator.filter;

import com.haytran.learnrx.BaseTest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;

public class FilterTest extends BaseTest {

    @Test
    public void test1() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0 ; i < 10000000; i ++) {
                    e.onNext(i );
                }
                e.onComplete();
            }
        });
        observable.filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(getObserver());

    }


}
