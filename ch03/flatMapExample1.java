package ch03;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class flatMapExample1 {
    public static void main(String[] args){
        Function<String, Observable<String>> getDoubleDiamonds =
                person->Observable.just(person+"<>",person+"<>");

        String[] people = {"영규","민주","수정","진영"};
        Observable<String> source = Observable.fromArray(people)
                .flatMap(getDoubleDiamonds);
        source.subscribe(System.out::println);

    }
}
