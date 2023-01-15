package byecode.zy.type.cp;

import byecode.zy.type.U1;

/**
 * CONSTANT_Float_info与CONSTANT_Integer_info在存储结构上是一样的，只是bytes所表示的内容不同，CONSTANT_Float_info的bytes存储的是浮点数。
 */
public class CONSTANT_Float_info extends CONSTANT_Integer_info {

    public CONSTANT_Float_info(U1 tag) {
        super(tag);
    }

    @Override
    public String toString() {
        return "CONSTANT_Float_info";
    }

}
