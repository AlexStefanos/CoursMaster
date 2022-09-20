import java.io.File;
import fr.ubs.io.*;
import java.io.IOException;

public class Prep{
    public static void main(String[] args) {
        File file = new File("/home/alexandre/Cours/Master/M1/S1/SystemesDistribues/TP/TP01/mail.txt");
        try {
            MailFile mailF = new MailFile(file);

            System.out.println(mailF.getFrom());
            System.out.println(mailF.getMessageId());
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}