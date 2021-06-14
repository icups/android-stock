package id.stockbit.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.stockbit.app.constant.Nav
import id.stockbit.ext.activity.adjustFontScale
import id.stockbit.ext.view.closeKeyboard
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel

abstract class BaseActivity<VM : ViewModel, VDB : ViewDataBinding>(viewModelClass: Class<VM>) : AppCompatActivity() {

    @Suppress("LeakingThis")
    open val viewModel: VM by viewModel(this@BaseActivity, viewModelClass)

    val navController: NavController by lazy {
        Navigation.findNavController(this@BaseActivity, navResources())
    }

    lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)

        setupArguments()

        attachViewDataBinding()
        setupStatusBar()

        setupViewPager()
        setupAdapter()

        setupListener()
        setupObserver()

        onViewCreated()
        initAPI()
    }

    private fun setupStatusBar() {
        window.apply {
            if (fullStatusBar()) {
                setFlags(android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            } else {
                clearFlags(android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
        }
    }

    private fun attachViewDataBinding() {
        DataBindingUtil.setContentView<VDB>(this@BaseActivity, layoutResources()).apply {
            setFinishOnTouchOutside(false)
            binding = this
        }
    }

    protected abstract fun onViewCreated()

    protected open fun setupArguments() {}
    protected open fun setupAdapter() {}
    protected open fun setupViewPager() {}

    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    protected open fun initAPI() {}

    protected open fun navResources(): Int = Nav.Main
    protected open fun fullStatusBar(): Boolean = false

    protected fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun hideSoftKeyboard() {
        binding.root.closeKeyboard()
    }

    @LayoutRes
    protected abstract fun layoutResources(): Int

}