package com.android.rbcanalytica.ui.reviewanalysis.dagger

import androidx.lifecycle.ViewModel
import com.android.rbcanalytica.dagger.ViewModelKey
import com.android.rbcanalytica.ui.reviewanalysis.ReviewAnalysisViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ReviewAnalysisViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReviewAnalysisViewModel::class)
    fun bindsReivewAnalysisViewModel(reviewAnalysisViewModel: ReviewAnalysisViewModel) : ViewModel

}