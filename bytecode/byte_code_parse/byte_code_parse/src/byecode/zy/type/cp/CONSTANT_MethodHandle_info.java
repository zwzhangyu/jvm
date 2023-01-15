package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * CONSTANT_MethodHandle_info结构用于存储方法句柄，这是虚拟机为实现动态调用invokedynamic指令所增加的常量结构。
 * CONSTANT_MethodHandle_info结构除必须的tag字段外，有一个U1类型的字段reference_kind，
 * 取值范围为1~9，包括1和9，表示方法句柄的类型，还有一个U1类型的字段reference_index，其值为指向常量池中某个常量的索引。
 */
public class CONSTANT_MethodHandle_info extends CpInfo {

    private U1 reference_kind;
    private U2 reference_index;

    public CONSTANT_MethodHandle_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        reference_kind = new U1(codeBuf.get());
        reference_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U1 getReference_kind() {
        return reference_kind;
    }

    public U2 getReference_index() {
        return reference_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_MethodHandle_info";
    }
}
