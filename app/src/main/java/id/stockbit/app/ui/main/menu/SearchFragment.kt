package id.stockbit.app.ui.main.menu

import id.stockbit.app.R
import id.stockbit.app.base.BaseFragment
import id.stockbit.app.databinding.FragmentChatBinding
import id.stockbit.app.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@SearchFragment
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_search
    }

}