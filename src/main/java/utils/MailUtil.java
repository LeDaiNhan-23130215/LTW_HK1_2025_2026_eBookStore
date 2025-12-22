package utils;

public class MailUtil {
    public static void send(String to, String subject, String content) {
        System.out.println("Send mail to: " + to);
        System.out.println("Content: " + content);
    }
}
