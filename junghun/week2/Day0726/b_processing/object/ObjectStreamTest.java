package Day0726.b_processing.object;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) {
//        write();
        read();
    }

    private static File target = new File("c:/Temp/objPerson.dat");

    private static void write() {
        Person person = new Person("홍길동2", "pass1234", "123-456", "seoul");
        // TODO: person을 target에 저장하시오.
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(target));
            output.writeObject(person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("객체 출력 성공");
        // END
    }

    private static void read() {
        // TODO: target에서 person을 읽어서 내용을 출력하시오.
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(target))) {
            Object obj = input.readObject();
            if (obj instanceof Person person) {
                System.out.println("복원한 객체 : " + person);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // END
    }
}
