package com.khs.dagger2

import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class MemoryCardModule(val memorySize:Int) {
    @Provides
    fun providesMemoryCard():MemoryCard{
        Timber.d("memorySize: $memorySize")
        return MemoryCard()
    }
}