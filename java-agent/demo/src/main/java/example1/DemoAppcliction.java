package example1;

import java.util.HashMap;
import java.util.Map;



public class DemoAppcliction {

    public static void main(String[] args) {
        System.out.println("main function runing...");
        Map<String, Object>  user = new UserService()
                .queryUser("wujiuye", 25);
        System.out.println(user);
    }

}