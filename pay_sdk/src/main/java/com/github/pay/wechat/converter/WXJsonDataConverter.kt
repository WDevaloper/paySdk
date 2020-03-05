package com.github.pay.wechat.converter

import com.tencent.mm.opensdk.modelpay.PayReq
import com.github.pay.wechat.WXPayParamConfigKeys
import org.json.JSONObject

/**
 * 微信数据转换
 */
class WXJsonDataConverter private constructor(private val jsonStr: String) : IDataConverter {
    companion object {
        fun create(data: String): IDataConverter {
            return WXJsonDataConverter(data)
        }
    }

    override fun converter(): PayReq {
        val jsonObject = JSONObject(jsonStr)
        val payReq = PayReq()
        payReq.appId = jsonObject.getString(WXPayParamConfigKeys.WX_APP_ID.value)
        payReq.prepayId = jsonObject.getString(WXPayParamConfigKeys.WX_PREPAY_ID.value)
        payReq.partnerId = jsonObject.getString(WXPayParamConfigKeys.WX_PARTNER_ID.value)
        payReq.packageValue = jsonObject.getString(WXPayParamConfigKeys.WX_PACKAGE.value)
        payReq.timeStamp = jsonObject.getString(WXPayParamConfigKeys.WX_TIME_STAMP.value)
        payReq.nonceStr = jsonObject.getString(WXPayParamConfigKeys.WX_NONCE_STR.value)
        payReq.sign = jsonObject.getString(WXPayParamConfigKeys.WX_SIGN.value)
        return payReq
    }
}