import java.util.*;
import java.io.*;
public class Maximums {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++)
			arr[i] = nextInt();
		long[] ans = new long[n];
		long max = arr[0];
		ans[0] = max;
		for(int i = 1; i < n; i++) {
			ans[i] = max + arr[i];
			max = Math.max(max, ans[i]);
		}
		PrintWriter out = new PrintWriter(System.out);
		for(int i = 0; i < n - 1; i++)
			out.print(ans[i] + " ");
		out.println(ans[n - 1]);
		out.close();
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
