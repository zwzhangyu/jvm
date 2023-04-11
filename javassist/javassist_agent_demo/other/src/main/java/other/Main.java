package other;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Stuff stuff = new Stuff();
        stuff.run();
        Thread.sleep(100000 * 1000);
        System.out.println("end");
    }
}
