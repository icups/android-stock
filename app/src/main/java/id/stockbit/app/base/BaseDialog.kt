package id.stockbit.app.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import id.stockbit.app.R


@SuppressLint("Registered")
abstract class BaseDialog<VDB : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        attachViewDataBinding()

        setupListener()
        setupObserver()

        onViewCreated()
    }

    private fun attachViewDataBinding() {
        DataBindingUtil.setContentView<VDB>(this@BaseDialog, layoutResources()).apply {
            setFinishOnTouchOutside(false)
            binding = this
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
    }

    protected open fun onViewCreated() {}

    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    protected abstract fun layoutResources(): Int

}