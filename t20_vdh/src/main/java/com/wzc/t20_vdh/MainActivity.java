package com.wzc.t20_vdh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wzc.t19_vdh.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonDragH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("horizontal", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonDragV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("vertical", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonDragEdge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("edge", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonDragCapture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                intent.putExtra("capture", true);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonYoutube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonHongyang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HongyangDemoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonWzc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreeDirectionScrollUnlockActivity.class);
                startActivity(intent);
            }
        });
    }
}
