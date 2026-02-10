package com.bonial.brochure

import android.app.Application
import com.bonial.brochure.di.appModule
import com.bonial.core.di.coreModule
import com.bonial.data.di.dataModule
import com.bonial.domain.di.domainModule
import com.bonial.network.di.createNetworkModule
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
