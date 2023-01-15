package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U4;

import java.nio.ByteBuffer;

/**
 * CONSTANT_Integer_info常量存储一个整型数值，除一个tag字段外，只有一个U4类型的字段bytes，bytes转为10进制数就是这个常量所表示的整型值
 */
public class CONSTANT_Integer_info extends CpInfo {

    private U4 bytes;

    public CONSTANT_Integer_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        bytes = new U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get());
    }

    public U4 getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "CONSTANT_Integer_info";
    }
}
