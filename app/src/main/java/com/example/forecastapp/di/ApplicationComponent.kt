package com.example.forecastapp.di

import android.app.Application
import com.example.forecastapp.AndroidApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    ActivityBuilder::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder : ComponentBuilder<ApplicationComponent> {
        /**
         * will allow clients of this builder to pass their own instances,
         * and those instances can be injected within the component
         */
        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(app: AndroidApplication)
}

/**
 * Helper interface to help builder class create a compoment
 */
interface ComponentBuilder<out C> {
    fun build(): C
}
