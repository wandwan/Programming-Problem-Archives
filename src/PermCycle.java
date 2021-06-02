import java.util.*;
import java.io.*;
public class PermCycle {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int a = nextInt();
		int b = nextInt();
		int copy = n;
		while(copy > 0) {
			if(copy % a == 0)
				break;
			copy -= b;
		}
		if(copy < 0) {
			System.out.println(-1);
			return;
		}
		int[] ans = new int[n + 1];
		for(int i = 0; i < copy / a; i++) {
			for(int j = 1; j < a; j++) {
				ans[j + i * a] = i * a + j + 1;
			}
			ans[(i + 1) * a] = i * a + 1;
		}
		for(int i = copy; i < n; i += b) {
			for(int j = 1; j < b; j++) {
				ans[j + i] = i + j + 1;
			}
			ans[(i + b)] = i + 1;
		}
		PrintWriter out = new PrintWriter(System.out);
		for(int i = 1; i < ans.length; i++)
			out.print(ans[i] + " ");
		out.println();
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
