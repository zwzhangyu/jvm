package byecode.zy.handler;

import byecode.zy.type.*;
import byecode.zy.type.cp.CONSTANT_Utf8_info;
import byecode.zy.util.FieldAccessFlagUtils;

import java.nio.ByteBuffer;

/**
 * 字段解析器
 */
public class FieldHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        // 排在接口解析器的后面
        return 6;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setFields_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取字段总数
        int len = classFile.getFields_count().toInt();
        if (len == 0) {
            return;
        }
        // 创建字段表
        FieldInfo[] fieldInfos = new FieldInfo[len];
        classFile.setFields(fieldInfos);
        // 循环解析字段表
        for (int i = 0; i < fieldInfos.length; i++) {
            // 解析字段
            fieldInfos[i] = new FieldInfo();
            // 字段访问标识
            fieldInfos[i].setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
            // 字段名称
            fieldInfos[i].setName_index(new U2(codeBuf.get(), codeBuf.get()));
            // 字段描述符
            fieldInfos[i].setDescriptor_index(new U2(codeBuf.get(), codeBuf.get()));
            // 字段对应的属性长度
            fieldInfos[i].setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
            // 获取字段的属性总数
            int attrLen = fieldInfos[i].getAttributes_count().toInt();
            if (attrLen == 0) {
                continue;
            }
            // 创建字段的属性表
            fieldInfos[i].setAttributes(new AttributeInfo[attrLen]);
            for (int j = 0; j < attrLen; j++) {
                fieldInfos[i].getAttributes()[j] = new AttributeInfo();
                // 解析字段的属性
                fieldInfos[i].getAttributes()[j]
                        .setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
                // 获取属性info的长度
                U4 attr_info_len = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
                fieldInfos[i].getAttributes()[j]
                        .setAttribute_length(attr_info_len);
                if (attr_info_len.toInt() == 0) {
                    continue;
                }
                // 解析info
                byte[] info = new byte[attr_info_len.toInt()];
                codeBuf.get(info, 0, attr_info_len.toInt());
                fieldInfos[i].getAttributes()[j].setInfo(info);
            }
        }
        printFieldDesc(classFile);
    }

    /**
     * 打印输出解析的字段相关信息
     */
    private void printFieldDesc(ClassFile classFile) {
        System.out.println("==============打印输出解析的字段相关信息===================");
        U2 fieldsCount = classFile.getFields_count();
        System.out.println("字段数量：" + fieldsCount.toInt());
        FieldInfo[] fields = classFile.getFields();
        // 遍历字段表
        for (FieldInfo fieldInfo : fields) {
            System.out.println("访问标志和属性：" + FieldAccessFlagUtils
                    .toFieldAccessFlagsString(fieldInfo.getAccess_flags()));
            System.out.println("字段名："
                    + getName(fieldInfo.getName_index(), classFile));
            System.out.println("字段的类型描述符："
                    + getName(fieldInfo.getDescriptor_index(), classFile));
            System.out.println("属性总数："
                    + fieldInfo.getAttributes_count().toInt());
            System.out.println();
        }
        System.out.println("==============打印输出解析的字段相关信息结束===================");

    }

    private static String getName(U2 name_index, ClassFile classFile) {
        CONSTANT_Utf8_info name_info = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()[name_index.toInt() - 1];
        return name_info.toString();
    }
}
