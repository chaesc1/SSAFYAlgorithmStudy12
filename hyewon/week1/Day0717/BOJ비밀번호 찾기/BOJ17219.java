import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String nm = bf.readLine();

		StringTokenizer st = new StringTokenizer(nm);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, String> info = new HashMap<String, String>();
		String[] a; 

		for(int i = 0; i < N; i++) {
			String text = bf.readLine();
			a = text.split(" ");
			info.put(a[0], a[1]);
		}

		for(int i = 0; i < M; i++) {
			String site = bf.readLine();
			bw.write(info.get(site));
			bw.newLine();
		}

		bw.flush();
		bw.close();
	}
}