//package com.techsfactory.etmam.webservices
//
//import com.google.gson.GsonBuilder
//import io.reactivex.schedulers.Schedulers
//import me.linshen.retrofit2.adapter.LiveDataCallAdapterFactory
//import okhttp3.*
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Converter
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import trackwareschoolbus.parentschool.api_v3.ConstantsV3
//import trackwareschoolbus.parentschool.api_v3.WebServicesV3
//import trackwareschoolbus.parentschool.toolsV2.SharedPrefToolsV2
//import java.io.IOException
//import java.lang.reflect.Type
//import java.util.concurrent.TimeUnit
//
//
//
//
//class ApiClient(/*val currentUseName: String = "",val currentCookie: String = ""*/) : ConstantsV3 {
//    var httpClient: OkHttpClient? = null
//    var client: WebServicesV3? = buildRetrofit(
//        buildOkHttpClient(),
//        ConstantsV3.URLS.BASE_URL
//    )?.create(WebServicesV3::class.java)
//
//    private fun buildOkHttpClient(): OkHttpClient? {
//        if (httpClient == null) {
//            val httpClientBuilder = OkHttpClient.Builder()
//            httpClientBuilder.connectTimeout(3, TimeUnit.MINUTES)
//            httpClientBuilder.writeTimeout(3, TimeUnit.MINUTES)
//            httpClientBuilder.readTimeout(3, TimeUnit.MINUTES)
//            //            if (BuildConfig.DEBUG) {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//            httpClientBuilder.networkInterceptors().add(interceptor)
////            httpClientBuilder.addInterceptor(addCookiesInterceptor(currentCookie))
//            httpClientBuilder.addInterceptor(interceptor)
//
//
////            httpClientBuilder.addInterceptor(BasicAuthInterceptor(AppConstant.API.USERNAME, AppConstant.API.PASSWORD))
//
//
//            //            }
////            httpClientBuilder.cache(new Cache(AppController.mInstance.getCacheDir(),10 * 1024 * 1024));
//            httpClient = httpClientBuilder.build()
//        }
//        return httpClient
//    }
//
//    private fun addCookiesInterceptor(set_cookie: String) = object : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain) =
//            chain.proceed(chain.request().newBuilder().header("Set-Cookie", set_cookie).build())
//    }
//
////    private fun getHeaderInterceptor() = object : Interceptor {
////        @Throws(IOException::class)
////        override fun intercept(chain: Interceptor.Chain) =
////            chain.proceed(chain.request().newBuilder().header("Authorization", SharedPrefToolsV2.getAuthorization()).build())
////    }
//
//
//    private fun buildRetrofit(client: OkHttpClient?, base_url: String): Retrofit? {
//        return client?.let { client ->
//            val builder = Retrofit.Builder()
//            builder.baseUrl(base_url)
//            builder.client(client)
//            builder.addCallAdapterFactory(LiveDataCallAdapterFactory())
//            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
////        builder.addConverterFactory(NullOnEmptyConverterFactory());
////                    builder.addConverterFactory(NullOnEmptyConverterFactory());
//
////        val registerTypeAdapter = GsonBuilder().registerTypeAdapter(PreviewFieldValue::class.java, JsonDeserializer<PreviewFieldValue> { json, typeOfT, context ->
////            try {
////                if (json.isJsonObject()) {
////                    val aa = object : TypeToken<PreviewFieldValue>() {}.type
////                    var tutorials: PreviewFieldValue = Gson().fromJson(json.asJsonObject, aa)
////                    tutorials
////                } else {
////                    null
////                }
////
////            } catch (e: Error) {
////                null
////            }
////
////        })
//
////        builder.addConverterFactory(GsonConverterFactory.create(registerTypeAdapter.create()))
//
////        builder.addConverterFactory(GsonConverterFactory.create( GsonBuilder().serializeNulls().create()));
//            builder.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            //        builder.addConverterFactory(LiveDataResponseBodyConverterFactory.create());
//            return@buildRetrofit builder.build()
//        } ?: return@buildRetrofit null
//
//    }
//
//
//}
//
////class ReceivedCookiesInterceptor : Interceptor {
////    override fun intercept(chain: Interceptor.Chain): Response {
////        val originalResponse = chain.proceed(chain.request())
////
////        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
////            val cookies: HashSet<String> = HashSet()
////            for (header in originalResponse.headers("Set-Cookie")) {
////                cookies.add(header)
////            }
////
////            // Save the cookies (I saved in SharedPrefrence), you save whereever you want to save
//////             preferencesHelper.putStringSet(PreferenceKey.PREF_KEY_COOKIES, cookies)
////        }
////        return originalResponse
////    }
////
////
////}
