package com.wzc.t21_using_touch_gestures;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * @author wzc
 * @date 2018/5/12
 */
public class ExtendTouchableAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_touchable_area);

        View parentView = findViewById(R.id.parent_layout);

        parentView.post(new Runnable() {
            // 为什么要使用一个 Runnable 的形式？
            // Post in the parent's message queue to make sure the parent
            // lays out its children before you call getHitRect()

            @Override
            public void run() {
                // The bounds for the delegate view (an ImageButton
                // in this example)
                Rect delegateArea = new Rect();

                ImageButton myButton = (ImageButton) findViewById(R.id.button);
                myButton.setEnabled(true);
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ExtendTouchableAreaActivity.this,
                                "Touch occurred within ImageButton touch region.",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                // The hit rectangle for the ImageButton
                myButton.getHitRect(delegateArea);

                // Extend the touch area of the ImageButton beyond its bounds
                // on the right and bottom.
                delegateArea.right += 100;
                delegateArea.bottom += 100;

                // Instantiate a TouchDelegate.
                // "delegateArea" is the bounds in local coordinates of
                // the containing view to be mapped to the delegate view.
                // "myButton" is the child view that should receive motion
                // events.
                TouchDelegate touchDelegate = new TouchDelegate(delegateArea, myButton);
                if (View.class.isInstance(myButton.getParent())) {
                    ((View) myButton.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
}
