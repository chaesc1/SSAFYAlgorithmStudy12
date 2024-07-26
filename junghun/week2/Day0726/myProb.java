package Day0726;

public class myProb {
	class A {
		static int x = 1;
		static {
			x++;
			x += B.y;
			x++;
		}
		{
			x++;
		}
		A() {
			x++;
		}
	}
	class B {
		static int y = 1;
		static {
			y++;
			y += A.x;
			y++;
		}
		{
			y++;
		}
	}
	myProb() {
		System.out.println(A.x);
		new A();
		System.out.println(A.x);
	}
    public static void main(String[] args) {
    	new myProb();
    }
}