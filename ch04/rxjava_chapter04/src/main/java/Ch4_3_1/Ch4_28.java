package Ch4_3_1;

import etc.Log;
import io.reactivex.Observable;

public class Ch4_28 {
    public static void main(String[] args){
        Observable<Integer> source = Observable.zip( //Chapter4.3 실습과제1번 응용
                Observable.just(100,200,300),
                Observable.just(10,20,30),
                (a,b)->a+b) //요기까지 110,220,330이 만들어짐
                .zipWith(Observable.just(1,2,3),
                        (ab,c)->ab+c); //이미 결합해놓은 것에 zipWith()함수를 적용시켜 1,2,3 Observable을 만들고 이전 결합에 다시 결합함
                                        // 그결과 111,222,333 이 된다.
        source.subscribe(Log::i);


    }
}
