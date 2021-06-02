import java.util.*;
import java.io.*;
public class Capasity {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("capasity.txt"));
		File file = new File("C:\\Users\\wanga\\Documents\\validate1.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out = new PrintWriter(new FileWriter(file));
		int t = nextInt();
		for(int l = 1; l <= t; l++) {
			int n = nextInt();
			int k = nextInt();
			long[] arr = new long[n];
			long[] x = new long[n];
			long[] y = new long[n];
			fill(arr, k);
			fill(x, k);
			fill(y, k);
			long sum = 0;
			long min = 0;
			long max = 0;
			for(int i = 0; i < n; i++) {
				sum += arr[i];
				min += x[i];
				max += y[i];
			}
			max += min;

//			System.out.println(Arrays.toString(arr));
//			System.out.println(Arrays.toString(x));
//			System.out.println(Arrays.toString(y));
			if(sum < min || sum > max) {
				out.println("Case #" + l + ": -1");
				continue;
			}
			long need = 0;
			long exc = 0;
			for(int i = 0; i < n; i++) {
				if(arr[i] < x[i])
					need += x[i] - arr[i];
				else if(arr[i] > x[i] + y[i])
					exc += arr[i] - x[i] - y[i];
			}
//			System.out.println(exc + " " + need);
			out.println("Case #" + l + ": " + (Math.max(exc, need)));
		}
		out.close();
	}
	public static void fill(long[] arr, int k) throws IOException {
		for(int i = 0; i < k; i++)
			arr[i] = nextInt();
		long a = nextInt();
		long b = nextInt();
		long c = nextInt();
		long d = nextInt();
		for(int i = k; i < arr.length; i++) {
			arr[i] = arr[i - 2] * a;
			arr[i] %= d;
			arr[i] += arr[i - 1] * b;
			arr[i] %= d;
			arr[i] += c;
			arr[i] %= d;
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
