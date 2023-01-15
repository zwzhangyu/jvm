package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * CONSTANT_String_info
 * CONSTANT_String_info结构存储Java中String类型的常量，
 * 除tag字段外，还有一个U2类型的字段string_index，值为常量池中某个常量的索引，该索引指向的常量必须是一个CONSTANT_Utf8_info常量。
 */
public class CONSTANT_String_info extends CpInfo {

    private U2 string_index;

    public CONSTANT_String_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        string_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getString_index() {
        return string_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_String_info";
    }
}
