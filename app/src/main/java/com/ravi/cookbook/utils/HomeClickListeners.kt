package com.ravi.cookbook.utils

import android.view.View
import com.ravi.cookbook.ui.home.HomeViewModel

interface HomeClickListeners {
    fun onClickBtnSearch(view : View, viewModel: HomeViewModel)
}