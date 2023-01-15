package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * CONSTANT_NameAndType_info结构用于存储字段的名称和字段的类型描述符，或者是用于存储方法的名称和方法的描述符
 */
public class CONSTANT_NameAndType_info extends CpInfo {

    private U2 name_index;

    private U2 descriptor_index;

    public CONSTANT_NameAndType_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        name_index = new U2(codeBuf.get(), codeBuf.get());
        descriptor_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getName_index() {
        return name_index;
    }

    public U2 getDescriptor_index() {
        return descriptor_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_NameAndType_info";
    }

}
