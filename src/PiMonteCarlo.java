import java.util.*;
import java.io.*;
public class PiMonteCarlo {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		Random r = new Random(12431423);
		int count = 0;
		for(int i = 0; i < n; i++) {
			double x = r.nextDouble();
			double y = r.nextDouble();
			if(x * x + y * y <= 1) {
				count++;
			}
		}
		double pi = 4.0 * count / n;
		System.out.println(pi);
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
