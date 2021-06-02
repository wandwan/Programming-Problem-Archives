import java.util.*;
import java.io.*;
public class RecursiveQueriees {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[1000001];
		int[][] pre = new int[10][1000001];
		for(int i = 1; i < 10; i++)
			dp[i] = i;
		for(int i = 10; i < 1000001; i++) {
			int copy = i;
			int mult = 1;
			while(copy != 0) {
				if(copy % 10 != 0) mult *= copy % 10;
				copy /= 10;
			}
			dp[i] = dp[mult];
		}
		for(int i = 1; i < 1000001; i++) {
			pre[dp[i]][i]++;
			for(int j = 0; j < 10; j++)
				pre[j][i] += pre[j][i - 1];
		}
		int n = nextInt();
		while(n-- > 0) {
			int l = nextInt();
			int r = nextInt();
			int k = nextInt();
			System.out.println(pre[k][r] - pre[k][l - 1]);
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
