package Day0726.a_node;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class SimpleInputTest {

    private String data1 = "hi java world";
    private String data2 = "자바는 객체지향 언어입니다.";

    private void read1() {
        try (InputStream input = new ByteArrayInputStream(data1.getBytes())) {
            int read = -1;
            while ((read = input.read()) != -1) {
                System.out.printf("읽은 값: %d, 문자로: %c%n", read, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read2() {
        byte[] buffer = new byte[10];
        try (InputStream input = new ByteArrayInputStream(data1.getBytes())) {
            int read = -1;
            while ((read = input.read(buffer)) > 0) {
                
                System.out.printf("읽은 개수: %d, 문자열: %s%n", read, new String(buffer, 0, read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read3() {
        char[] buffer = new char[10];
        // TODO: CharArrayReader를 이용해 data2를 읽고 출력하시오.
        try(Reader reader = new CharArrayReader(data2.toCharArray())) {
            int read = -1; // 더이상 읽을 것이 없다.
            while ((read = reader.read(buffer)) != -1) {
                System.out.println("읽은 개수: " + String.valueOf(buffer, 0, read));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // END
    }

    public static void main(String[] args) {
        SimpleInputTest ns = new SimpleInputTest();
//        ns.read1();
        // ns.read2();
         ns.read3();
    }
}
