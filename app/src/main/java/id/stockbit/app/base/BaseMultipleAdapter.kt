package id.stockbit.app.base

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import id.stockbit.ext.inflater.getLayoutInflater

abstract class BaseMultipleAdapter<T, VH : RecyclerView.ViewHolder>(private val mList: MutableList<T> = ArrayList()) : RecyclerView.Adapter<VH>() {

    @SuppressWarnings("unused")
    lateinit var adapterContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: ViewDataBinding = DataBindingUtil.inflate(getLayoutInflater(parent), viewType, parent, false)
        return onCreate(binding.also { adapterContext = it.root.context })
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBind(mList[position], holder, getItemViewType(position))
    }

    fun initializeData(list: List<T>) {
        mList.clear()
        mList.addAll(list)
    }

    protected abstract fun setupItemId(data: T): Int
    protected abstract fun setupItemViewType(data: T): Int

    protected abstract fun onCreate(binding: ViewDataBinding): VH
    protected abstract fun onBind(data: T, holder: VH, viewType: Int)

    override fun getItemId(position: Int): Long {
        return try {
            setupItemId(mList[position]).toLong()
        } catch (e: Exception) {
            0L
        }
    }

    override fun getItemViewType(position: Int): Int {
        return setupItemViewType(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}