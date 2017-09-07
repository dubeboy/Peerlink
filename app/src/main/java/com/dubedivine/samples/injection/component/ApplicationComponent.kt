package com.dubedivine.samples.injection.component

import com.dubedivine.samples.data.DataManager
import com.dubedivine.samples.data.remote.MvpStarterService
import com.dubedivine.samples.injection.ApplicationContext
import com.dubedivine.samples.injection.module.ApplicationModule
import android.app.Application
import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun mvpBoilerplateService(): MvpStarterService
}
