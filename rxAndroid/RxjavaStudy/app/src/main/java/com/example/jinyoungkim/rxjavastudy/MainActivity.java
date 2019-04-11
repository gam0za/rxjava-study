package com.example.jinyoungkim.rxjavastudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text);

        // 1. Hello World 출력
        Observer<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello World!");
                e.onComplete();
            }
        }).subscribe(observer);


        // 2. 람다식 사용
        Observable.<String>create(s->{
            s.onNext("Hello World!");
            s.onComplete();
        }).subscribe(o->textView.setText(o));


        // 3. just() 사용
        Observable.just("Hello, World!")
                .subscribe(textView::setText);
    }
}
