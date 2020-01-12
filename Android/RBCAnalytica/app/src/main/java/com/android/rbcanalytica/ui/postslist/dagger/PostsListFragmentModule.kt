package com.android.rbcanalytica.ui.reviewanalysis.dagger

import com.android.rbcanalytica.ui.postslist.PostsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PostsListFragmentModule{
    @ContributesAndroidInjector
    internal abstract fun contributesReviewAnalysisFragment(): PostsListFragment
}