package id.stockbit.ext.widget

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setTrue(menuId: Int) {
    menu.findItem(menuId)?.isChecked = true
}