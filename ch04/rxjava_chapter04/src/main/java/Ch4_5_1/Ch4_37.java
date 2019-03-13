package Ch4_5_1;

import etc.Log;
import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.reactivestreams.Publisher;

public class Ch4_37 {

    public static void main(String[] args){
        Integer[] data = {1,2,3,4};

        //1.count
        Single<Long> source = Observable.fromArray(data)
                .count();
        source.subscribe(count-> Log.i("count is "+count));

        //2.max() & min()
        Flowable.fromArray(data)
                .to(MathFlowable::max)
                .subscribe(max->Log.i("max is "+max));

        Flowable.fromArray(data)
                .to(MathFlowable::min)
                .subscribe(min->Log.i("min is "+min));

        //3.sum() & average()
        Flowable<Integer> flowable = Flowable.fromArray(data)
                .to(MathFlowable::sumInt);
        flowable.subscribe(sum->Log.i("sum is "+sum));

        Flowable<Double> flowable2 = Observable.fromArray(data)
                .toFlowable(BackpressureStrategy.BUFFER)
                .to(MathFlowable::averageDouble);
        flowable2.subscribe(average->Log.i("average is "+average));




    }

}
