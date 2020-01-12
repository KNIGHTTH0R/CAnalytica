package com.android.rbcanalytica.ui.listmain

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.rbcanalytica.ui.facebooklist.FacebookListFragment
import com.android.rbcanalytica.ui.twitterlist.TwitterListFragment

class ListFragmentAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment =
            TwitterListFragment()
        if(position == 0){
            fragment =
                TwitterListFragment()
        }else if(position==1){
            fragment =
                FacebookListFragment()
        }
        return fragment
    }

}