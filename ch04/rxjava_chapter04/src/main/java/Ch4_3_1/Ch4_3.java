package Ch4_3_1;

import etc.Log;
import etc.Shape;
import io.reactivex.Observable;



public class Ch4_3 {
    public static void main(String[] args){
       String[] shapes = {"BALL","PENTAGON","STAR"};

       String[] coloredTriangles = {"2-T","6-T","4-T"};

       Observable<String> source = Observable.zip(
               Observable.fromArray(shapes).map(Shape::getSuffix),
               Observable.fromArray(coloredTriangles).map(Shape::getColor),
               (suffix,color)->color+suffix);

       source.subscribe(Log::i);
    }
}
