//package com.example.test.di
//
//import com.example.test.data.network.SwitchGameApiKtor
//import com.example.test.data.network.SwitchGameApiKtorImpl
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import io.ktor.client.*
//import io.ktor.client.engine.android.*
//import io.ktor.client.features.*
//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*
//import io.ktor.client.features.logging.*
//import io.ktor.client.features.websocket.*
//import kotlinx.serialization.json.Json
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object KtorModule {
//
//    @Provides
//    fun json() = Json {
//        ignoreUnknownKeys = true // 모델에 없고, json에 있는경우 해당 key 무시
//        prettyPrint = true
//        isLenient = true // "" 따옴표 잘못된건 무시하고 처리
//        encodeDefaults = true //null 인 값도 json에 포함 시킨다.
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideKtorHttpClient(): HttpClient {
//        return HttpClient(Android) {
//            install(Logging)
//            install(WebSockets)
//            install(JsonFeature) {
//                serializer = KotlinxSerializer(
//                    kotlinx.serialization.json.Json {
//                        prettyPrint = true
//                        isLenient = true
//                        ignoreUnknownKeys = true
//                    }
//                )
//            }
//            install(HttpTimeout){
//                requestTimeoutMillis = 15 * 1000L
//            }
//            install(HttpSend) {
//
//            }
////            if (SdkConfig.isLoggingEnabled) {
////                install(Logging) {
////                    logger = CustomAndroidHttpLogger
////                    level = LogLevel.ALL
////                }
////            }
//        }
////            .apply {
////            receivePipeline.intercept(HttpReceivePipeline.Before) { response ->
////                response
////            }
////        }
//    }
//
//    @Singleton
//    @Provides
//    fun provideSwitchApiService(client : HttpClient) : SwitchGameApiKtor = SwitchGameApiKtorImpl(client)
//
//}