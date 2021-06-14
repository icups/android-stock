package id.stockbit.app.ui.main.menu

import id.stockbit.app.R
import id.stockbit.app.base.BaseFragment
import id.stockbit.app.databinding.FragmentChatBinding
import id.stockbit.app.databinding.FragmentPortfolioBinding

class PortfolioFragment : BaseFragment<FragmentPortfolioBinding>() {

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@PortfolioFragment
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_portfolio
    }

}