package com.example.jinyoungkim.rxjavastudy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class LoopActivity extends AppCompatActivity {

    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        text2 = (TextView)findViewById(R.id.text2);

        Iterable<String> samples  = Arrays.asList("banana", "orange", "apple", "apple mango",
                "melon", "watermelon");

        // rxJava 2.x
        Observable.fromIterable(samples)
                .skipWhile(s -> !s.contains("apple"))
//                .filter(s -> s.contains("apple"))
                .first("Not found")
                .subscribe((Consumer<? super String>) text2::setText);

    }



}
