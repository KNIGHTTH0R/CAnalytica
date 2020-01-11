package com.android.rbcanalytica.ui.reviewanalysis

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.rbcanalytica.R
import com.android.rbcanalytica.dagger.ViewModelFactory
import com.android.rbcanalytica.databinding.FragmentReviewAnalysisBinding
import com.android.rbcanalytica.repository.Review
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ReviewAnalysisFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ReviewAnalysisViewModel
    private lateinit var binding: FragmentReviewAnalysisBinding
    private var reviews: List<Review> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_review_analysis, container, false
        )
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.twitterButton.setOnClickListener { getReviews() }

        viewModel = viewModelFactory.create(ReviewAnalysisViewModel::class.java)

        viewModel.reviews.observe(this, Observer { reviews ->
            reviews?.let { displayReviews(it) }
        })
    }

    private fun getReviews() {
        viewModel.getReviews()
    }

    private fun displayReviews(reviews: List<Review>) {

    }
}