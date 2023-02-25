package asm_writer;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class UseAsmCreateClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String className = "demo2.example.AsmGenerateClass";
        String signature = "L" + className.replace(".", "/") + ";";
        // 字节计算局部变量表和操作数栈大小、栈映射桢
        ClassWriter classWriter = new ClassWriter(0);
        // 类名和父类名需要使用 “/”替换“.”。这个可以在常量池中找到答案
        classWriter.visit(Opcodes.V1_8, ACC_PUBLIC,
                className.replace(".", "/"),
                signature,
                Object.class.getName().replace(".", "/"),
                null);
        generateMethod(classWriter);
        generateField(classWriter);
        classWriter.visitEnd();
        // 获取生成的class的字节数组
        byte[] byteCode = classWriter.toByteArray();
        ByteCodeUtils.savaToFile(className, byteCode);
    }


    static void generateMethod(ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>",
                "()V", null, null);
        methodVisitor.visitCode();

        // 调用父类构造器
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL,
                "java/lang/Object", "<init>", "()V", false);
        // 添加一条返回指令
        methodVisitor.visitInsn(RETURN);
        // 设置操作数栈和局部变量表大小
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
    }

    /**
     * 添加字段
     */
    static void generateField(ClassWriter classWriter) {
        FieldVisitor fieldVisitor = classWriter.visitField(ACC_PRIVATE, "name", "Ljava/java/Sting", null, null);
        fieldVisitor.visitAnnotation("Llombok/Getter", false);

    }

}
