package ch03;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapExample2 {
    public static void main(String[] args){

        // 1. 입력 타입과 반환 타입이 같은 경우
        Function<String, String> getDiamond = person -> person+"<>"; // String을 받아 String으로 반환 <String, String>
        String[] people = {"영규","민주","수정","진영"};

        Observable<String> source = Observable.fromArray(people)
                .map(getDiamond);
        source.subscribe(System.out::println);

        // 2. 입력 타입과 반환 타입이 다른 경우
        Function<String, Integer> getAge = person->{
            switch (person){
                case "영규": return 26;
                case "민주": return 25;
                case "진영": return 25;
                case "수정": return 23;
                default: return -1;
            }
        };

        Observable<Integer> source2 = Observable.fromArray(people)
                .map(getAge); // 명시적인 타입 변환 없이 바로 사용 가능
        source2.subscribe(System.out::println);
    }

}
