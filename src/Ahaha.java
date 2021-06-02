import java.util.*;
import java.io.*;
public class Ahaha {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = nextInt();
		outer:
		for(int x = 0; x < t; x++) {
			int n = nextInt();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int i = 0; i < n; i++)
				arr.add(i + 1);
			Collections.shuffle(arr);
			System.out.println(arr);
			out.println();
		}
		out.close();
	}
	public static int gcd(int a, int b) {
		if(a == 0)
			return b;
		return gcd(b % a, a);
	}
	public static boolean getSum(int[] arr, int x) {
		int sum = 0;
		for(int i = 0; i < x; i++)
				sum += arr[i] * (i % 2 == 0 ? 1 : -1);
		for(int i = x + 1; i < arr.length; i++)
			sum += arr[i] * (i % 2 == 0 ? -1: 1);
		return sum == 0;
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
