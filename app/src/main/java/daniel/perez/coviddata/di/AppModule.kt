package daniel.perez.coviddata.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context)
{

    @Provides @Singleton
    fun provideContext(): Context
    {
        return context
    }

    @Provides @Singleton
    fun provideMoshi(): Moshi
    {
        return Moshi.Builder()
            .add( KotlinJsonAdapterFactory() )
            .build()
    }

    @Provides @Singleton
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory
    {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides @Singleton
    fun provideRxJavaAdapterFactory(): RxJava3CallAdapterFactory
    {
        return RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides @Singleton
    fun provideOkHttp(): OkHttpClient
    {

        val cacheSize: Long = 10 * 1024 * 1024
        return OkHttpClient.Builder()
            .cache( Cache(context.cacheDir, cacheSize) )
            .addInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)  )
            .build()
    }

    @Provides @Singleton
    fun provideRetrofit(moshi: MoshiConverterFactory,
                        callAdapterFactory: RxJava3CallAdapterFactory,
                        okHttp: OkHttpClient): Retrofit.Builder
    {
        return Retrofit.Builder()
            .client( okHttp )
            .addConverterFactory( moshi )
            .addCallAdapterFactory( callAdapterFactory )
    }
}