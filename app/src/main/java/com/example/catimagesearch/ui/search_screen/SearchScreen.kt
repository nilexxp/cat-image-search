package com.example.catimagesearch.ui.search_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import com.example.catimagesearch.App
import com.example.catimagesearch.IRetrofitServices
import com.example.catimagesearch.R
import com.example.catimagesearch.data.database.SavedQueryDao
import com.example.catimagesearch.data.google_responce.Item
import com.example.catimagesearch.ui.adapter.ResponseAdapter
import com.example.catimagesearch.ui.adapter.SavedQueriesAdapter
import kotlinx.android.synthetic.main.search_screen.view.*
import javax.inject.Inject

class SearchScreen: Controller() {

    @Inject
    lateinit var presenter: SearchScreenPresenter

    private val responseImageAdapter = ResponseAdapter()
    private val queriesAdapter = SavedQueriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.search_screen, container, false)

        view.apply {
            responseList.adapter = responseImageAdapter

            savedQueriesList.adapter = queriesAdapter
            queriesAdapter.listener =  {
                inputSearchText.setText(it)
            }

            responseImageAdapter.listener =  {
                presenter.clickedLink(it)
            }

            buttonSearch.setOnClickListener {
                presenter.search(inputSearchText.text.toString())
            }
            inputSearchText.setOnFocusChangeListener { _: View?, hasFocus: Boolean ->
                if (hasFocus) presenter.inputTextFocus()
                else hideSavedQueries()
            }


        }
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        App.appComponent.inject(this)
        presenter.onCreate(this)
    }


    fun updateList(items: List<Item?>) {
        responseImageAdapter.refresh(items as List<Item>)
    }

    fun showLoader(){
        responseImageAdapter.clear()
        view?.messageView?.visibility = TextView.GONE
        view?.loader?.visibility = ProgressBar.VISIBLE
        view?.clearFocus()
    }

    fun hideLoader(){
        view?.loader?.visibility = ProgressBar.GONE
    }

    fun showMessage(message: String){
        view!!.messageView!!.text = message
        view?.messageView?.visibility = TextView.VISIBLE
    }

    fun showSavedQueries(list: List<String>) {
        queriesAdapter.refresh(list)
        view?.savedQueriesList?.visibility = RecyclerView.VISIBLE
    }

    fun hideSavedQueries() {
        view?.savedQueriesList?.visibility = RecyclerView.GONE
    }
}