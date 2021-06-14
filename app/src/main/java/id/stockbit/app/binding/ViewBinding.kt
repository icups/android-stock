package id.stockbit.app.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.stockbit.ext.property.orDefault
import id.stockbit.ext.view.backgroundColor
import id.stockbit.ext.view.gone
import id.stockbit.ext.view.invisible
import id.stockbit.ext.view.visible

object ViewBinding {

    @JvmStatic
    @BindingAdapter("backgroundColor")
    fun setBackgroundColor(view: View, hexCode: String? = "#ffffff") {
        hexCode?.let { view.backgroundColor(it) }
    }

    @JvmStatic
    @BindingAdapter("visibility")
    fun setVisibility(view: View, visibility: Int) {
        when (visibility) {
            View.VISIBLE -> view.visible()
            View.GONE -> view.gone()
            View.INVISIBLE -> view.invisible()
        }
    }

    @JvmStatic
    @BindingAdapter("refreshing")
    fun setRefreshing(view: SwipeRefreshLayout, refreshing: Boolean?) {
        view.isRefreshing = refreshing.orDefault()
    }

}