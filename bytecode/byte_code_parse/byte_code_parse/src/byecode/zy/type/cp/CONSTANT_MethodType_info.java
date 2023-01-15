package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * CONSTANT_MethodType_info结构表示方法类型，与CONSTANT_MethodHandle_info结构一样，
 * 也是虚拟机为实现动态调用invokedynamic指令所增加的常量结构。
 */
public class CONSTANT_MethodType_info extends CpInfo {

    private U2 descriptor_index;

    public CONSTANT_MethodType_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        descriptor_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getDescriptor_index() {
        return descriptor_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_MethodType_info";
    }
}
