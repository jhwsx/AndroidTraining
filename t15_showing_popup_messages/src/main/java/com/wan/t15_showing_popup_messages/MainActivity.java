package com.wan.t15_showing_popup_messages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;
    private RecyclerView mRecyclerView;
    static final String[] THINGS = { "bottle", "bowl", "brick", "building",
            "bunny", "cake", "car", "cat", "cup", "desk", "dog", "duck",
            "elephant", "engineer", "fork", "glass", "griffon", "hat", "key", "knife", "lawyer",
            "llama", "manual", "meat", "monitor", "mouse", "tangerine", "paper", "pear", "pen",
            "pencil", "phone", "physicist", "planet", "potato", "road", "salad", "shoe", "slipper",
            "soup", "spoon", "star", "steak", "table", "terminal", "treehouse", "truck",
            "watermelon", "window" };
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        final Button button = (Button) findViewById(R.id.btn);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorlayout), "Snackbar", Snackbar.LENGTH_SHORT);
//                snackbar.setAction("改变button的颜色", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (flag) {
//                            button.setBackgroundResource(R.color.colorAccent);
//                            flag = false;
//                        } else {
//                            button.setBackgroundResource(R.color.colorPrimaryDark);
//                            flag = true;
//                        }
//
//                    }
//                });
//                snackbar.show();
//            }
//        });
        List<String> mList = Arrays.asList(THINGS);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(mList));
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("MainActivity", "onScrollStateChanged newState : " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("MainActivity", "onScrolled dx = " + dx + ", dy = " + dy);
                if (dy > 0) {
                    if (mFloatingActionButton.isShown()) {
                        mFloatingActionButton.hide();
                    }
                } else if (dy < 0) {
                    if (!mFloatingActionButton.isShown()) {
                        mFloatingActionButton.show();
                    }
                }
            }
        });
    }
}
