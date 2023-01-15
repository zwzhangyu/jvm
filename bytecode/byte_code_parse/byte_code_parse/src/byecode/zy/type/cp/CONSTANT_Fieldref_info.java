package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * 解析对应的就是类中的成员变量
 * CONSTANT_Fieldref_info常量存储字段的符号信息，
 * 除tag字段外，有两个U2类型的指向常量池中某个常量的索引字段，分别是class_index、name_and_type_index。
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public class CONSTANT_Fieldref_info extends CpInfo {

    private U2 class_index;

    private U2 name_and_type_index;

    public CONSTANT_Fieldref_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        class_index = new U2(codeBuf.get(), codeBuf.get());
        name_and_type_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getClass_index() {
        return class_index;
    }

    public U2 getName_and_type_index() {
        return name_and_type_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_Fieldref_info";
    }

}
