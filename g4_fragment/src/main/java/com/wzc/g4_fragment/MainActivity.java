package com.wzc.g4_fragment;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         findViewById(R.id.btn_news_reader).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, NewsReaderActivity.class));
             }
         });
         findViewById(R.id.btn_using_headless_fragment).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, UsingHeadlessFragmentActivity.class));
             }
         });
         findViewById(R.id.btn_stack).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, StackActivity.class));
             }
         });
         findViewById(R.id.btn_fragment_transaction).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, FragmentTransactionActivity.class));
             }
         });
         findViewById(R.id.btn_pop_back_stack).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, PopBackStackActivity.class));
             }
         });
    }
}
