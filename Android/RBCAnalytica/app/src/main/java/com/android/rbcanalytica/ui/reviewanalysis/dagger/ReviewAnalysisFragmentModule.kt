package com.android.rbcanalytica.ui.reviewanalysis.dagger

import com.android.rbcanalytica.ui.reviewanalysis.ReviewAnalysisFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReviewAnalysisFragmentModule{
    @ContributesAndroidInjector
    internal abstract fun contributesReviewAnalysisFragment(): ReviewAnalysisFragment
}