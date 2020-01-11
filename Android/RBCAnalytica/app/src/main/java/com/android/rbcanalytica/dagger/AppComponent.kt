package com.android.rbcanalytica.dagger

import com.android.rbcanalytica.MainApplication
import com.android.rbcanalytica.ui.reviewanalysis.dagger.ReviewAnalysisFragmentModule
import com.android.rbcanalytica.ui.reviewanalysis.dagger.ReviewAnalysisViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        ReviewAnalysisFragmentModule::class,
        ReviewAnalysisViewModelModule::class

    ]
)
@Singleton
interface AppComponent : AndroidInjector<MainApplication> {


    override fun inject(instance: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }
}