package Ch4_4_4;

import etc.Log;
import etc.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

public class Ch4_36 {

    public static void main(String[] args){
        String[] data = {"1","2","3","4"};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);
        //.all(val->Shape.BALL.equals(Shape.getShape(val)));

        source.subscribe((Consumer<? super Boolean>) Log::i);

    }



}
