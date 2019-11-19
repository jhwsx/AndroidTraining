package com.wan.t16_creating_custom_views;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wan.t16_creating_custom_views.view.PieChart;

/**
 * @author wzc
 * @date 2018/3/1
 */
public class PieChartActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        Resources res = getResources();
        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

        ((Button) findViewById(R.id.Reset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = count % pie.mData.size();
                pie.setCurrentItem(index);
                count++;
            }
        });
    }
}
