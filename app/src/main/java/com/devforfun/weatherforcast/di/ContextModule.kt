package com.devforfun.weatherforcast.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule constructor(context: Context) {

    var mContext = context;

    @Provides
    fun context() : Context {
        return mContext.applicationContext
    }
}