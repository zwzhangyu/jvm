package byecode.zy.util;

import byecode.zy.type.U2;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析class文件的访问标志
 * Class文件结构中的访问标志项access_flags是用U2类型存储的，也就是2个字节。用某个bit位的值是否为1判断该类或接口的访问权限、属性。
 * 访问标志与类或接口的访问权限、属性的映射
 *
 * @author zhangyu
 */
public class ClassAccessFlagUtils {

    private static final Map<Integer, String> classAccessFlagMap = new HashMap<>();

    static {
        // 公有类型
        classAccessFlagMap.put(0x0001, "public");
        // 不允许有子类
        classAccessFlagMap.put(0x0010, "final");
        classAccessFlagMap.put(0x0020, "super");
        // 接口
        classAccessFlagMap.put(0x0200, "interface");
        // 抽象类
        classAccessFlagMap.put(0x0400, "abstract");
        // 该class非java代码编译后生成
        classAccessFlagMap.put(0x1000, "synthetic");
        // 注解类型
        classAccessFlagMap.put(0x2000, "annotation");
        // 枚举类型
        classAccessFlagMap.put(0x4000, "enum");
    }

    /**
     * 获取16进制对应的访问标志字符串表示 （仅用于类的访问标志）
     *
     * @param flag 访问标志
     */
    public static String toClassAccessFlagsString(U2 flag) {
        final int flagVlaue = flag.toInt();
        StringBuilder flagBuild = new StringBuilder();
        classAccessFlagMap.keySet().forEach(key -> {
            // 先从Class文件字节缓存中读取到access_flags的值，再将access_flags转为int类型，
            // 将转换后的值“算术与”上各个标志的值，判断结果是否等于这个标志的值
            if ((flagVlaue & key) == key) {
                flagBuild.append(classAccessFlagMap.get(key)).append(",");
            }
        });
        return flagBuild.length() > 0 && flagBuild.charAt(flagBuild.length() - 1) == ',' ?
                flagBuild.substring(0, flagBuild.length() - 1) : flagBuild.toString();
    }

}
