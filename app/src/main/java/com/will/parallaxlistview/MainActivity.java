package com.will.parallaxlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{
    LinearLayout header;
    int headerHeight;
    Toolbar toolbar;
    @Override
    @SuppressWarnings("all")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObservableListView listView = (ObservableListView) findViewById(R.id.list);
        initListView(listView);
        listView.setScrollViewCallbacks(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Android");
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0,getResources().getColor(R.color.colorPrimary)));
        setSupportActionBar(toolbar);
    }
    private void initListView(ListView listView){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1;i<=100; i++){
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        header = (LinearLayout)getLayoutInflater().inflate(R.layout.header,listView,false);
        headerHeight = 300*4 - 56*4 -24*4;
        listView.addHeaderView(header);
        listView.setAdapter(adapter);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / headerHeight);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        header.setAlpha(1 - alpha);
        if(scrollY > 56*4){
            toolbar.setTitle("CSDN");
        }else{
            toolbar.setTitle("Android");
        }
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    public void onDownMotionEvent() {

    }
}
