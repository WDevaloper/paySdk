package com.github.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

import java.io.IOException;

/**
 * @author wfy
 * @Describe: 支付入口注解访问器
 */
final class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private final Filer FILER;
    private String mPackageName;

    PayEntryVisitor(Filer FILER, String wxPackageName) {
        this.FILER = FILER;
        this.mPackageName = wxPackageName;
    }

    @Override
    public Void visitString(String s, Void p) {
        if (mPackageName == null || "".equals(mPackageName)) return p;
        mPackageName = s;
        return p;
    }


    /**
     * 这些visitXXX的方法，就要看你需要处理什么类型，那么你就实现什么类型，就这么简单
     * <p>
     * 这个方法意思就是：访问注解中的什么类型
     *
     * @param t 类型环境
     * @param p 返回值类型
     * @return Void
     */
    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity = TypeSpec.classBuilder(Constants.WXPAY_ENTRY_ACTIVITY)
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(typeMirror))
                .build();
        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信支付入口文件")
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
