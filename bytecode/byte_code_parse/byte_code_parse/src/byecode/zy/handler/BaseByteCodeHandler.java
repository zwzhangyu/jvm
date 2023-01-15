package byecode.zy.handler;

import byecode.zy.type.ClassFile;

import java.nio.ByteBuffer;


/**
 * 1.针对抽象出class文件结构各项的解析器
 * 2.class文件结构中各项的类型，如常量池、字段表、方法表、属性表、U2、U4，再定义各项的解析器实现当前接口，并实现各个类型的处理逻辑
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public interface BaseByteCodeHandler {

    /**
     * 排序，根据排序先后顺序对Class文件各个数据结构进行解析
     */
    int order();

    /**
     * 读取字节码数据
     * 1.BaseByteCodeHandler接口只定义了一个read方法，该方法要求传入class文件的字节缓存和ClassFile对象。
     * 2.class文件字节缓存是指从class文件读入内存的字节缓存，这是一个数组，大小即为class文件的大小。
     * 3.read方法将从字节缓存中读取相应的字节数据写入ClassFile对象。由于解析是按顺序解析的，
     * 4.因此BaseByteCodeHandler接口还定义了一个返回排序值的方法，用于实现解析器排序，比如版本号解析器排在魔数解析器的后面。
     * 5.读取的class文件就是.java文件编译后的.class文件，实现排序是因为.class文件中的二进制代码都是按照JVM规范实现的，
     * 因此我们解析只要根据数据结构类型和数据内容解析就可以还原Class源码信息
     *
     * @param codeBuf   缓存数据
     * @param classFile Class文件
     */
    void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception;

}
