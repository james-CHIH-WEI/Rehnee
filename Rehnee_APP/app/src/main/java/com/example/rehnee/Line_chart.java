package com.example.rehnee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line_chart extends AppCompatActivity {

    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYAxis;           //右侧Y轴
    private Legend legend;
    private LineDataSet lineDataSet;
    private Spinner select_type;

    private String Id;
    private String[] type_name = new String[3];
    private String[][] select_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");

        type_name[0] = getString(R.string.type_1);
        type_name[1] = getString(R.string.type_2);
        type_name[2] = getString(R.string.type_3);

        findid();

        init_spinner();

        String[][] values = sqlite_record_query();
        init_lineChart();
        showLineChart(values);



        select_type.setOnItemSelectedListener(select_typeOnItemClick);
    }

    private void findid() {
        lineChart = (LineChart) findViewById(R.id.chart_line);
        select_type = (Spinner) findViewById(R.id.select_type);
    }

    private void init_LineDataSet() {//圖表初始化
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawFilled(true);
        //lineDataSet.setFormLineWidth(3f);
        //lineDataSet.setFormSize(20.f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);

        lineChart.setDrawGridBackground(false);
        //是否顯示邊界
        lineChart.setDrawBorders(true);
        //是否可以拖移
        lineChart.setDragEnabled(true);
        //是否有觸碰事件
        lineChart.setTouchEnabled(true);
        //設置XY軸轉動效果
        lineChart.animateY(500);
        lineChart.animateX(500);

        lineChart.getDescription().setText("");

        legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(20f);


    }

    private void init_lineChart() {//曲線初始化
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYAxis = lineChart.getAxisRight();
        //X轴在底部開始
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //Y軸從0開始
        leftYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMinimum(0f);
    }

    private void showLineChart(String[][] values) {
        int x;
        List<Entry> chartData = new ArrayList<>();
        final ArrayList<String> xLabel = new ArrayList<>();
        for (x = 0; x < values.length; x++) {
            chartData.add(new Entry(x, Integer.valueOf(values[x][7])));
            xLabel.add(values[x][5].substring(5));
        }

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String val = null;
                try {
                    val = xLabel.get((int) value);
                } catch (IndexOutOfBoundsException e) {
                    axis.setGranularityEnabled(false);
                }
                return val;
            }
        });

        lineDataSet = new LineDataSet(chartData, "復健花費時間(秒)");
        init_LineDataSet();
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);

        setMarkerView();
    }


    private String[][] sqlite_record_query() {
        String[][] values;
        SQLite_Record sqLite_record = new SQLite_Record(Line_chart.this, Line_chart.this);
        values = sqLite_record.query(Id);
        return values;
    }

    private void init_spinner() {
        int x;
        int[] use_type = {0, 0, 0};

        String[][] values = sqlite_record_query();
        List<String> type = new ArrayList<>();

        for (x = 0; x < values.length; x++) {
            if (values[x][2].equals("1")) {
                use_type[0] = 1;
            } else if (values[x][2].equals("2")) {
                use_type[1] = 1;
            } else if (values[x][2].equals("3")) {
                use_type[2] = 1;
            }
        }
        for (x = 0; x < use_type.length; x++) {
            if (use_type[x] == 1) {
                type.add(type_name[x]);
            }
        }

        ArrayAdapter<String> typeList = new ArrayAdapter<>(Line_chart.this, android.R.layout.simple_spinner_dropdown_item, type);
        select_type.setAdapter(typeList);
    }

    private AdapterView.OnItemSelectedListener select_typeOnItemClick = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int now_select_type, long l) {
            String[][] values = sqlite_record_query();
            int x, type_index = 0;

            for (x = 0; x < type_name.length; x++) {
                if (type_name[x].equals(select_type.getSelectedItem().toString())) {
                    type_index = x + 1;
                    break;
                }
            }

            select_values = new String[0][0];
            for (x = 0; x < values.length; x++) {
                if (values[x][2].equals(String.valueOf(type_index))) {
                    select_values = Arrays.copyOf(select_values, select_values.length + 1);//把陣列長度加一
                    select_values[select_values.length - 1] = new String[8];
                    System.arraycopy(sqlite_record_query()[x], 0, select_values[select_values.length - 1], 0, sqlite_record_query()[x].length);
                }
            }
            lineChart.clear();
            showLineChart(select_values);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    public void setMarkerView() {
        LineChartMarkView mv = new LineChartMarkView(this, xAxis.getValueFormatter());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        lineChart.invalidate();
    }

}
