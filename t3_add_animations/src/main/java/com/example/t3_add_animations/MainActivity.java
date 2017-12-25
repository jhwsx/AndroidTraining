package com.example.t3_add_animations;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.t3_add_animations.screenslide.ScreenSlideActivity;

public class MainActivity extends ListActivity {

    private Element[] mElements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mElements = new Element[] {
                new Element(R.string.text_simple_crossfade,SimpleCrossfadeActivity.class),
                new Element(R.string.text_cardflip,CardFlipActivity.class),
                new Element(R.string.text_screen_slide,ScreenSlideActivity.class),
                new Element(R.string.text_layout_changes,LayoutChangesActivity.class),

        };
        setListAdapter(new ArrayAdapter<Element>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mElements));
    }

    private class Element {
        private Class<? extends Activity> mClass;
        private String mTitle;

        public Element( int resId,Class<? extends Activity> aClass) {
            mClass = aClass;
            mTitle = getResources().getString(resId);
        }


        @Override
        public String toString() {
            return mTitle.toString();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(MainActivity.this, mElements[position].mClass);
        startActivity(intent);
    }
}
