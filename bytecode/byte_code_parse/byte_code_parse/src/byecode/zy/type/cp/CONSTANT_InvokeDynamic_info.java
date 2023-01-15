package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * CONSTANT_InvokeDynamic_info表示invokedynamic指令用到的引导方法bootstrap method以及引导方法所用到的动态调用名称、参数、返回类型
 */
public class CONSTANT_InvokeDynamic_info extends CpInfo {

    private U2 bootstrap_method_attr_index;
    private U2 name_and_type_index;

    public CONSTANT_InvokeDynamic_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        bootstrap_method_attr_index = new U2(codeBuf.get(), codeBuf.get());
        name_and_type_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getName_and_type_index() {
        return name_and_type_index;
    }

    public U2 getBootstrap_method_attr_index() {
        return bootstrap_method_attr_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_InvokeDynamic_info";
    }
}
