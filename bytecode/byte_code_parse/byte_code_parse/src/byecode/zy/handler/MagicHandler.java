package byecode.zy.handler;

import byecode.zy.type.ClassFile;
import byecode.zy.type.U4;

import java.nio.ByteBuffer;

/**
 * 魔数处理器
 * 魔数解析的实现很简单，只需要从class文件字节缓存中连续读取四个字节，将这四个字节转为U4对象，并赋值给ClassFile对象的magic字段
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public class MagicHandler implements BaseByteCodeHandler {

    /**
     * 每个.class文件开头JVM规定了需要以魔数开头
     * 魔数占四个字节，它只是用来确定这个文件是否是一个class文件。魔数固定值为0xCAFEBABE，这个值永远不会改变。
     */
    @Override
    public int order() {
        return 0;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 读取二进制数组中的4个字节数据
        classFile.setMagic(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
        // 判断是否是魔数，这个是判定是不是JVM规范要求的class文件，注意并不是只有java语言可以生成class文件，
        // 这是一个规范，其他语言也可以按照规范实现class文件
        if (!"0xCAFEBABE".equalsIgnoreCase(classFile.getMagic().toHexString())) {
            throw new Exception("这不是一个Class文件");
        }
        System.out.println("解析魔数正常");
    }
}
