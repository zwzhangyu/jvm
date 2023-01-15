package byecode.zy.type.cp;

import byecode.zy.type.CpInfo;
import byecode.zy.type.U1;
import byecode.zy.type.U2;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * UTF8编码字符串，保存了具体的常量字面量，比如方法名称，类名称，方法全限定名等等
 * CONSTANT_Utf8_info常量结构用于存储字符串常量，字符串编码使用UTF-8。
 * 除一个必须的tag字段和存储字符串的字节数组外，还要有一个字段存储描述这个字符串字节数组的长度，字符串实际上是通过字节数组存储的
 *
 * @author zhangyu
 */
public class CONSTANT_Utf8_info extends CpInfo {

    private U2 length;

    private byte[] bytes;

    public CONSTANT_Utf8_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // read方法在从Class文件字节缓存ByteBuffer对象中读取该类型的常量时，需按顺序先读取长度，再根据长度n取后续n个字节存放到该常量的字节数组中
        length = new U2(codeBuf.get(), codeBuf.get());
        bytes = new byte[length.toInt()];
        codeBuf.get(bytes, 0, length.toInt());
        System.out.println("解析常量池字符串字面量：" + new String(bytes));
    }

    public U2 getLength() {
        return length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return super.toString() +
                ",length=" + length.toInt() +
                ",str=" + new String(bytes, StandardCharsets.UTF_8);
    }

}
