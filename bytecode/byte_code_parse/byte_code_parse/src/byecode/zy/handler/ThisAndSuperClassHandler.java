package byecode.zy.handler;

import byecode.zy.type.ClassFile;
import byecode.zy.type.U2;
import byecode.zy.type.cp.CONSTANT_Class_info;
import byecode.zy.type.cp.CONSTANT_Utf8_info;

import java.nio.ByteBuffer;

/**
 * 在Class文件结构中，紧挨着访问标志access_flags项的是this_class和super_class这两项，也都是U2类型。
 * this_class存储的是常量池中某项常量的索引，super_class要么为0，要么也是存储常量池中某项常量的索引。
 * this_class和super_class指向的常量必须是一个CONSTANT_Class_info结构的常量。
 */
public class ThisAndSuperClassHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setThis_class(new U2(codeBuf.get(), codeBuf.get()));
        classFile.setSuper_class(new U2(codeBuf.get(), codeBuf.get()));
        printClassThisAndSuperDesc(classFile);
    }

    /**
     * 输出ClassFile中this与super项对应的常量项描述
     * 在解析获取到this_class与super_class之后，我们就可以先根据this_class的值到常量池取得对应的CONSTANT_Class_info常量，
     * 再从取得的CONSTANT_Class_info常量中获取该常量的name_index的值，
     * 最后根据name_index再回到常量池中取得对应的CONSTANT_Utf8_info常量，这样就能获取到具体的类名了
     */
    public void printClassThisAndSuperDesc(ClassFile classFile) {
        System.out.println("==============输出ClassFile中this与super项对应的常量项描述===================");
        U2 thisClass = classFile.getThis_class();
        // 根据this_class到常量池获取CONSTANT_Class_info常量
        // 由于常量池的索引是从1开始的，所以需要将索引减1取得数组下标
        CONSTANT_Class_info this_class_cpInfo =
                (CONSTANT_Class_info) classFile.getConstant_pool()[thisClass.toInt() - 1];
        CONSTANT_Utf8_info this_class_name = (CONSTANT_Utf8_info) classFile.getConstant_pool()
                [this_class_cpInfo.getName_index().toInt() - 1];
        System.out.println(this_class_name);
        // super_class
        U2 super_class = classFile.getSuper_class();
        CONSTANT_Class_info super_class_cpInfo = (CONSTANT_Class_info)
                classFile.getConstant_pool()[super_class.toInt() - 1];

        CONSTANT_Utf8_info supor_class_name = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()
                        [super_class_cpInfo.getName_index().toInt() - 1];
        System.out.println(supor_class_name);
        System.out.println("==============输出ClassFile中this与super项对应的常量项描述结束===================");
    }

}
