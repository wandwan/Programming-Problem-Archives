import java.util.*;
import java.io.*;
public class Circular {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("capasity.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		File file = new File("C:\\Users\\wanga\\Documents\\validate1.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out = new PrintWriter(new FileWriter(file));
		int t = nextInt();
		for(int l = 1; l <= t; l++) {
			int n = nextInt();
			int m = nextInt();
			int e = nextInt();
			int k = nextInt();
			long[] x = new long[n];
			long[] y = new long[n];
			long[] i = new long[e];
			long[] w = new long[e];
			fill(x, k, m);
			fill(y, k, m);
			fill(i, k, n * m + n);
			fill(w, k, 1000000000);
			long[] weight = new long[n * m + n];
			Arrays.fill(weight, 1);
			long[] ans = new long[e + 1];
			ans[0] = n - 1 + (n * (m - 1));
			TreeSet<Integer>[] min = new TreeSet[n + 1];
			for(int j = 0; j <= n; j++) min[j] = new TreeSet<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if(weight[o1] != weight[o2])
						return Long.compare(weight[o1], weight[o2]);
					return o1 - o2;
				}
			});
			for(int j = 0; j < n; j++)
				for(int z = 0; z < m; z++)
					min[j].add(j * m + z);
			for(int j = 0; j < n; j++)
				min[n].add(j + n * m);
			long[] sum = new long[n + 1];
			Arrays.fill(sum, m);
			sum[n] = n;
			for(int j = 0; j < e; j++) {
//				System.out.println(Arrays.toString(sum));
				int currIdx = (int) (i[j] / m);
				if(currIdx >= sum.length)
					currIdx = sum.length - 1;
//				System.out.println(currIdx);
				long prev = sum[currIdx] - weight[min[currIdx].last()];
				sum[currIdx] += w[j];
				sum[currIdx] -= weight[(int) i[j]];
				weight[(int) i[j]] = w[j];
				min[currIdx].remove((int) i[j]);
				min[currIdx].add((int) i[j]);
				ans[j + 1] = ans[j] + sum[currIdx] - weight[min[currIdx].last()] - prev;
			}
			long ans1 = 1;
			for(int j = 1; j < ans.length; j++) {
				ans1 *= ans[j];
				ans1 %= 1000000007;
			}
//			System.out.println(Arrays.toString(ans));
			out.println("Case #" + l + ": " + ans1);
			System.out.println("Case #" + l + ": " + ans1);
		}
	}
	public static void fill(long[] arr, int k, int m) throws IOException {
		for(int i = 0; i < k; i++)
			arr[i] = nextInt();
		long a = nextInt();
		long b = nextInt();
		long c = nextInt();
		for(int i = k; i < arr.length; i++) {
			arr[i] = ((arr[i - 2] * a) % m);
			arr[i] = ((arr[i] + arr[i - 1] * b) % m);
			arr[i] = ((arr[i] + c) % m);
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
