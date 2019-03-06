package ch03;

import io.reactivex.Observable;

public class filterExample1 {
    public static void main(String[] args){

        String[] people = {"영규 남자","민주 여자","진영 여자", "수정 여자"};

        Observable<String> source = Observable.fromArray(people)
                .filter(female->female.endsWith("여자"))
                .map(female->female.replace("여자",""));
        source.subscribe(System.out::print);

    }
}
