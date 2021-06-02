import java.util.*;
import java.io.*;
public class Elimination {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("capasity.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		File file = new File("C:\\Users\\wanga\\Documents\\validate1.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out = new PrintWriter(new FileWriter(file));
		int t = nextInt();
		for(int l = 1; l <= t; l++) {
			int n = nextInt();
			double p = nextDouble();
//			System.out.println(p);
			double[] ans = new double[n];
			double[] survive = new double[n];
			Arrays.fill(survive, 1);
			System.out.println(0 +": " + Arrays.toString(survive));
//			Arrays.fill(ans, 1);
			for(int i = 1; i < n; i++) {
				for(int j = 0; j < n; j++) {
					double ph = (double) (n - j - 1) / (n - 1);
					double pl = (double) j / (n - 1);
					ans[j] += (survive[j]) * i * (ph * p + pl * (1 - p));
					survive[j] *= ((2.0 / (n - i + 1)) * (ph * (1.0 - p) + pl * p)) + ((double) (n - i - 1) / (n - i + 1));
				}
				System.out.println(i +": " + Arrays.toString(survive));
			}
			for(int i = 0; i < n; i++)
				ans[i] += survive[i] * (n - 1);
			System.out.println(Arrays.toString(ans));
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
