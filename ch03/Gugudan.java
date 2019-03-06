package ch03;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.Scanner;

public class Gugudan {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("구구단 입력: ");
        int dan= Integer.parseInt(sc.nextLine());

        // Step 1. for문 -> Observable 로 변환
        System.out.println("********************* Step 1. ************************");
        Observable<Integer> source = Observable.range(1,9);
        source.subscribe(row->System.out.println(dan+"*"+row+"="+dan*row));


        // Step 2. 사용자 함수 정의하기
        System.out.println("********************* Step 2. ************************");
        Function<Integer,Observable<String>> gugudan = num->
                Observable.range(1,9).map(row->num+"*"+row+"="+dan*row); // num 대신 dan 을 쓰면, 자바 컴파일러가 사용자 입력으로 받는 변수와 혼동!
        Observable<String> source2 = Observable.just(dan).flatMap(gugudan);
        source2.subscribe(System.out::println);

        // Step 3. flatMap() 함수를 좀 더 활용해보기
        System.out.println("********************* Step 3. ************************");
        Observable<String>source3 = Observable.just(dan)
                .flatMap(num->Observable.range(1,9)
                .map(row->num+"*"+row+"="+dan*row));
        source3.subscribe(System.out::println);

        // Step 4. flatMap() 심화 버전
        System.out.println("********************* Step 4. ************************");
        Observable<String> source4 = Observable.just(dan)
                .flatMap(gugu->Observable.range(1,9),
                        (gugu,i)->gugu+"*"+i+"="+gugu*i);
        source4.subscribe(System.out::println);

        sc.close();
    }
}
