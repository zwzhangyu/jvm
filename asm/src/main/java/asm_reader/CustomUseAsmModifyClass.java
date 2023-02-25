package asm_reader;

import asm_writer.ByteCodeUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

public class CustomUseAsmModifyClass {
        public static void main(String[] args) throws IOException {
            String className = "asm_reader.HelloWorld";
            ClassReader classReader = new ClassReader(className);
            // 创建MyClassWriter实例
            MyClassWriter classWriter = new MyClassWriter(new ClassWriter(0));
            classReader.accept(classWriter, 0);

            byte[] newByteCode = classWriter.toByteArray();
            ByteCodeUtils.savaToFile(className, newByteCode);
        }
}
