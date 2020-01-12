package com.android.rbcanalytica.ui.postsstats

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.rbcanalytica.R
import com.android.rbcanalytica.dagger.ViewModelFactory
import com.android.rbcanalytica.databinding.FragmentStatsBinding
import com.android.rbcanalytica.repository.Review
import com.android.rbcanalytica.ui.postslist.PostsListViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class StatsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PostsListViewModel
    private lateinit var binding: FragmentStatsBinding
    private lateinit var mChart :LineChart
    private val positiveEntries: ArrayList<Entry> = ArrayList()
    private val negativeEntries: ArrayList<Entry> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_stats, container, false
        )
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = viewModelFactory.create(PostsListViewModel::class.java)
        viewModel.reviews.observe(this, Observer { postData ->
            postData?.let { handleReviews(it) }
        })

        getReviews()

        mChart = binding.chart
        mChart.setTouchEnabled(true)
        mChart.setPinchZoom(true)
        mChart.setDrawGridBackground(false)
        mChart.description.isEnabled = false
        mChart.setDrawBorders(false)
        mChart.axisRight.setDrawAxisLine(false)
        mChart.axisRight.setDrawGridLines(false)
        mChart.xAxis.setDrawGridLines(false)
        // enable touch gestures
        mChart.setTouchEnabled(true)

    }

    private fun handleReviews(posts: List<Review>) {
        positiveEntries.add(Entry(0.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(1.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(2.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(3.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(4.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(5.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(6.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(7.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(8.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(9.toFloat(), 0.toFloat()))
        positiveEntries.add(Entry(10.toFloat(),0.toFloat()))
        positiveEntries.add(Entry(11.toFloat(), 0.toFloat()))

        negativeEntries.add(Entry(0.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(1.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(2.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(3.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(4.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(5.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(6.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(7.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(8.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(9.toFloat(), 0.toFloat()))
        negativeEntries.add(Entry(10.toFloat(),0.toFloat()))
        negativeEntries.add(Entry(11.toFloat(), 0.toFloat()))
        for(post:Review in posts){
            if(post.positive){
                positiveEntries[post.date.month].y +=1
            }else{
                negativeEntries[post.date.month].y +=1
            }

        }
        renderData()
    }

    private fun renderData() {

        val dataSets :ArrayList<ILineDataSet> = ArrayList<ILineDataSet>()

        val positiveDataSet = LineDataSet(positiveEntries, "Positive Reviews")
        positiveDataSet.color = Color.GREEN
        positiveDataSet.valueTextColor = Color.GREEN

        val negativeDataSet = LineDataSet(negativeEntries, "Negative Reviews")
        negativeDataSet.color = Color.RED
        negativeDataSet.valueTextColor = Color.RED

        dataSets.add(positiveDataSet)
        dataSets.add(negativeDataSet)

        //****
        // Controlling X axis
        //****
// Controlling X axis
        val xAxis: XAxis = mChart.xAxis
        // Set the xAxis position to bottom. Default is top
        // Set the xAxis position to bottom. Default is top
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //Customizing x axis value
        //Customizing x axis value
        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "Jun","Jul", "Aug", "Sept", "Oct", "Nov", "Dec")

        val formatter =
            IAxisValueFormatter { value, axis -> months[value.toInt()] }
        xAxis.granularity = 1f // minimum axis-step (interval) is 1

        xAxis.valueFormatter = formatter

        //***
        // Controlling right side of y axis
        //***
// Controlling right side of y axis
        val yAxisRight: YAxis = mChart.axisRight
        yAxisRight.isEnabled = false

        //***
        // Controlling left side of y axis
        //***
// Controlling left side of y axis
        val yAxisLeft: YAxis = mChart.axisLeft
        yAxisLeft.granularity = 1f

        // Setting Data
        // Setting Data
        val data = LineData(dataSets)
        mChart.data = data
        //refresh
        //refresh
        mChart.invalidate()
    }

    private fun setData() {
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(1.toFloat(), 50.toFloat()))
        values.add(Entry(2.toFloat(), 100.toFloat()))
        values.add(Entry(3.toFloat(), 80.toFloat()))
        values.add(Entry(4.toFloat(), 120.toFloat()))

        val set1: LineDataSet
        if (mChart.data != null &&
            mChart.data.dataSetCount > 0
        ) {
            set1 = mChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            mChart.data.notifyDataChanged()
            mChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "Sample Data")
            set1.setDrawIcons(false)
            set1.enableDashedLine(10f, 5f, 0f)
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            set1.color = Color.GREEN
            set1.setCircleColor(Color.DKGRAY)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.valueTextSize = 9f
            set1.setDrawFilled(true)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1)
            val data = LineData(dataSets)
            mChart.data = data
        }
    }


    private fun getReviews() {
        viewModel.getReviews()
    }

    private fun displayReviews(reviews: List<Review>) {

    }

}