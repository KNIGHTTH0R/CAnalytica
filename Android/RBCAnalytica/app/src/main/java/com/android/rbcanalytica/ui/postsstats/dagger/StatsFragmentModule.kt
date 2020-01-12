package com.android.rbcanalytica.ui.reviewanalysis.dagger

import com.android.rbcanalytica.ui.postsstats.StatsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StatsFragmentModule{
    @ContributesAndroidInjector
    internal abstract fun contributesStatsFragment(): StatsFragment
}