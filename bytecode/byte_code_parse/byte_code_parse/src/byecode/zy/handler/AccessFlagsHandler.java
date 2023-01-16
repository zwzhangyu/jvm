package byecode.zy.handler;

import byecode.zy.type.ClassFile;
import byecode.zy.type.U2;
import byecode.zy.util.ClassAccessFlagUtils;

import java.nio.ByteBuffer;

/**
 * 访问标志解析器解析
 */
public class AccessFlagsHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 3;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 u2 = new U2(codeBuf.get(), codeBuf.get());
        classFile.setAccess_flags(u2);
        System.out.println("解析获取类的访问标识：" + ClassAccessFlagUtils.toClassAccessFlagsString(u2));
    }

}
