package com.example.jinyoungkim.rxjavastudy.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinyoungkim.rxjavastudy.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private ArrayList<RecyclerItem> items = new ArrayList<>();
    private PublishSubject<RecyclerItem> publishSubject;

    RecyclerAdapter(){
        this.publishSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final RecyclerItem item = items.get(position);
        viewHolder.image.setImageDrawable(item.getImage());
        viewHolder.title.setText(item.getTitle());
        viewHolder.getClickObserver(item).subscribe(publishSubject);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(ArrayList<RecyclerItem> item){
        items.addAll(item);
    }

    public void updateItems(RecyclerItem item){
        items.add(item);
    }

    public PublishSubject<RecyclerItem> getItemPublishSubject() {
        return publishSubject;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.image);
            title = (TextView)itemView.findViewById(R.id.title);
        }

        Observable<RecyclerItem> getClickObserver(RecyclerItem item){
            return Observable.create(e->itemView.setOnClickListener(view->e.onNext(item)));
        }
    }
}
