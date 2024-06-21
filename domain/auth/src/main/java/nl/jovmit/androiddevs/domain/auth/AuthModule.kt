package nl.jovmit.androiddevs.domain.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.jovmit.androiddevs.domain.auth.data.repository.RemoteAuthRepository
import nl.jovmit.androiddevs.domain.auth.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthModule {

    @Binds
    @Singleton
    internal abstract fun bindAuthRepository(
        repository: RemoteAuthRepository
    ): AuthRepository
}