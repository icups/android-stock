package id.stockbit.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.stockbit.app.R
import id.stockbit.app.constant.Nav

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {

    private val appCompatActivity by lazy { activity as AppCompatActivity }
    val navController: NavController by lazy {
        Navigation.findNavController(requireActivity(), navResources())
    }

    lateinit var binding: VDB
    var onProcess: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResources(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArguments()

        setupAdapter()
        setupViewPager()

        setupListener()
        setupObserver()

        setupAnimation()

        onViewCreated()
        initAPI()
    }

    private fun setupAnimation() {
        if (!animateLayout()) return
        binding.root.run {
            animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
            animate()
        }
    }

    protected abstract fun onViewCreated()

    protected open fun setupArguments() {}
    protected open fun setupViewPager() {}
    protected open fun setupAdapter() {}
    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    protected open fun initAPI() {}

    protected open fun navResources(): Int = Nav.Main
    protected open fun animateLayout(): Boolean = false

    protected fun setupToolbar(toolbar: Toolbar) {
        appCompatActivity.run {
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            toolbar.setNavigationOnClickListener { onBackPressed() }
        }
    }

    fun finish() {
        activity?.finish()
    }

    @LayoutRes
    protected abstract fun layoutResources(): Int

}