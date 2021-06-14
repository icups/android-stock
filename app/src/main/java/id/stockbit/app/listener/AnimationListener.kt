package id.stockbit.app.listener

import android.view.animation.Animation

abstract class AnimationListener : Animation.AnimationListener {
    override fun onAnimationStart(anim: Animation?) {}
    override fun onAnimationRepeat(anim: Animation?) {}
    override fun onAnimationEnd(anim: Animation?) {}
}