package com.github.pay.wechat

enum class WXPayParamConfigKeys(val value:String) {
    WX_APP_ID("appId"),
    WX_PREPAY_ID("prepayId"),
    WX_PARTNER_ID("partnerId"),
    WX_PACKAGE("wxPackage"),
    WX_TIME_STAMP("timestamp"),
    WX_NONCE_STR("nonceStr"),
    WX_SIGN("sign")
}