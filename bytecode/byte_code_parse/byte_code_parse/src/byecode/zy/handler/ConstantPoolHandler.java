package byecode.zy.handler;

import byecode.zy.type.ClassFile;
import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;
import byecode.zy.type.cp.CONSTANT_Long_info;

import java.nio.ByteBuffer;

/**
 * 解析常量池
 * 1.常量池表中所有常量项的结构都包含一个tag项，tag值用于标志一个常量是哪种常量结构
 * 2.只有根据tag确定常量的结构，才能根据常量结构计算常量所占用的字节数
 */
public class ConstantPoolHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        // 解析位置排在第三位
        return 2;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 cpLen = new U2(codeBuf.get(), codeBuf.get());
        classFile.setConstant_pool_count(cpLen);
        int cpInfoLeng = cpLen.toInt() - 1;
        classFile.setConstant_pool(new CpInfo[cpInfoLeng]);
        for (int i = 0; i < cpInfoLeng; i++) {
            U1 tag = new U1(codeBuf.get());
            CpInfo cpInfo = CpInfo.newCpInfo(tag);
            cpInfo.read(codeBuf);
            // System.out.println("#" + (i + 1) + ":" + cpInfo);
            classFile.getConstant_pool()[i] = cpInfo;
            if (cpInfo instanceof CONSTANT_Long_info) {
                i++; // jump n+2
            }
        }
    }

}
