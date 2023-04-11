package example1;

import java.lang.instrument.Instrumentation;

public class MyJavaAgent {
    public static void premain(String agentOps, Instrumentation instrumentation) {
        System.out.println("premain function run...");
    }
}