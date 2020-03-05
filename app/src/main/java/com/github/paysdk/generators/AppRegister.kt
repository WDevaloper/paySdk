package com.github.paysdk.generators


import com.github.annotations.AppRegisterGenerator
import com.github.pay.wechat.templates.AppRegisterTemplate

/**
 * @author wfy
 * @Describe: 仅仅是为了生成代码,没有实际用途
 */
@AppRegisterGenerator(registerTemplate = AppRegisterTemplate::class)
interface AppRegister
