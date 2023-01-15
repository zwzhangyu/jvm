package byecode.zy.type.cp;

import byecode.zy.type.U1;

/**
 * CONSTANT_InterfaceMethodref_info在结构上与CONSTANT_Fieldref_info一样，因此可通过继承CONSTANT_Fieldref_info类实现其字段的定义和完成解析工作。
 */
public class CONSTANT_InterfaceMethodref_info extends CONSTANT_Fieldref_info{

    public CONSTANT_InterfaceMethodref_info(U1 tag) {
        super(tag);
    }

    @Override
    public String toString() {
        return "CONSTANT_InterfaceMethodref_info";
    }
}
