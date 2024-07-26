package Day0726;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BookSave {
    public static void main(String[] args) {
        Book book = new Book("21424", "Java Pro", "김하나", "jaen.kr", 15_000, "JAVA 기본 문법");
        try (FileOutputStream fos = new FileOutputStream("./book.ser")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(book);
            System.out.println("Book saved to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
