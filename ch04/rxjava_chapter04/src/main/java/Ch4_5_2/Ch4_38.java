package Ch4_5_2;

import etc.CommonUtils;
import etc.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Ch4_38 {
    public static void main(String[] args){
        String[] data = {"1","7","2","3","4"};
        Observable<String> source = Observable.fromArray(data)
                .delay(100L, TimeUnit.MILLISECONDS);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }
}
