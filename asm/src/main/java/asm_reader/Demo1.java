package asm_reader;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class Demo1 {

    public static void main(String[] args) throws IOException {
        String className = "asm_reader.HelloWorld";
        ClassReader classReader = new ClassReader(className);
        System.out.println(classReader.getClassName());
    }
}
