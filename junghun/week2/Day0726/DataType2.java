package Day0726;

public class DataType2 {
        public static void main(String[] args) {
            int a = 10;
            double b = 20.5;
            char c = 'A'; // 'A'의 유니코드 값은 65
            byte d = 3;

            double tempResult = a + b;
            tempResult = tempResult / d;
            int finalResult = (int) (tempResult + c);
            if(++finalResult > --tempResult || finalResult++ > tempResult--) {
                System.out.println(finalResult--);
            }
            else {
                System.out.println(tempResult--);
            }

        }
}
