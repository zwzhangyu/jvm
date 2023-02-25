package demo1;


/**
 * 简单版ASM框架生成一个类，为该类添加一个字段并为该字段添加一个注解，
 * 然后为该类添加一个方法并设置该方法的局部变量表和操作数栈的大小
 *
 * @author zhangyu
 * @date 2023/2/25
 */
public class Main {
    public static void main(String[] args) {
        ClassWriter classWriter = new ClassWriter();
        classWriter.visit(52, "public", "com.wujiuye.User");
// 添加字段
        FieldVisitor fieldVisitor = classWriter
                .visitField("private", "name", "Ljava/lang/String;");
        // 为字段添加注解
        fieldVisitor.visitAnnotation("@Getter", true);
// 添加方法
        MethodVisitor methodVisitor = classWriter
                .visitMethod("public", "getName", "(Ljava/lang/String)V");
        // 设置局部变量表和操作数栈的大小
        methodVisitor.visitMaxs(1, 1);
        classWriter.showClass();
    }
}
