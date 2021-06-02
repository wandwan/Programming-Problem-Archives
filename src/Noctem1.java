import java.util.*;
import java.io.*;
public class Noctem1 {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("verybest.in"));
//		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int m = nextInt();
		long[] arr = new long[n];
		for(int i = 0; i < n; i++)
			arr[i] = nextLong();
		long l = 0;
		long r = 100000000000000l;
		while(l < r) {
//			System.out.println(l + " " + r);
			long mid = (l + r) / 2;
			boolean test = check(mid,m,arr);
			if(test) r = mid;
			else l = mid + 1;
		}
		PrintWriter out = new PrintWriter("verybest.out");
		out.println(l);
//		System.out.println(l);
		out.close();
	}
	public static boolean check(long k, int m, long[] arr) {
		long sum = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > k) return false;
			if(sum + arr[i] > k) {
				sum = 0;
				m--;
			}
			sum += arr[i];
		}
		if(sum > 0)
			m--;
		if(m < 0)
			return false;
		return true;
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
