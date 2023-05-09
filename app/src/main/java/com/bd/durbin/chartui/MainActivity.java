package com.bd.durbin.chartui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PieChart chart3;
    private LineChart chart2;
    private BarChart chart1;
    BarData barData;
    BarDataSet barDataSet;
    List barEntriesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Piechart3Code();
        Linechart2Code();

        Barchart1Code();


        setData3(3, 10);
        setData2(13, 50);

    }

    protected final String[] parties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private void Barchart1Code() {


        chart1 = findViewById(R.id.chart1);
        //  chart1.setOnChartValueSelectedListener(this);
        chart1.setDrawBarShadow(false);
        chart1.setDrawValueAboveBar(false);
        // baground line
        chart1.getAxisRight().setDrawGridLines(false);
        chart1.getAxisLeft().setDrawGridLines(false);
        chart1.getXAxis().setDrawGridLines(false);
        chart1.setPinchZoom(false);
        chart1.setDrawGridBackground(false);


        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");
        chart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));

        Legend l = chart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, 6));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));

        barDataSet = new BarDataSet(barEntriesArrayList, "bar chart");
        barDataSet.setColors(new int[] {Color.RED, Color.GREEN, Color.GRAY, Color.BLACK, Color.BLUE});
        barData = new BarData(barDataSet);
        chart1.setData(barData);
    }


    private void Linechart2Code() {

        chart2 = findViewById(R.id.chart2);
        // chart2.setOnChartValueSelectedListener(this);
        chart2.setDrawGridBackground(false);

        // no description text
        chart2.getDescription().setEnabled(false);

        // enable touch gestures
        chart2.setTouchEnabled(true);

        // enable scaling and dragging
        chart2.setDragEnabled(true);
        chart2.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart2.setPinchZoom(true);

        // set an alternative background color
        // chart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(chart2); // For bounds control
        chart2.setMarker(mv); // Set the marker to the chart

        XAxis xl = chart2.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        xl.setAxisMinimum(0f);

        YAxis leftAxis = chart2.getAxisLeft();
        leftAxis.setInverted(true);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart2.getAxisRight();
        rightAxis.setEnabled(false);


        // // restrain the maximum scale-out factor
        // chart.setScaleMinima(3f, 3f);
        //
        // // center the view to a specific position inside the chart
        // chart.centerViewPort(10, 50);

        // get the legend (only possible after setting data)
        Legend l = chart2.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);

        // don't forget to refresh the drawing
        chart2.invalidate();
    }

    private void setData2(int count, float range) {

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float xVal = (float) (Math.random() * range);
            float yVal = (float) (Math.random() * range);
            entries.add(new Entry(xVal, yVal));
        }

        // sort by x-value
        Collections.sort(entries, new EntryXComparator());

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(entries, "DataSet 1");

        set1.setLineWidth(1.5f);
        set1.setCircleRadius(4f);

        // create a data object with the data sets
        LineData data = new LineData(set1);

        // set data
        chart2.setData(data);
    }


    private void Piechart3Code() {
        chart3 = findViewById(R.id.chart3);
        chart3.setUsePercentValues(true);
        chart3.getDescription().setEnabled(false);
        chart3.setExtraOffsets(5, 10, 5, 5);

        chart3.setDragDecelerationFrictionCoef(0.95f);
        chart3.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        chart3.setDrawHoleEnabled(true);
        chart3.setHoleColor(Color.WHITE);

        chart3.setTransparentCircleColor(Color.WHITE);
        chart3.setTransparentCircleAlpha(110);

        chart3.setHoleRadius(58f);
        chart3.setTransparentCircleRadius(61f);

        chart3.setDrawCenterText(true);

        chart3.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart3.setRotationEnabled(true);
        chart3.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
//        chart3.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);

        chart3.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart3.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private void setData3(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) (Math.random() * range) + range / 5, parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        chart3.setData(data);
        // undo all highlights
        chart3.highlightValues(null);

        chart3.invalidate();
    }


}