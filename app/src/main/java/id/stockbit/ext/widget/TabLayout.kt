package id.stockbit.ext.widget

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun TabLayout.bindWithPager(pager: ViewPager2, titles: List<String> = emptyList()) {
    TabLayoutMediator(this, pager) { tab, position ->
        if (titles.isNotEmpty()) tab.text = titles[position]
    }.attach()
}