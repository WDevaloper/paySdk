package com.github.pay.wechat


enum class WXPayResultConfigKeys(val code: Int) {
    /*展示成功页面*/
    WX_PAY_SUCCESS(0),

    /*可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等*/
    WX_PAY_FAIL(-1),

    /*无需处理。发生场景：用户不支付了，点击取消，返回APP。*/
    WX_PAY_CANCEL(-2)
}