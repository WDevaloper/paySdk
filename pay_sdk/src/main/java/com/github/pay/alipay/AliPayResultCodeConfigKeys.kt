package com.github.pay.alipay

enum class AliPayResultCodeConfigKeys(val resultCode: String) {
    //订单支付成功
    AL_PAY_STATUS_SUCCESS("9000"),
    //订单处理中
    AL_PAY_STATUS_PAYING("8000"),
    //订单支付失败
    AL_PAY_STATUS_FAIL("4000"),
    //用户取消
    AL_PAY_STATUS_CANCEL("6001"),
    //支付网络错误
    AL_PAY_STATUS_CONNECT_ERROR("6002")
}