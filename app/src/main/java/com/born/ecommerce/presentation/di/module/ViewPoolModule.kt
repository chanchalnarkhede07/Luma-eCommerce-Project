package com.born.ecommerce.presentation.di.module

import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Provides [RecyclerView.RecycledViewPool]s to share views between [RecyclerView]s.
 * E.g. Between different days of the schedule.
 */
@Module
internal class ViewPoolModule {

    @Provides
    @Named("productViewPool")
    fun providesProductViewPool(): RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    @Provides
    @Named("bestSellersViewPool")
    fun providesBestSellersViewPool(): RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    @Provides
    @Named("addPromottedViewPool")
    fun providesAddPromttedViewPool(): RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    @Provides
    @Named("tagViewPool")
    fun providesTagViewPool(): RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
}
