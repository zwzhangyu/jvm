package agent;


import com.sun.tools.attach.VirtualMachine;

public class AttachTest {
    public static void main(String[] args) {
        try {
            VirtualMachine  vm= VirtualMachine.attach("6046");
            vm.loadAgent("/Users/zhangyu/code/zy/github/jvm/javassist/javassist_agent_demo/agent-attach/target/agent-attach-0.1-SNAPSHOT.jar","param");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}