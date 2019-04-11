package Ch4_3_1;

import etc.Log;
import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;

import static java.lang.Float.max;
import static java.lang.Float.min;

public class Ch4_3exercise_4 {
    public static void main(String[] args){
        String[] data = {
                "100",
                "300"
        };

        Observable<Integer> basePirce = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val->{
                    if(val <= 200) return 910;
                    if(val <= 400) return 1600;
                    return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val->{
                    double series1 = min(200, val) * 93.3;
                    double series2 = min(200, max(val-200,0)) * 187.9;
                    double series3 = min(0, max(val-400,0)) * 280.65;
                    return (int)(series1+series2+series3);
                });

        //3번에서 쓰인부분
//        Observable<Integer> source = Observable.zip(
//                basePirce,
//                usagePrice,
//                (v1,v2)->v1+v2
//        );


        //3번에서 쓰인 부분으로 저 index가 문제가 된다.
//        source.map(val->
//                new DecimalFormat("#,###").format(val))
//                .subscribe(val->{
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("Usage: "+data[index] +" kWh => ");//문제가되는부분
//                    sb.append("Price: " + val +"원");
//                    Log.i(sb.toString());
//                    index++//문제가 되는 부분
//                });
        /**
         * 위의 문제를 해결할 3가지 힌트
         * 1.data를 추가로 넘겨주는 방법을 고민하자.
         * 2.zip()함수는 2개뿐만 아니라 3개의 Observable도 결합할수있다.
         * 3.새로운 클래스를 사용하는 것 봗 앞서 3.4에서 소개한 아파치 커먼즈의 Pair클래스를 사용하자.
         */

        //4번

        Observable<Pair<String,Integer>> source = Observable.zip(
                basePirce,
                usagePrice,
                Observable.fromArray(data),
                (v1,v2,i)->Pair.of(i,v1+v2)
        );

        source.map(val->Pair.of(val.getLeft(),
                new DecimalFormat("#,###").format(val.getRight())))
                .subscribe(val->{
                    StringBuilder sb = new StringBuilder();
                    sb.append("Usage: "+val.getLeft() +" kWh => ");
                    sb.append("Price: " + val.getRight() +"원");
                    Log.i(sb.toString());
                });



    }
}
