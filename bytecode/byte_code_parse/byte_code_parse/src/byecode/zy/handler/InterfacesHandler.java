package byecode.zy.handler;

import byecode.zy.type.ClassFile;
import byecode.zy.type.U2;
import byecode.zy.type.cp.CONSTANT_Class_info;
import byecode.zy.type.cp.CONSTANT_Utf8_info;

import java.nio.ByteBuffer;

/**
 * 解析实现的接口
 * 在读取class文件字节缓存时，先顺序读取到interfaces_count，interfaces_count是类实现的接口总数。
 * 再根据interfaces_count创建接口表interfaces，接口表的数组长度等于interfaces_count。
 * 接口表中的每项都是一个常量索引，指向常量池表中CONSTANT_Class_info结构的常量。
 */
public class InterfacesHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 5;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 获取接口总数
        classFile.setInterfaces_count(new U2(codeBuf.get(), codeBuf.get()));
        int interfacesCount = classFile.getInterfaces_count().toInt();
        if (interfacesCount > 0) {
            // 创建对应数量的接口数据结构数组
            U2[] interfaces = new U2[interfacesCount];
            classFile.setInterfaces(interfaces);
            for (int i = 0; i < interfacesCount; i++) {
                // 每个接口使用两个字节，接口表中的每项都是一个常量索引，指向常量池表中CONSTANT_Class_info结构的常量。
                interfaces[i] = new U2(codeBuf.get(), codeBuf.get());
            }
        }
        printClassFileInterfaceList(classFile);
    }

    /**
     * 解析实现的接口
     */
    private void printClassFileInterfaceList(ClassFile classFile) {
        System.out.println("===============解析实现的接口=============");
        System.out.println("接口总数:" + classFile.getInterfaces_count().toInt());
        if (classFile.getInterfaces_count().toInt() == 0) {
            return;
        }
        U2[] interfaces = classFile.getInterfaces();
        // 遍历接口表
        for (U2 interfacesIndex : interfaces) {
            // 根据索引从常量池中取得一个CONSTANT_Class_info常量
            CONSTANT_Class_info interfaces_class_info = (CONSTANT_Class_info) classFile.getConstant_pool()[interfacesIndex.toInt() - 1];
            // 根据CONSTANT_Class_info的name_index从常量池取得一个
            //  CONSTANT_Utf8_info常量
            CONSTANT_Utf8_info interfaces_class_name_info =
                    (CONSTANT_Utf8_info) classFile.getConstant_pool()
                            [interfaces_class_info.getName_index().toInt() - 1];
            System.out.println(interfaces_class_name_info);
        }
        System.out.println("===============解析实现的接口结束=============");
    }

}
