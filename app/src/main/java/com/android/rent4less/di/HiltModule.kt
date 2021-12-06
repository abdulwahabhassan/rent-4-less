package com.android.rent4less.di

import com.android.rent4less.data.api.HomePageApi
import com.android.rent4less.data.api.ServiceModule
import com.android.rent4less.data.api.SignUpApi
import com.android.rent4less.data.datasource.RemoteDatasource
import com.android.rent4less.data.datasource.RemoteDatasourceImpl
import com.android.rent4less.data.repository.RepositoryImpl
import com.android.rent4less.domain.repository.Repository
import com.android.rent4less.domain.usecases.CreateNewAccountUseCase
import com.android.rent4less.domain.usecases.GetHomePageDataUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomePageApiModule {

    private const val HOMEPAGE_API_BASE_URL = "https://rent-4-less-iyqm7.ondigitalocean.app/"

    @Provides
    fun providesHomePageApi () : HomePageApi {
        return ServiceModule().createHomePageApi(HOMEPAGE_API_BASE_URL)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SignUpApiModule {

    private const val SIGNUP_API_BASE_URL = "https://solo-crypto.herokuapp.com/api/"

    @Provides
    fun providesSignUpApi () : SignUpApi {
        return ServiceModule().createSignUpApi(SIGNUP_API_BASE_URL)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDatasourceModule {

    @Binds
    abstract fun bindRemoteDatasource(
        remoteDatasourceImpl: RemoteDatasourceImpl
    ): RemoteDatasource
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {

    @Singleton
    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(
        source: RemoteDatasource
    ): Repository {
        return RepositoryImpl(
            source
        )
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @ViewModelScoped
    @Provides
    fun provideGetHomePageDataUseCase(
        repository: Repository
    ) : GetHomePageDataUseCase {
        return GetHomePageDataUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideCreateAccountUseCase(
        repository: Repository
    ) : CreateNewAccountUseCase {
        return CreateNewAccountUseCase(repository)
    }

}

