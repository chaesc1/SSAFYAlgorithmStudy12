import java.io.*;
import java.util.*;

// 백준 20291번 파일 정리 실버3
public class BOJ20291 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		HashMap<String, Integer> h1 = new HashMap<String, Integer>();
		
		String str;
		String extension;
		
		for(int i = 0; i < n; i++) {
			str = br.readLine();
			int index = str.indexOf(".");
			extension = str.substring(index+1, str.length());
//			System.out.println(extension);
			
			if(h1.containsKey(extension)) {
				h1.put(extension, h1.get(extension)+1);
			}else {
				h1.put(extension, 1);
			}
		}
		
		List<String> keySet = new ArrayList<>(h1.keySet());
		Collections.sort(keySet);
		
		for(String key : keySet)
			System.out.println(key+" "+h1.get(key));
		
		
		
	}
}