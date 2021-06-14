package id.stockbit.app.ui.main.menu

import id.stockbit.app.R
import id.stockbit.app.base.BaseFragment
import id.stockbit.app.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@ChatFragment
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_chat
    }

}