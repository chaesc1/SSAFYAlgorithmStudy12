package Day0726.a_node;

import java.io.File;
import java.io.IOException;

public class UseFileTest {

    public static void main(String[] args) {
        try {
            // 절대 경로
            File temp = new File("c:\\Temp");
            System.out.printf("존재? %b, 디렉토리? %b%n", temp.exists(), temp.isDirectory());

            // 상대경로
            File current = new File(".");
            System.out.printf("여기는 어디? %s%n", current.getCanonicalPath());

            // 상대경로의 기준은 java program을 실행하는 위치(소스와 무관)
            // d:\>java -cp .;[project_root]\bin [package].UseFileStream

            // TODO: readme.txt에 해당하는 File 객체를 만들어 보자.
            //  프로젝트와 무관하게 파일을 찾을 때(절대 경로)
            //  프로젝트 내부에서 파일을 찾을 때(상대 경로)
            //  특정 클래스 기준으로 파일을 찾을 때
            // 1. Finding file with absolute path
            File absoluteFilePath = new File("c:\\path\\to\\your\\project\\readme.txt");
            System.out.println(absoluteFilePath.exists() ? "File found with absolute path" : "File not found");

            // 2. Finding file with relative path (relative to the project root directory)
            File relativeFilePath = new File("./readme.txt");
            System.out.println(relativeFilePath.exists() ? "File found with relative path" : "File not found");

            // 3. Finding file relative to a class (This needs the readme.txt file to be in the same package as your class)
            String currentDir = System.getProperty("user.dir");
            File relativeToClass = new File(currentDir + "\\src\\" + UseFileTest.class.getPackage().getName().replace(".", "\\") + "\\readme.txt");
            System.out.println(relativeToClass.exists() ? "File found relative to class" : "File not found");
            // END
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
