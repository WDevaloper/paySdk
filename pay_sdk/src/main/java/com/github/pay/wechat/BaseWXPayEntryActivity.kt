package com.github.pay.wechat

import android.util.Log
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseResp

/**
 * @Describe:
 *
 * 在WXPayEntryActivity类中实现onResp函数，支付完成后，微信APP会返回到商户APP并回调onResp函数
 * ,开发者需要在该函数中接收通知，判断返回错误码，如果支付成功则去后台查询支付结果再展示用户实际支付结果。
 * 注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
 *
 * @author wfy
 *
 */
abstract class BaseWXPayEntryActivity : BaseWXActivity() {

    protected abstract fun onPaySuccess()

    protected abstract fun onPayFail()

    protected abstract fun onPayCancel()

    override fun onResp(baseResp: BaseResp) {
        if (baseResp.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            when (baseResp.errCode) {
                WXPayResultConfigKeys.WX_PAY_SUCCESS.code -> onPaySuccess()
                WXPayResultConfigKeys.WX_PAY_FAIL.code -> onPayFail()
                WXPayResultConfigKeys.WX_PAY_CANCEL.code -> onPayCancel()
                else -> {
                    Log.e("tag", "No Error Code!")
                }
            }
        }
    }
}
