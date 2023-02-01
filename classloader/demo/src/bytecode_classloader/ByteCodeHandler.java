package bytecode_classloader;

/**
 * 字节码处理器
 *
 */
public interface ByteCodeHandler {

    /**
     * 获取类名
     *
     * @return
     */
    String getClassName();

    /**
     * 获取类的字节码
     *
     * @return
     */
    byte[] getByteCode();

}