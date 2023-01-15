package byecode.zy.type;

import byecode.zy.type.cp.*;

/**
 * 常量池数据结构处理
 * 1.根据常量结构的通用格式将常量结构抽象出一个父类CpInfo
 * 2.每个常量类型具体实现处理策略
 * 3.CpInfo抽象类约定构建方法必须传入tag值，且约定子类必须实现ConstantInfoHandler常量结构解析器接口，并实现常量解析方法
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public abstract class CpInfo implements ConstantInfoHandler {

    private U1 tag;

    protected CpInfo(U1 tag) {
        this.tag = tag;
    }

    public U1 getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "tag=" + tag.toString();
    }

    /**
     * 根据当前常量类型，获取对应的常量实际数据结构，然后根据具体类型找到对应的子类去解析剩余的字节数据
     *
     * @param tag 当前常量类型
     */
    public static CpInfo newCpInfo(U1 tag) throws Exception {
        int tagValue = tag.toInt();
        CpInfo info;
        switch (tagValue) {
            case 1:
                info = new CONSTANT_Utf8_info(tag);
                break;
            case 3:
                info = new CONSTANT_Integer_info(tag);
                break;
            case 4:
                info = new CONSTANT_Float_info(tag);
                break;
            case 5:
                info = new CONSTANT_Long_info(tag);
                break;
            case 6:
                info = new CONSTANT_Double_info(tag);
                break;
            case 7:
                info = new CONSTANT_Class_info(tag);
                break;
            case 8:
                info = new CONSTANT_String_info(tag);
                break;
            case 9:
                info = new CONSTANT_Fieldref_info(tag);
                break;
            case 10:
                info = new CONSTANT_Methodref_info(tag);
                break;
            case 11:
                info = new CONSTANT_InterfaceMethodref_info(tag);
                break;
            case 12:
                info = new CONSTANT_NameAndType_info(tag);
                break;
            case 15:
                info = new CONSTANT_MethodHandle_info(tag);
                break;
            case 16:
                info = new CONSTANT_MethodType_info(tag);
                break;
            case 18:
                info = new CONSTANT_InvokeDynamic_info(tag);
                break;
            default:
                throw new Exception("没有找到该TAG=" + tagValue + "对应的常量类型");
        }
        return info;
    }

}
