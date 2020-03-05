package com.github.paysdk.generators


import com.github.annotations.PayEntryGenerator
import com.github.pay.wechat.templates.WXPayEntryTemplate

/**
 * @author wfy
 * @Describe: 仅仅是为了生成代码,没有实际用途
 */
@PayEntryGenerator(payEntryTemplate = WXPayEntryTemplate::class)
interface WeChatPayEntry
