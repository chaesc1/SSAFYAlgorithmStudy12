package Day0726;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BookLoad {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("./book.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Book book = (Book) ois.readObject();
            System.out.println("읽어 들인 Book 객체 : " + book);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
