package asm_reader;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassWriter extends ClassVisitor {

    private ClassWriter classWriter;

    public MyClassWriter(ClassWriter classWriter) {
        super(Opcodes.ASM6, classWriter);
        this.classWriter = classWriter;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if ("main".equals(name)) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new MainMethodWriter(methodVisitor);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public byte[] toByteArray() {
        return classWriter.toByteArray();
    }

}