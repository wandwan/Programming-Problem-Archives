import java.util.*;
import java.io.*;
public class PoweredAddition {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int t = nextInt();
		PrintWriter out = new PrintWriter(System.out);
		while(t --> 0) {
			int n = nextInt();
			long[] arr = new long[n];
			for(int i = 0; i < n; i++) arr[i] = nextInt();
			long max = Long.MIN_VALUE;
			int maxSec = 0;
			for(int i = 0; i < n; i++) {
				max = Math.max(max, arr[i]);
				for(int j = 1; j < 10000; j++) {
					if(arr[i] < max && arr[i] + (1l << j) - 1l >= max) {
						arr[i] = max;
						maxSec = Math.max(maxSec, j);
						break;
					}
				}
			}
			out.println(maxSec);
		}
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
