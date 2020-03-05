package com.github.pay.wechat

import android.app.Activity
import android.content.Context
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.github.pay.common.IPayResultListener
import java.lang.ref.SoftReference

/**
 * @Describe 微信支付帮助类
 *
 * @author wfy
 *
 */
internal class WXPayHelper private constructor(context: Context) {
    val WX_API: IWXAPI

    private var mIPayResultListenerRef: SoftReference<IPayResultListener>? = null


    fun callback() = mIPayResultListenerRef

    fun callback(callback: IPayResultListener?): WXPayHelper {
        mIPayResultListenerRef?.let { if (it.get() != null) it.clear() }
        callback?.let { mIPayResultListenerRef = SoftReference(it) }
        return this
    }

    init {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        WX_API = WXAPIFactory.createWXAPI(context, WX_APP_ID, true)
        // 将应用的appId注册到微信
        WX_API.registerApp(WX_APP_ID)
    }

    companion object {
        private var INSTANCE: WXPayHelper? = null
        /*WX_APP_ID 替换为你的应用从官方网站申请到的合法appID*/
        const val WX_APP_ID = "wx22ee3b56bd261e8c"

        fun build(activity: Activity): WXPayHelper {
            if (INSTANCE == null) {
                synchronized(WXPayHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = WXPayHelper(activity)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
