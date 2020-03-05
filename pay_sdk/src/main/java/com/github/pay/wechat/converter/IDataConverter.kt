package com.github.pay.wechat.converter

import com.tencent.mm.opensdk.modelpay.PayReq

interface IDataConverter {
    fun converter(): PayReq
}