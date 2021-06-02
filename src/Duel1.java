import java.util.*;
import java.io.*;
public class Duel1 {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("reach");
		int n = nextInt();
		int[] arr = new int[n];
		String t = next();
//		System.out.println("reach");
		for(int i = 0; i < n; i++) {
			arr[i] = t.charAt(i) == '(' ? 1:-1;
			if(i > 0) arr[i] += arr[i - 1];
		}
		if(arr[n - 1] != 0) {
			System.out.println(-1);
			return;
		}
//		System.out.println("reach");
		int ans = 0;
		int i = 0;
		while(i < n) {
			if(arr[i] < 0) {
				int j = i + 1;
				while(j < n && arr[j] < 0) {
					j++;
//					System.out.println(j);
				}
				ans += (j - i) + 1;
				i = j;
			}
//			System.out.println(i);
			i++;
		}
		System.out.println(ans);
	}
	public static String next() throws IOException {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null)
				throw new IOException();
			tokenizer = new StringTokenizer(line);
		}
		return tokenizer.nextToken();
	}

	public static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	public static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	public static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}
