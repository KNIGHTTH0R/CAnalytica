package com.android.rbcanalytica.ui.twitterlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rbcanalytica.R
import com.android.rbcanalytica.dagger.ViewModelFactory
import com.android.rbcanalytica.databinding.FragmentTwitterListBinding
import com.android.rbcanalytica.repository.Review
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TwitterListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TwitterListViewModel
    private lateinit var binding: FragmentTwitterListBinding
    private lateinit var adapter: TwitterListAdapter
    private var reviews: List<Review> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_twitter_list, container, false
        )
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.twitterButton.setOnClickListener { getReviews() }

        viewModel = viewModelFactory.create(TwitterListViewModel::class.java)

        viewModel.reviews.observe(this, Observer { reviews ->
            reviews?.let { displayReviews(it) }
        })

        adapter = TwitterListAdapter(requireContext())
        val postRecyclerView = binding.recyclerView
        postRecyclerView.adapter = adapter
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        postRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.reviews.observe(this, Observer { postData ->
            postData?.let { displayReviews(it) }
        })

        getReviews()
    }

    private fun getReviews() {
        viewModel.getReviews()
    }

    private fun displayReviews(reviews: List<Review>) {
        adapter.setReviews(reviews)
        Toast.makeText(requireContext(), "Updated List!", Toast.LENGTH_SHORT).show()
    }
}