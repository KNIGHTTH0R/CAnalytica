package com.android.rbcanalytica.ui.facebooklist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.rbcanalytica.R
import com.android.rbcanalytica.dagger.ViewModelFactory
import com.android.rbcanalytica.databinding.FragmentFacebookListBinding
import com.android.rbcanalytica.repository.Review
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FacebookListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentFacebookListBinding
    private var reviews: List<Review> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_facebook_list, container, false
        )
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun getReviews() {
    }

    private fun displayReviews(reviews: List<Review>) {

    }
}