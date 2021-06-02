import java.util.*;
import java.io.*;
public class Charging {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		long[] arr = new long[n];
		long[] pre = new long[n];
		long[] suf = new long[n];
		for(int i = 0; i < n; i++)
			arr[i] = nextInt();
		pre[0] = arr[0];
		for(int i = 1; i < n; i++)
			pre[i] += arr[i] + pre[i - 1];
		suf[n - 1] = arr[n - 1];
		for(int i = n - 2; i >= 0; i--)
			suf[i] += arr[i] + suf[i + 1];
		long min = pre[n - 1];
		for(int i = 0; i < n; i++) {
			long suff = (i == n - 1 ? 0: suf[i + 1]);
			min = Math.min(min, pre[i] + suff - Math.min(pre[i] / 10, suff));
		}
		System.out.println(min);
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
