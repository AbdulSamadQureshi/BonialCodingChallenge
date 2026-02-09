package com.rabbah.clw

import android.app.Application
import com.rabbah.clw.di.appModule
import com.rabbah.core.di.coreModule
import com.rabbah.data.di.dataModule
import com.rabbah.domain.di.domainModule
import com.rabbah.network.di.createNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger() // todo disable logger in production
            androidContext(this@App)
            modules(
                appModule,
                createNetworkModule(BuildConfig.BASE_URL, BuildConfig.DEBUG),
                dataModule,
                domainModule,
                coreModule
            )
        }
    }
}