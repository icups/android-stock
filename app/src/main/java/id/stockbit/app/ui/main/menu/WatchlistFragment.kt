package id.stockbit.app.ui.main.menu

import id.stockbit.app.R
import id.stockbit.app.base.BaseFragment
import id.stockbit.app.databinding.FragmentWatchlistBinding
import id.stockbit.app.listener.LinearLoadMoreListener
import id.stockbit.app.ui.main.adapter.CryptoAdapter
import id.stockbit.constant.Page
import id.stockbit.ext.alert.showToast
import id.stockbit.ext.observer.observe
import id.stockbit.ext.property.orDefault
import id.stockbit.model.State
import id.stockbit.response.CoinResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding>() {

    private val viewModel: WatchlistViewModel by viewModel()

    private val mAdapter = CryptoAdapter()
    private lateinit var loadMoreListener: LinearLoadMoreListener

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@WatchlistFragment
            vm = viewModel

            refresh = true
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_watchlist
    }

    override fun initAPI() {
        viewModel.fetchTopCoins()
    }

    override fun setupAdapter() {
        binding.recyclerWatchlist.adapter = mAdapter
    }

    override fun setupObserver() {
        viewModel.run {
            observe(topCoins) {
                when (it) {
                    is State.Loading -> onProcess = true
                    is State.Success -> handleItems(it.data.filter { coin -> coin.display != null })
                    is State.Failure -> handleError(it.message)
                }
            }
        }
    }

    override fun setupListener() {
        binding.swipeRefresh.setOnRefreshListener {
            refresh()
        }

        binding.recyclerWatchlist.run {
            addOnScrollListener(object : LinearLoadMoreListener(layoutManager) {
                override fun isLoading(): Boolean {
                    return onProcess
                }

                override fun loadMoreItems(next: Int) {
                    viewModel.fetchTopCoins(next)
                }
            }.also { loadMoreListener = it })
        }
    }

    private fun refresh() {
        loadMoreListener.reset()
        binding.refresh = true

        viewModel.fetchTopCoins(Page.FIRST)
    }

    private fun stopRefreshing() {
        binding.run {
            if (refresh.orDefault()) refresh = false
            onProcess = false
        }
    }

    private fun handleItems(list: List<CoinResponse>) {
        mAdapter.run {
            if (loadMoreListener.isFirstPage()) replaceAll(list)
            else addAll(list)
        }

        stopRefreshing()
        loadMoreListener.nextPage()
    }

    private fun handleError(message: String?) {
        showToast(message)
        stopRefreshing()
    }

}