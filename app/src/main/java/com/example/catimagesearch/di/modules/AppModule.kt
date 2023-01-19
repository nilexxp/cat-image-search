package com.example.catimagesearch.di.modules

import android.content.Context
import com.example.catimagesearch.IRetrofitServices
import com.example.catimagesearch.data.KeyData
import com.example.catimagesearch.data.database.SavedQueryDao
import com.example.catimagesearch.ui.search_screen.SearchScreenPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [DatabaseModule::class])
class AppModule {

    companion object{
        private val BASE_URL = "https://www.googleapis.com"
    }

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): IRetrofitServices = retrofit.create(IRetrofitServices::class.java)


    @Provides
    fun provideSearchScreenPresenter(api: IRetrofitServices, dao: SavedQueryDao, context: Context, keyData: KeyData): SearchScreenPresenter =
        SearchScreenPresenter(api, dao, context, keyData)

    @Provides
    fun provideKeyData(): KeyData = KeyData()

}