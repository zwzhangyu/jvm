package byecode.zy.test;


import byecode.zy.ClassFileAnalysiser;
import byecode.zy.type.ClassFile;
import byecode.zy.type.CpInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * 字节码class文件解析测试类
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public class ClassFileAnalysisMain {

    /**
     * 测试类1
     */
    private final static String TEXT_CLASS1 = "/Users/zhangyu/code/zy/github/jvm/bytecode/byte_code_parse/byte_code_parse/out/production/byte_code_parse/byecode/zy/test/Demo.class";

    public static ByteBuffer readFile(String classFilePath) throws Exception {
        File file = new File(classFilePath);
        if (!file.exists()) {
            throw new Exception("file not exists!");
        }
        byte[] byteCodeBuf = new byte[4096];
        int lenght;
        try (InputStream in = new FileInputStream(file)) {
            lenght = in.read(byteCodeBuf);
        }
        if (lenght < 1) {
            throw new Exception("not read byte code.");
        }
        return ByteBuffer.wrap(byteCodeBuf, 0, lenght)
                .asReadOnlyBuffer();
    }

    public static void main(String[] args) throws Exception {
        ByteBuffer codeBuf = readFile(TEXT_CLASS1);
        ClassFile classFile = ClassFileAnalysiser.analysis(codeBuf);
        checkCp(classFile);
    }

    private static void checkCp(ClassFile classFile) {
        CpInfo[] cps = classFile.getConstant_pool();
        int index = 1;
        for (CpInfo cpInfo : cps) {
            if (cpInfo == null) {
                index++;
                continue;
            }
            System.out.println("#" + index + "==>" + cpInfo.getClass().getName());
            index++;
        }
    }

}
