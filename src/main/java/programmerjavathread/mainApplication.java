package programmerjavathread;

public class mainApplication {
    public static void main(String[] args) {
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
