package com.android.rbcanalytica.ui.reviewanalysis.dagger

import com.android.rbcanalytica.ui.twitterlist.TwitterListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TwitterListFragmentModule{
    @ContributesAndroidInjector
    internal abstract fun contributesReviewAnalysisFragment(): TwitterListFragment
}