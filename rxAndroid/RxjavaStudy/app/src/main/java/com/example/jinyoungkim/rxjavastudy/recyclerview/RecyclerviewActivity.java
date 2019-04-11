package com.example.jinyoungkim.rxjavastudy.recyclerview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jinyoungkim.rxjavastudy.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecyclerviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        adapter.getItemPublishSubject().subscribe(s->toast(s.getTitle()));

    }

    private void toast(String title){
        Toast.makeText(getApplicationContext(),title,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(adapter==null){
            return;
        }

        getItemObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    adapter.updateItems(item);
                    adapter.notifyDataSetChanged();
                });

    }


    private Observable<RecyclerItem> getItemObservable() {
        RecyclerItem recyclerItem = new RecyclerItem();

        final PackageManager pm = getApplicationContext().getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        return Observable.fromIterable(pm.queryIntentActivities(i, 0))
                .sorted(new ResolveInfo.DisplayNameComparator(pm))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(item -> {
                    Drawable image = item.activityInfo.loadIcon(pm);
                    String title = item.activityInfo.loadLabel(pm).toString();
                    recyclerItem.setTitle(title);
                    recyclerItem.setImage(image);
                    return recyclerItem;
                });
    }


}
