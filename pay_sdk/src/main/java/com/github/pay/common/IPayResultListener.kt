package com.github.pay.common

import android.app.Activity
import android.widget.Toast


/**
 *  @Describe 支付回调接口
 *
 * @author wfy
 */
interface IPayResultListener {
    fun onPaySuccess() = Unit
    fun onPaying() = Unit
    fun onPayFail() = Unit
    fun onPayCancel(activity: Activity) {
        activity.runOnUiThread { Toast.makeText(activity, "支付已取消", Toast.LENGTH_SHORT).show() }
    }

    fun onPayConnectError() = Unit
}
