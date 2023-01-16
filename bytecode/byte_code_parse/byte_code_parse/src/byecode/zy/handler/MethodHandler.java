package byecode.zy.handler;

import byecode.zy.type.*;
import byecode.zy.type.cp.CONSTANT_Utf8_info;
import byecode.zy.util.FieldAccessFlagUtils;

import java.nio.ByteBuffer;

/**
 * 解析方法表
 */
public class MethodHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        // 排在字段解析器的后面
        return 7;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setMethods_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取方法总数
        int len = classFile.getMethods_count().toInt();
        if (len == 0) {
            return;
        }
        // 创建方法表
        MethodInfo[] methodInfos = new MethodInfo[len];
        classFile.setMethods(methodInfos);
        for (int i = 0; i < methodInfos.length; i++) {
            // 解析方法
            methodInfos[i] = new MethodInfo();
            methodInfos[i].setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
            methodInfos[i].setName_index(new U2(codeBuf.get(), codeBuf.get()));
            methodInfos[i].setDescriptor_index(new U2(codeBuf.get(), codeBuf.get()));
            methodInfos[i].setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
            // 获取方法的属性总数
            int attr_len = methodInfos[i].getAttributes_count().toInt();
            if (attr_len == 0) {
                continue;
            }
            // 创建方法的属性表
            methodInfos[i].setAttributes(new AttributeInfo[attr_len]);
            for (int j = 0; j < attr_len; j++) {
                methodInfos[i].getAttributes()[j] = new AttributeInfo();
                // 解析方法的属性
                methodInfos[i].getAttributes()[j]
                        .setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
                // 获取属性info的长度
                U4 attr_info_len = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
                methodInfos[i].getAttributes()[j]
                        .setAttribute_length(attr_info_len);
                if (attr_info_len.toInt() == 0) {
                    continue;
                }
                // 解析info
                byte[] info = new byte[attr_info_len.toInt()];
                codeBuf.get(info, 0, attr_info_len.toInt());
                methodInfos[i].getAttributes()[j].setInfo(info);
            }
        }
        printMethodHandlerHandler(classFile);
    }


    private static String getName(U2 name_index, ClassFile classFile) {
        CONSTANT_Utf8_info name_info = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()[name_index.toInt() - 1];
        return name_info.toString();
    }

    public void printMethodHandlerHandler(ClassFile classFile) throws Exception {
        System.out.println("===============解析方法表=============");
        System.out.println("方法总数:" + classFile.getMethods_count().toInt());
        System.out.println();
        MethodInfo[] methodInfos = classFile.getMethods();
        // 遍历方法表
        for (MethodInfo methodInfo : methodInfos) {
            System.out.println("访问标志和属性：" + FieldAccessFlagUtils
                    .toFieldAccessFlagsString(methodInfo.getAccess_flags()));
            System.out.println("方法名：" + getName(methodInfo.getName_index(), classFile));
            System.out.println("方法描述符："
                    + getName(methodInfo.getDescriptor_index(), classFile));
            System.out.println("属性总数：" + methodInfo.getAttributes_count().toInt());
            System.out.println();
        }
        System.out.println("===============解析方法表结束=============");
    }
}
