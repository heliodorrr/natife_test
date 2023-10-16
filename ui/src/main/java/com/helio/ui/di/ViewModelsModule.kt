package com.helio.ui.di

import androidx.lifecycle.ViewModel
import com.helio.ui.imagelist.ImageListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {

    @[Binds ViewModelKey(ImageListViewModel::class) IntoMap]
    fun bindImageListVM(vm: ImageListViewModel): ViewModel
}