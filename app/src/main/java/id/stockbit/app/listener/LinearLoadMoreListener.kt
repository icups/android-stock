package id.stockbit.app.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.stockbit.constant.Page

abstract class LinearLoadMoreListener(private val layoutManager: RecyclerView.LayoutManager?) : RecyclerView.OnScrollListener() {

    private var mPage = Page.FIRST

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems(mPage)
            }
        }
    }

    abstract fun isLoading(): Boolean
    abstract fun loadMoreItems(next: Int)

    fun isFirstPage() = mPage == Page.FIRST
    fun nextPage() = mPage++

    fun reset() {
        mPage = Page.FIRST
    }

}