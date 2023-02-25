package asm_reader;

import asm_writer.ByteCodeUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PRIVATE;

public class UseAsmModifyClass {

    public static void main(String[] args) throws IOException {
        String className = "asm_reader.HelloWorld";
        ClassReader classReader = new ClassReader(className);
        ClassWriter classWriter = new ClassWriter(0);
        classReader.accept(classWriter, 0);
        generateField(classWriter);
        byte[] newByteCode = classWriter.toByteArray();
        ByteCodeUtils.savaToFile(className, newByteCode);
    }

    static void generateField(ClassWriter classWriter) {
        FieldVisitor fieldVisitor = classWriter.visitField(ACC_PRIVATE,
                "name", "Ljava/lang/String;", null, null);
        fieldVisitor.visitAnnotation("Llombok/Getter;", false);
    }
}
