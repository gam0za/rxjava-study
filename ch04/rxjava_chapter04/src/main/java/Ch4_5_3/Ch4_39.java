package Ch4_5_3;

import etc.CommonUtils;
import etc.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

import java.util.concurrent.TimeUnit;

public class Ch4_39 {
    public static void main(String[] main){
        String[] data = {"1","3","7"};

        CommonUtils.exampleStart();
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item->{
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }
}
