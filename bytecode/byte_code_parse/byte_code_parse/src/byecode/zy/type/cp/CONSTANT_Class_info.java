package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;

/**
 * CONSTANT_Class_Info常量存储类的符号信息，除tag字段外，只有一个存储指向常量池表中某一常量的索引字段name_index，
 * name_index指向的常量必须是一个CONSTANT_Utf8_info常量，该常量存储class的类名
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public class CONSTANT_Class_info extends CpInfo {

    /**
     * 这里获取的索引需要去常量池中定位，定位的最终数据应该是CONSTANT_Utf8_info
     */
    private U2 name_index;

    public CONSTANT_Class_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.name_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getName_index() {
        return name_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_Class_info{" +
                "name_index=" + name_index.toInt() +
                '}';
    }

}
