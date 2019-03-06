package ch03;

import io.reactivex.Observable;
import io.reactivex.Single;

public class filterExample2 {
    public static void main(String[] args){
        Integer[] numbers = {100, 200, 300, 400, 500};
        Single<Integer> single;
        Observable<Integer> source;

        // 1. first
        single = Observable.fromArray(numbers).first(-1);
        single.subscribe(data->System.out.println("first()함수: "+data));
        System.out.println();

        // 2. last
        single = Observable.fromArray(numbers).last(999);
        single.subscribe(data->System.out.println("last()함수: "+data));
        System.out.println();

        // 3. take(N)
        source = Observable.fromArray(numbers).take(3);
        source.subscribe(data->System.out.println("take(3)함수: "+data));
        System.out.println();

        // 4. takeLast(N)
        source = Observable.fromArray(numbers).takeLast(3);
        source.subscribe(data->System.out.println("takeLast(3)함수: "+data));
        System.out.println();

        // 5. skip(N)
        source = Observable.fromArray(numbers).skip(2);
        source.subscribe(data->System.out.println("skip(2)함수: "+data));
        System.out.println();

        // 6. skipLast(N)
        source = Observable.fromArray(numbers).skipLast(2);
        source.subscribe(data->System.out.println("skipLast(2)함수: "+data));
    }
}
