package com.github.pay.wechat.templates

import android.widget.Toast
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.github.pay.R
import com.github.pay.wechat.BaseWXPayEntryActivity
import com.github.pay.wechat.WXPayHelper

/**
 * @Describe 生成代码WXPayEntryActivity的父类
 *
 * @author wfy
 *
 *
 */
 open class WXPayEntryTemplate : BaseWXPayEntryActivity() {

    override fun onPaySuccess() {
        WXPayHelper.build(this).callback()?.get()?.onPaySuccess()
        //不想看到微信的支付完成页面，取消动画
        finish()
        overridePendingTransition(0, 0)
    }

    override fun onPayFail() {
        WXPayHelper.build(this).callback()?.get()?.onPayFail()
        Toast.makeText(this, getString(R.string.pay_text_failed), Toast.LENGTH_SHORT).show()
        finish()
        overridePendingTransition(0, 0)
    }

    override fun onPayCancel() {
        WXPayHelper.build(this).callback()?.get()?.onPayCancel(this)
        finish()
        overridePendingTransition(0, 0)
    }

    override fun onReq(baseReq: BaseReq) {}
}
