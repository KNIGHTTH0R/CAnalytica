package com.android.rbcanalytica.ui.reviewanalysis.dagger

import androidx.lifecycle.ViewModel
import com.android.rbcanalytica.dagger.ViewModelKey
import com.android.rbcanalytica.ui.postslist.PostsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PostsListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsListViewModel::class)
    fun bindsReivewAnalysisViewModel(postsListViewModel: PostsListViewModel) : ViewModel

}