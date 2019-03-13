package Ch4_3_2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

import java.util.Scanner;

public class Ch4_30 {
    public static void main(String[] args){
        new Ch4_30().run();
    }
    public void run(){
        ConnectableObservable<String> source = userInput();
        Observable<Integer> a = source
                .filter(str->str.startsWith("a:"))
                .map(str->str.replace("a:",""))
                .map(Integer::parseInt);
        Observable<Integer> b = source
                .filter(str->str.startsWith("b:"))
                .map(str->str.replace("b:",""))
                .map(Integer::parseInt);

        Observable.combineLatest(
                a.startWith(0),
                b.startWith(0),
                (x,y)->x+y
        ).subscribe(res->System.out.println("Result: " + res));
        source.connect();

    }
    public ConnectableObservable<String> userInput(){
        return Observable.create((ObservableEmitter<String> emitter)->{
            Scanner in = new Scanner(System.in);
            while(true){
                System.out.println("Input: ");
                String line = in.nextLine();
                emitter.onNext(line);

                if(line.indexOf("exit")>=0){
                    in.close();
                    break;
                }
            }
        }).publish();
    }
}
