import java.util.*;
import java.io.*;
public class Dragon {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int t = nextInt();
		PrintWriter out = new PrintWriter(System.out);
		while(t --> 0) {
			int x = nextInt();
			int n = nextInt();
			int m = nextInt();
			while(n > 0 && x / 2 + 10 < x) {
				x = x / 2 + 10;
				n--;
			}
			if(x > m * 10)
				out.println("NO");
			else out.println("YES");
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
