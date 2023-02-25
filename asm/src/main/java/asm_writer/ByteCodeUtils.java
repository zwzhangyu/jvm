package asm_writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteCodeUtils {
    public static void savaToFile(String className, byte[] byteCode) throws IOException {
        File file = new File("/Users/zhangyu/code/zy/github/jvm/asm/src/main/java/temp/" + className + ".class");
        if ((!file.exists() || file.delete()) && file.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(byteCode);
            }
        }
    }
}
