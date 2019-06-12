package muhammadbahaa.actorbox.data.remote

import io.reactivex.schedulers.Schedulers
import muhammadbahaa.actorbox.BuildConfig
import muhammadbahaa.actorbox.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by muhammadbahaa on 2019-06-12.
 */
class ApiClient {

    companion object {

        private val retrofit: Retrofit? = null

        fun provideRetrofitInterface(): Retrofit {

            if (retrofit != null)
                return retrofit

            return Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(getUnsafeOkHttpClient(createLoggingInterceptor(), createHeaderInterceptor()))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }

        private fun getUnsafeOkHttpClient(logging: Interceptor, header: Interceptor): OkHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(header)
                .addInterceptor(logging)
                .build()

        private fun createHeaderInterceptor(): Interceptor = Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url().newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
                .method(request.method(), request.body())
                .build()
            chain.proceed(newRequest)
        }

        private fun createLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
}