package Ch4_4_3;

import etc.CommonUtils;
import etc.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;

public class Ch4_35 {
    public static void main(String[] args){
        String[] data={"1","2","3","4","5","6"};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),(val,notUsed)->val)
                .skipUntil(Observable.timer(500L,TimeUnit.MILLISECONDS));

        source.subscribe(Log::i);
        CommonUtils.sleep(1000);

    }
}
