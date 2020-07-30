package com.ravi.cookbook.ui.meals

import android.net.Uri
import android.os.Bundle
import kotlin.reflect.KClass

sealed class ActivityState{
    data class Explicit(val target: KClass<*>, val bundle: Bundle?, val finish: Boolean, val requestCode: String?, val anim: Pair<Int, Int>?) : ActivityState()
    data class Implicit(val action: String, val packages: String, val data: Uri?) : ActivityState()
}