package com.example.rehnee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

public class LineChartMarkView extends MarkerView {

    private TextView tvDate;
    private TextView tvValue;
    private IAxisValueFormatter xAxisValueFormatter;
    DecimalFormat df = new DecimalFormat("0");

    public LineChartMarkView(Context context, IAxisValueFormatter xAxisValueFormatter) {
        super(context, R.layout.activity_markview);
        this.xAxisValueFormatter = xAxisValueFormatter;

        tvDate = findViewById(R.id.tv_date);
        tvValue = findViewById(R.id.tv_value);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvDate.setText("復健日期："+xAxisValueFormatter.getFormattedValue(e.getX(), null));
        tvValue.setText("花費時間：" + df.format(e.getY()) + "秒");
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());

    }
}
