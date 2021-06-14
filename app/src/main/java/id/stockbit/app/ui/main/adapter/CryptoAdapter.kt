package id.stockbit.app.ui.main.adapter

import id.stockbit.app.R
import id.stockbit.app.base.BaseSingleAdapter
import id.stockbit.app.databinding.ItemCryptoBinding
import id.stockbit.response.CoinResponse

class CryptoAdapter : BaseSingleAdapter<CoinResponse, ItemCryptoBinding>() {

    override fun onBind(binding: ItemCryptoBinding, data: CoinResponse, adapterPosition: Int) {
        binding.run {
            item = data.crypto
            currency = data.display?.currency
        }
    }

    override fun layoutResources(): Int {
        return R.layout.item_crypto
    }

    override fun animated(): Boolean {
        return true
    }

}