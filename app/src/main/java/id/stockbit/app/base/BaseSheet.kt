package id.stockbit.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.stockbit.app.R
import id.stockbit.ext.common.launchDelayedFunction

abstract class BaseSheet<VDB : ViewDataBinding> : BottomSheetDialogFragment() {

    lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, styleResource())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArgument()
        setupView()

        setupAdapter()
        setupObserver()

        initView()
        initAPI()

        launchDelayedFunction { setupListener() }
    }

    protected open fun initView() {}
    protected open fun initAPI() {}

    protected open fun setupArgument() {}

    protected open fun setupView() {}
    protected open fun setupAdapter() {}

    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    @StyleRes
    protected open fun styleResource(): Int {
        return R.style.AppTheme_Sheet
    }

    protected fun disableTouchOutside() {
        dialog?.window?.decorView?.findViewById<View>(com.google.android.material.R.id.touch_outside)?.setOnClickListener(null)
    }

    @LayoutRes
    protected abstract fun layoutResource(): Int

}