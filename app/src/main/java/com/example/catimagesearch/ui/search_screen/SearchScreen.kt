package com.example.catimagesearch.ui.search_screen

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import com.example.catimagesearch.App
import com.example.catimagesearch.R
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

            responseImageAdapter.listener =  { link, type ->
                presenter.clickedButton(link, type)
            }

            buttonSearch.setOnClickListener {
                presenter.search(inputSearchText.text.toString())
            }
            inputSearchText.setOnFocusChangeListener { _: View?, hasFocus: Boolean ->
                if (hasFocus) presenter.inputTextFocus()
                else hideSavedQueries()
            }
            inputSearchText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.search(inputSearchText.text.toString())
                }
                false
            }


        }
        return view
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity!!.currentFocus

        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object ButtonType {
        const val DOWNLOAD = "downloadButton"
        const val SHARE = "shareButton"
        const val COPY = "copyLinkButton"
    }
    override fun onAttach(view: View) {
        super.onAttach(view)
        App.appComponent.inject(this)
        presenter.onCreate(this)
    }


    fun clickedShareButton(link: String) {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val shareIntent = Intent.createChooser(sendIntent, "Поделитесь изображением!")
        startActivity(shareIntent)

//        showToast("clicked share link, link: $link")
    }

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this.applicationContext, text, length).show()
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