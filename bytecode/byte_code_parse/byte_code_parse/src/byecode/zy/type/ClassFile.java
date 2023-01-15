package byecode.zy.type;

/**
 * 1.字节码Class文件数据结构，ClassFile类中的每个字段是按照class文件结构中各项的顺序声明的
 * 2.以大写U开头和数字表示对应的数据结构占用的字节数，U1一个字节，U2两个字节...
 *
 * @author zhangyu
 * @date 2023/1/15
 */
public class ClassFile {

    // 魔数
    private U4 magic;
    // 副版本号
    private U2 minor_version;
    // 主版本号
    private U2 magor_version;
    // 常量池计数器
    private U2 constant_pool_count;
    // 常量池
    private CpInfo[] constant_pool;
    // 访问标志
    private U2 access_flags;
    // 类索引
    private U2 this_class;
    // 父类索引
    private U2 super_class;
    // 接口总数
    private U2 interfaces_count;
    // 接口数组
    private U2[] interfaces;
    // 字段总数
    private U2 fields_count;
    // 字段表
    private FieldInfo[] fields;
    // 方法总数
    private U2 methods_count;
    // 方法表
    private MethodInfo[] methods;
    // 属性总数
    private U2 attributes_count;
    // 属性表
    private AttributeInfo[] attributes;

    public U4 getMagic() {
        return magic;
    }

    public void setMagic(U4 magic) {
        this.magic = magic;
    }

    public U2 getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(U2 minor_version) {
        this.minor_version = minor_version;
    }

    public U2 getMagor_version() {
        return magor_version;
    }

    public void setMagor_version(U2 magor_version) {
        this.magor_version = magor_version;
    }

    public U2 getConstant_pool_count() {
        return constant_pool_count;
    }

    public void setConstant_pool_count(U2 constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
    }

    public CpInfo[] getConstant_pool() {
        return constant_pool;
    }

    public void setConstant_pool(CpInfo[] constant_pool) {
        this.constant_pool = constant_pool;
    }

    public U2 getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(U2 access_flags) {
        this.access_flags = access_flags;
    }

    public U2 getThis_class() {
        return this_class;
    }

    public void setThis_class(U2 this_class) {
        this.this_class = this_class;
    }

    public U2 getSuper_class() {
        return super_class;
    }

    public void setSuper_class(U2 super_class) {
        this.super_class = super_class;
    }

    public U2 getInterfaces_count() {
        return interfaces_count;
    }

    public void setInterfaces_count(U2 interfaces_count) {
        this.interfaces_count = interfaces_count;
    }

    public U2[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(U2[] interfaces) {
        this.interfaces = interfaces;
    }

    public U2 getFields_count() {
        return fields_count;
    }

    public void setFields_count(U2 fields_count) {
        this.fields_count = fields_count;
    }

    public FieldInfo[] getFields() {
        return fields;
    }

    public void setFields(FieldInfo[] fields) {
        this.fields = fields;
    }

    public U2 getMethods_count() {
        return methods_count;
    }

    public void setMethods_count(U2 methods_count) {
        this.methods_count = methods_count;
    }

    public MethodInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInfo[] methods) {
        this.methods = methods;
    }

    public U2 getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(U2 attributes_count) {
        this.attributes_count = attributes_count;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
