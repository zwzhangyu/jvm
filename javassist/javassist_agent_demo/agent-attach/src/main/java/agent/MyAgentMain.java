package agent;

import java.lang.instrument.Instrumentation;

public class MyAgentMain {
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agent main start");
        System.out.println("args:"+agentArgs);
    }
}