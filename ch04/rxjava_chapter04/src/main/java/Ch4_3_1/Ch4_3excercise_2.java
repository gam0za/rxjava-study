package Ch4_3_1;

import etc.CommonUtils;
import etc.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;

public class Ch4_3excercise_2 {

    public static void main(String[] args){
        Observable<String> source = Observable.zip(
                Observable.just("RED","GREEN","BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value,i)->value
        );

        CommonUtils.exampleStart();
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);

    }



}
