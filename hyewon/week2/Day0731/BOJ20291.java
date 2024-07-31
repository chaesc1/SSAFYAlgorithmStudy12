import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		//https://velog.io/@cchoijjinyoung/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-5-HashMap%ED%95%B4%EC%8B%9C%EB%A7%B5%EC%9D%84-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90
		Map<String, Integer> map = new HashMap<>(); // hashmap : 키(Key)와 값(value)쌍을 저장하는 자료 구조
		List<String> typeList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();
			String ex = st.nextToken(); // type
			
			if(!map.containsKey(ex)) typeList.add(ex);
			map.put(ex, map.getOrDefault(ex, 0) + 1); // map의 키 값이 ex면 기존 값에 + 1, 아니면 0으로 저장 
		}
		
		Collections.sort(typeList);
		
		for(String t : typeList) {
			System.out.println(t + " " + map.get(t)); // map.get()은 key값을 찾아 value값 반환하는 것
		}
	}
}