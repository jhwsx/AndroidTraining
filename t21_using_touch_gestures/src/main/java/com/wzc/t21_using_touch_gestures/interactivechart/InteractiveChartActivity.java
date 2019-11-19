package com.wzc.t21_using_touch_gestures.interactivechart;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.wzc.t21_using_touch_gestures.R;

/**
 * @author wzc
 * @date 2018/5/11
 */
public class InteractiveChartActivity extends AppCompatActivity {
    private InteractiveLineGraphView mGraphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactivechart);
        mGraphView = (InteractiveLineGraphView) findViewById(R.id.chart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_zoom_in:
                mGraphView.zoomIn();
                return true;

            case R.id.action_zoom_out:
                mGraphView.zoomOut();
                return true;

            case R.id.action_pan_left:
                mGraphView.panLeft();
                return true;

            case R.id.action_pan_right:
                mGraphView.panRight();
                return true;

            case R.id.action_pan_up:
                mGraphView.panUp();
                return true;

            case R.id.action_pan_down:
                mGraphView.panDown();
                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
