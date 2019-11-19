package com.jetpack.demos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.jetpack.demos.databinding.DatabindingActivity;
import com.jetpack.demos.lifecycles.LifecyclesActivity;
import com.jetpack.demos.livedata.LiveDataActivity;
import com.jetpack.demos.room.RoomActivity;
import com.jetpack.demos.springanimation.SpringAnimationActivity;
import com.jetpack.demos.viewmodel.ViewModelActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         findViewById(R.id.button_room).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 RoomActivity.start(MainActivity.this);
             }
         });
         findViewById(R.id.button_lifecycles).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 LifecyclesActivity.start(MainActivity.this);
             }
         });
         findViewById(R.id.button_livedata).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 LiveDataActivity.start(MainActivity.this);
             }
         });
         findViewById(R.id.button_viewmodel).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ViewModelActivity.start(MainActivity.this);
             }
         });
         findViewById(R.id.button_spring_animation).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SpringAnimationActivity.start(MainActivity.this);
             }
         });
         findViewById(R.id.button_databinding).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DatabindingActivity.start(MainActivity.this);
             }
         });
    }
}
