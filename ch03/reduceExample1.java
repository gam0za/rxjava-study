package ch03;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class reduceExample1 {
    public static void main(String[] args){
        String[] balls = {"1","3","5"};
        Maybe<String>source = Observable.fromArray(balls)
                .reduce((ball1,ball2)->ball2+"("+ball1+")");
        source.subscribe(System.out::println);

        // BiFunction 이용
        BiFunction<String, String, String> mergeBalls =
                (ball1, ball2)-> ball2+"("+ball1+")";
        Maybe<String>source2 = Observable.fromArray(balls)
                .reduce(mergeBalls);
        source2.subscribe(System.out::println);
    }
}
