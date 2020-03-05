package com.github.pay.dialog

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import com.github.pay.R
import com.github.pay.common.PayWayConfigKeys
import kotlinx.android.synthetic.main.pay_layout_payway_dialog.view.*
import razerdp.basepopup.BasePopupWindow

class DefaultPayWayPopup(context: Context?, private val mPayWayCallback: (PayWayConfigKeys) -> Unit) :
    BasePopupWindow(context) {

    override fun onCreateContentView(): View = createPopupById(R.layout.pay_layout_payway_dialog).also {
        initView(it)
    }

    private fun initView(contentView: View) {
        contentView.mCloseIv.setOnClickListener { dismiss() }
        contentView.mLLAliPay.setOnClickListener {
            dismiss()
            mPayWayCallback.invoke(PayWayConfigKeys.ALI_PAY)
        }
        contentView.mWXAliPay.setOnClickListener {
            dismiss()
            mPayWayCallback.invoke(PayWayConfigKeys.WX_PAY)
        }
    }


    override fun onCreateShowAnimation(): Animation {
        return AnimationSet(false).also {
            it.addAnimation(createVerticalAnimation(1f, 0f))
            it.addAnimation(getDefaultAlphaAnimation(true))
        }
    }

    override fun onCreateDismissAnimation(): Animation {
        return AnimationSet(false).also {
            it.addAnimation(createVerticalAnimation(0f, 1f))
            it.addAnimation(getDefaultAlphaAnimation(false))
        }
    }

    private fun createVerticalAnimation(fromY: Float, toY: Float): Animation {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            fromY,
            Animation.RELATIVE_TO_SELF,
            toY
        )
        animation.duration = 500
        animation.interpolator = DecelerateInterpolator()
        return animation
    }

}