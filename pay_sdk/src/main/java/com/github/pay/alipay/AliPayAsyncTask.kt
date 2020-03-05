package com.github.pay.alipay

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.alipay.sdk.app.PayTask
import com.github.pay.R
import com.github.pay.common.IPayResultListener
import java.lang.ref.SoftReference

/**
 * @Describe 支付宝异步处理
 *
 * @author wfy
 *
 */
internal class AliPayAsyncTask(
    private val mActivityRef: SoftReference<Activity>,
    private val mIAlPayResultListenerRef: SoftReference<IPayResultListener>?
) : AsyncTask<String, Void, Map<String, String>>() {

    override fun doInBackground(vararg params: String): Map<String, String> {
        val alPaySign = params[0]
        val payTask = PayTask(mActivityRef.get())
        return payTask.payV2(alPaySign, true)
    }


    override fun onPostExecute(result: Map<String, String>) {
        super.onPostExecute(result)
        val payResult = AliPayResult
            .create()
            .parseResult(result)
        when (payResult.resultStatus) {
            AliPayResultCodeConfigKeys.AL_PAY_STATUS_SUCCESS.resultCode -> {
                mIAlPayResultListenerRef?.get()?.onPaySuccess()
            }
            AliPayResultCodeConfigKeys.AL_PAY_STATUS_FAIL.resultCode -> {
                mIAlPayResultListenerRef?.get()?.onPayFail()
                Toast.makeText(
                    mActivityRef.get(),
                    mActivityRef.get()?.getString(R.string.pay_text_pay_failed) ?: "",
                    Toast.LENGTH_SHORT
                ).show()
            }
            AliPayResultCodeConfigKeys.AL_PAY_STATUS_PAYING.resultCode -> mIAlPayResultListenerRef?.get()?.onPaying()
            AliPayResultCodeConfigKeys.AL_PAY_STATUS_CANCEL.resultCode -> {
                val activity = mActivityRef.get()
                if (activity != null) {
                    mIAlPayResultListenerRef?.get()?.onPayCancel(activity)
                }
            }
            AliPayResultCodeConfigKeys.AL_PAY_STATUS_CONNECT_ERROR.resultCode -> {//网络连接错误
                mIAlPayResultListenerRef?.get()?.onPayConnectError()
                Toast.makeText(
                    mActivityRef.get(),
                    mActivityRef.get()?.getString(R.string.pay_text_pay_failed_reson) ?: "",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Log.e("tag", "AliPayAsyncTask Error Info: AliPay Not Found result code by ${payResult.resultStatus}")
            }
        }
    }
}
