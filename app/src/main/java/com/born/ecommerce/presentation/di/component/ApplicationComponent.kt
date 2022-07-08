package com.born.ecommerce.presentation.di.component

import com.born.ecommerce.EcommerceApp
import com.born.ecommerce.presentation.di.module.ActivityBindingModule
import com.born.ecommerce.presentation.di.module.AppModule
import com.born.ecommerce.presentation.di.module.NetworkModule
import com.born.ecommerce.presentation.di.module.ViewModelModule
import com.btechapp.presentation.di.module.CoroutinesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * singleton scope for application component
 * classes annotated with @Singleton will have a unique instance in this Component
 * AndroidSupportInjectionModule is a module from Dagger.Android for sub components
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        AppModule::class,
        NetworkModule::class,
        CoroutinesModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<EcommerceApp> {

    // binds application instance to the application component
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: EcommerceApp): ApplicationComponent
    }
}
