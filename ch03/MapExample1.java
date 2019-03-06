package ch03;

import io.reactivex.Observable;

public class MapExample1 {
    public static void main(String[] args){

        String[] people = {"영규","민주","수정","진영"};

        Observable<String> source = Observable.fromArray(people)
                .map(person->person+"<>");
        source.subscribe(System.out::println);
    }
}
