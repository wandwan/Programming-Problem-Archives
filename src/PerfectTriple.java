import java.util.*;
import java.io.*;
public class PerfectTriple {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] arr = new boolean[100001];
		for(int i = 1; i < 900; i++) {
			if(arr[i]) continue;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j]) continue;
				if(arr[j ^ i]) continue;
				arr[i] = true;
				arr[j] = true;
				arr[i ^ j] = true;
				System.out.print(i + " " + j + " s");
				break;
			}
		}
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
