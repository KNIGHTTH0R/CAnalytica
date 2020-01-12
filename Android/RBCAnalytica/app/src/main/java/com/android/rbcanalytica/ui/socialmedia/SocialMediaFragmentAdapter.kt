package com.android.rbcanalytica.ui.socialmedia

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.rbcanalytica.ui.postsstats.StatsFragment
import com.android.rbcanalytica.ui.postslist.PostsListFragment

class SocialMediaFragmentAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment =
            PostsListFragment()
        if(position == 0){
            fragment =
                PostsListFragment()
        }else if(position==1){
            fragment =
                StatsFragment()
        }
        return fragment
    }

}