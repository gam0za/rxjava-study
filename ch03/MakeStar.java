package ch03;

import io.reactivex.Observable;
// 별찍기 문제
public class MakeStar {
    public static void main(String[] args){

        // for 문 사용
        Observable<String> stars = Observable.range(1,5)
                .map(row->{
                    String star="";
                    for(int i=0;i<row;i++){
                        star+="*";
                    }
                    return star;
                });

        stars.subscribe(System.out::println);
    }
}
