package com.example.catimagesearch.ui.search_screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.example.catimagesearch.IRetrofitServices
import com.example.catimagesearch.data.database.SavedQueryDao
import com.example.catimagesearch.data.entity.SavedQueryModel
import com.example.catimagesearch.data.google_responce.Item
import com.example.catimagesearch.data.google_responce.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class SearchScreenPresenter (
    private var api: IRetrofitServices,
    private var dao: SavedQueryDao,
    private var context: Context,
): Callback<ResponseModel> {

    //гугловская штука
    companion object{
        private const val API_KEY = "AIzaSyAq9xyzCZlcQFmCgKm633EZp1AFbmYYWfQ"
        private const val CX = "8b8faaa29e4af41f0"
    }

    private lateinit var view: SearchScreen
    private val coroutineIO = CoroutineScope(Dispatchers.Main)


    fun onCreate(view: SearchScreen) {
        this.view = view
    }
    fun search(query: String) {
        view.showLoader()
        api.getData(API_KEY, CX, query, "image").enqueue(this)
    }


    private fun updateResponseList(items: List<Item?>){
        view.updateList(items)
    }

    override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
        view.hideLoader()

        if (response.isSuccessful){
            response.body()?.items?.let { updateResponseList(it) }
            saveQuery(response.body()?.queries?.request?.first()?.searchTerms.toString())
        } else {
            view.showMessage("Код ошибки: "+response.code().toString())
        }
    }

    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
        view.hideLoader()
        view.showMessage(t.localizedMessage)
    }

    private fun saveQuery(query: String) {
        coroutineIO.launch {
            dao.insert(SavedQueryModel(query = query, date = Date().toString()))
        }
    }

    fun inputTextFocus(){
        coroutineIO.launch {
            view.showSavedQueries(dao.getStringQuery())
        }
    }

    fun clickedLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}