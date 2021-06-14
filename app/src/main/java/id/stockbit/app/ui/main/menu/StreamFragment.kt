package id.stockbit.app.ui.main.menu

import id.stockbit.app.R
import id.stockbit.app.base.BaseFragment
import id.stockbit.app.databinding.FragmentStreamBinding

class StreamFragment : BaseFragment<FragmentStreamBinding>() {

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@StreamFragment
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_stream
    }

}