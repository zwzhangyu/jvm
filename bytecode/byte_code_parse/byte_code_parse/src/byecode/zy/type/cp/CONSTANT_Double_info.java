package byecode.zy.type.cp;

import byecode.zy.type.U1;

/**
 * CONSTANT_Double_info常量与CONSTANT_Long_info常量在结构上也是一样的，只是所表示的值类型不同。
 * 因此CONSTANT_Double_info类的定义和解析方法的实现也可通过继承CONSTANT_Long_info实现
 */
public class CONSTANT_Double_info extends CONSTANT_Long_info {

    public CONSTANT_Double_info(U1 tag) {
        super(tag);
    }

    @Override
    public String toString() {
        return "CONSTANT_Double_info";
    }
}
