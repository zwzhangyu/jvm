package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U4;

import java.nio.ByteBuffer;

/**
 * CONSTANT_Long_info常量使用8个字节存储一个长整型数值，即使用两个U4类型的字段分别存储一个长整型数的高32位和低32位。
 */
public class CONSTANT_Long_info extends CpInfo {

    private U4 hight_bytes;
    private U4 low_bytes;

    public CONSTANT_Long_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        hight_bytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
        low_bytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
    }

    public U4 getHight_bytes() {
        return hight_bytes;
    }

    public U4 getLow_bytes() {
        return low_bytes;
    }

    @Override
    public String toString() {
        return "CONSTANT_Long_info";
    }
}
