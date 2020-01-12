package com.android.rbcanalytica.ui.reviewanalysis.dagger

import com.android.rbcanalytica.ui.facebooklist.FacebookListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FacebookListFragmentModule{
    @ContributesAndroidInjector
    internal abstract fun contributesFacebookAnalysisFragment(): FacebookListFragment
}