package com.android.rbcanalytica.ui.socialmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.rbcanalytica.R
import com.android.rbcanalytica.databinding.FragmentSocialMediaBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

class SocialMediaFragment : Fragment() {

    private lateinit var binding: FragmentSocialMediaBinding
    private lateinit var listFragmentAdapter: SocialMediaFragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_social_media, container, false
        )
        (activity as AppCompatActivity).supportActionBar?.title = "Twitter"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFragmentAdapter = SocialMediaFragmentAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = listFragmentAdapter

        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager,
            TabConfigurationStrategy { tab, position ->
                if(position == 0) {
                    tab.text = "Posts"
                }else if(position == 1){
                    tab.text = "Stats"
                }
            }).attach()

    }
}