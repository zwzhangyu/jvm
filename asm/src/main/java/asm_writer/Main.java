package asm_writer;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String className = "com.zy.AsmGenerateClass";
        String signature = "L" + className.replace(".", "/") + ";";
        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8, ACC_PUBLIC, className.replace(".", "/"),
                signature, Object.class.getName().replace(".", "/"), null);
        classWriter.visitEnd();
     //   MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
        generateMethod(classWriter);
       // methodVisitor.visitEnd();







        byte[] byteArray = classWriter.toByteArray();
        savaToFile(className, byteArray);

    }


    static void generateMethod(ClassWriter classWriter){
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>",
                "()V", null, null);
        methodVisitor.visitCode();

        // 调用父类构造器
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL,
                "java/lang/Object","<init>", "()V", false);
        // 添加一条返回指令
        methodVisitor.visitInsn(RETURN);
        // 设置操作数栈和局部变量表大小
        methodVisitor.visitMaxs(1,1);
        methodVisitor.visitEnd();
    }

    public static void savaToFile(String className, byte[] byteCode) throws IOException {
        File file = new File("/Users/zhangyu/code/zy/github/jvm/asm/src/main/java/demo2/" + className + ".class");
        if ((!file.exists() || file.delete()) && file.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(byteCode);
            }
        }
    }
}
