package com.devexperto.movietrailerstv.ui.detail

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.leanback.widget.*
import com.devexperto.movietrailerstv.R
import com.devexperto.movietrailerstv.domain.Movie
import com.devexperto.movietrailerstv.ui.common.parcelable

class FullWidthDetailsMovieRowPresenter(private val activity: Activity) :
    FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter()) {
    //val moviePath: String = ""

    private enum class Options(@StringRes val stringRes: Int) {
        WATCH_TRAILER(R.string.watch_trailer),
        VER(R.string.ver);
    }

    init {
        val sharedElementHelper = FullWidthDetailsOverviewSharedElementHelper()
        sharedElementHelper.setSharedElementEnterTransition(
            activity, DetailActivity.SHARED_ELEMENT_NAME
        )
        setListener(sharedElementHelper)
        isParticipatingEntranceTransition = true
    }

    fun buildActions(movie: Movie): ArrayObjectAdapter {

        setOnActionClickedListener { action ->
            val option = Options.values().first { it.ordinal == action.id.toInt() }
            if(option.ordinal==0) {
                val movieIntent = Intent(activity, MovieActivity::class.java)
                movieIntent.putExtra("path", movie.trailer)
                activity.startActivity(movieIntent)
            }else {
                val movieIntent = Intent(activity, MovieActivity::class.java)
                movieIntent.putExtra("path", movie.movie)
                activity.startActivity(movieIntent)
            }
        }

        return ArrayObjectAdapter().apply {
            Options.values().forEach { option ->
                add(Action(option.ordinal.toLong(), activity.getString(option.stringRes)))
            }
        }
    }
}

private class DetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(vh: ViewHolder, item: Any) {
        val movie = item as Movie
        vh.title.text = movie.title
        vh.subtitle.text = movie.releaseDate
        vh.body.text = movie.summary
    }

}