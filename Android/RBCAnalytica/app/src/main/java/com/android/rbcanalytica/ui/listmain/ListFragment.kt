package com.android.rbcanalytica.ui.listmain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.rbcanalytica.R
import com.android.rbcanalytica.databinding.FragmentListBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var listFragmentAdapter: ListFragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFragmentAdapter = ListFragmentAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = listFragmentAdapter

        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager,
            TabConfigurationStrategy { tab, position ->
                if(position == 0) {
                    tab.text = "Twitter"
                }else if(position == 1){
                    tab.text = "Facebook"
                }
            }).attach()

    }
}