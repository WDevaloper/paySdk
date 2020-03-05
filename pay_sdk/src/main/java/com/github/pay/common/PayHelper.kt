package com.github.pay.common

import android.app.Activity
import android.os.AsyncTask
import com.google.gson.Gson
import com.github.pay.alipay.AliPayAsyncTask
import com.github.pay.wechat.WXPayHelper
import com.github.pay.wechat.converter.WXJsonDataConverter
import java.lang.ref.SoftReference


/**
 *  @Describe 支付帮助类
 *
 *  @author wfy
 *
 */
class PayHelper private constructor(activity: Activity) {

    private val mActivityRef: SoftReference<Activity> = SoftReference(activity)
    /*
      设置支付回调监听
   */
    private var mIPayResultListenerRef: SoftReference<IPayResultListener>? = null

    fun setPayResultCallback(listener: IPayResultListener): PayHelper {
        if (this.mIPayResultListenerRef != null) this.mIPayResultListenerRef?.clear()
        this.mIPayResultListenerRef = SoftReference(listener)
        return this
    }

    /**
     * 支付宝，必须是异步的调用客户端支付接口
     *
     * @param paySign 签名参数
     *
     * */
    fun aliPay(paySign: String) {
        val payAsyncTask = AliPayAsyncTask(mActivityRef, mIPayResultListenerRef)
        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign)
    }


    /**
     *  微信支付：
     *  微信支付需要我们携带的参数，其实这些参数都是服务器返回给我们的，
     *  appId我们可以自己保存在本地一份，其余的都是服务器返回给我们的
     *
     *  @param content 签名参数
     *
     */
    fun wxPay(content: String) {
        mActivityRef.get()?.let {
            val converter = WXJsonDataConverter.create(content)
            val payReq = converter.converter()
            val payHelper = WXPayHelper.build(it).callback(mIPayResultListenerRef?.get())
            payHelper.WX_API.sendReq(payReq)
        }
    }


    /**
     * 统一支付入
     *
     * @param paySign 支付宝和微信支付参数
     *
     * */
    fun pay(payWay: PayWayConfigKeys, paySign: Any) {
        if (payWay == PayWayConfigKeys.ALI_PAY) return aliPay("$paySign")
        if (payWay == PayWayConfigKeys.WX_PAY) return wxPay(Gson().toJson(paySign))
    }

    companion object {
        @JvmStatic
        fun create(activity: Activity): PayHelper = PayHelper(activity)
    }
}
