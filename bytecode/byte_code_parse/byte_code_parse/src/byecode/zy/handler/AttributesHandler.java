package byecode.zy.handler;

import byecode.zy.type.AttributeInfo;
import byecode.zy.type.ClassFile;
import byecode.zy.type.U2;
import byecode.zy.type.U4;

import java.nio.ByteBuffer;

/**
 * 解析class文件的属性表
 * 1.字段结构和方法结构也都有属性表，所以要注意不要将这些属性表混在一起理解。
 * 2.所有属性都有一个通用的结构，因此，解析class文件结构的属性表我们也可以使用通用的属性结构来解析。
 * 3.解析步骤是先从class文件字节缓存中读取两个字节，如果前面的解析工作都正常，那么现在读取到的这两个字节就是该class文件属性表的长度。
 * 4.接着根据长度创建属性表，使用通用属性结构循环解析出每个属性，循环次数为属性的总数
 */
public class AttributesHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 8;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取属性总数
        int len = classFile.getAttributes_count().toInt();
        if (len == 0) {
            return;
        }
        // 创建属性表
        AttributeInfo[] attributeInfos = new AttributeInfo[len];
        classFile.setAttributes(attributeInfos);
        for (int i = 0; i < len; i++) {
            // 创建属性
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfos[i] = attributeInfo;
            // 解析属性
            attributeInfo.setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
            attributeInfo.setAttribute_length(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
            int attr_len = attributeInfo.getAttribute_length().toInt();
            if (attr_len == 0) {
                continue;
            }
            // 解析属性的info项
            byte[] bytes = new byte[attr_len];
            attributeInfo.setInfo(bytes);
            codeBuf.get(bytes, 0, bytes.length);
        }
    }

}
