package com.dubedivine.samples.injection.module

import android.app.Activity
import android.content.Context
import com.dubedivine.samples.features.common.SearchArrayAdapter
import com.dubedivine.samples.injection.ActivityContext
import dagger.Module
import dagger.Provides


// this is where I list how it instantiate a class

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mActivity
    }

    @Provides
    internal fun providesSearchArrayAdapter(): SearchArrayAdapter {
        return SearchArrayAdapter(mActivity, arrayListOf())
    }


}
