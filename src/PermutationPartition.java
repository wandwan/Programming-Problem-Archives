import java.util.*;
import java.io.*;
public class PermutationPartition {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int k = nextInt();
		int[] arr = new int[n];
		ArrayList<Integer> pos = new ArrayList<Integer>();
		long ans = (n + n - k + 1) * (long) k / 2;
		long ans1 = 1;
		for(int i = 0; i < n; i++) {
			arr[i] = nextInt();
			if(arr[i] > n - k) pos.add(i);
		}
		pos.sort(null);
		for(int i = 0; i < pos.size() - 1; i++) {
			ans1 *= pos.get(i + 1) - pos.get(i);
			ans1 %= 998244353;
		}
		System.out.println(ans + " " + ans1);
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
