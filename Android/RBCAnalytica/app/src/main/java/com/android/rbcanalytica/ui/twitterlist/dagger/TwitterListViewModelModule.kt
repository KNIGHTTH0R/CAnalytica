package com.android.rbcanalytica.ui.reviewanalysis.dagger

import androidx.lifecycle.ViewModel
import com.android.rbcanalytica.dagger.ViewModelKey
import com.android.rbcanalytica.ui.twitterlist.TwitterListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TwitterListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TwitterListViewModel::class)
    fun bindsReivewAnalysisViewModel(twitterListViewModel: TwitterListViewModel) : ViewModel

}