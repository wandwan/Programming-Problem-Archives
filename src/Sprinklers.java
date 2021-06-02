import java.util.*;
import java.io.*;
public class Sprinklers {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("sprinklers2.in"));
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int[][] arr = new int[n][n + 1];
		boolean[][] arr1 = new boolean[n][n + 1];
		long[][] dp = new long[n + 1][n + 1];
		for(int i = 0; i < n; i++) {
			char[] t = next().toCharArray();
			for(int j = n - 1; j >= 0; j--) {
				arr1[i][j] = t[j] == '.';
				arr[i][j] = arr[i][j + 1] + (t[j] == '.' ? 1 : 0);
			}arr1[i][n] = true;
		}
		long[] pre = new long[n + 1];
		long[] curr = new long[n + 1];
		for(int j = 0; j <= n; j++) {
			long x = 1;
			if(j < n) x = pow(2,arr[0][j + 1], 1000000007);
			long y = 1;
			if(j > 0) y = pow(2,arr[0][0] - arr[0][j - 1], 1000000007);
			if(j > 0 && !arr1[0][j - 1]) y = 0;
			dp[0][j] = (x * y) % 1000000007;
			if(arr1[0][j] && (j == 0 || arr1[0][j - 1])) pre[j] = dp[0][j];
			if(j != 0) pre[j] += pre[j - 1];
			pre[j] %= 1000000007;
		}
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= n; j++) {
				long x = 1;
				if(j < n) x = pow(2,arr[i][j + 1], 1000000007);
				long y = 1;
				if(j > 0) y = pow(2,arr[i][0] - arr[i][j - 1], 1000000007);
				long z = 0;
				if(j > 0 && arr1[i][j - 1]) z = pre[j - 1];
				long y1 = pow(2,arr[i][0] - arr[i][j], 1000000007);
				dp[i][j] = ((((long) x * y) % 1000000007) * z) % 1000000007 + (((y1 * x) % 1000000007) * dp[i - 1][j] * (j == n || !arr1[i - 1][j] ? 1 : 2)) % 1000000007;
				if(arr1[i][j] && (j == 0 || arr1[i][j - 1])) curr[j] = dp[i][j];
				if(j != 0) curr[j] += curr[j - 1];
				curr[j] %= 1000000007;
			}
			pre = curr;
			curr = new long[n + 1];
		}
		for(int i = 0; i < n; i++)
			System.out.println(Arrays.toString(dp[i]));
		long ans = 0;
		for(int j = 0; j < n; j++)
			if(arr1[n - 1][j])
				ans = (ans + dp[n - 1][j]) % 1000000007;
		ans += dp[n - 1][n];
		ans %= 1000000007;
		System.out.println(ans);
		System.out.println(generate(5));
//		PrintWriter out = new PrintWriter("sprinklers2.out");
//		out.println(ans);
//		out.close();
	}
	public static String generate(int n) {
		String t = "";
		Random r = new Random(System.currentTimeMillis());
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				t += (r.nextInt(10) > 6) ? ".": "W";
			}
			t += "\n";
		}
		return t;
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
	public static long pow(long base, long exp, int mod) {
		long result = 1;
		while(true) {
			if((exp & 1) == 1)
				result = (result * base) % mod;
			exp >>= 1;
			if(exp == 0)
				break;
			base = (base * base) % mod;
		}
		return result;
	}
}
