import java.util.*;
import java.io.*;
public class AdjacentDifference {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int t = nextInt();
		PrintWriter out = new PrintWriter(System.out);
		while(t --> 0) {
			int n = nextInt();
			Integer[] arr = new Integer[n];
			for(int i = 0; i < n; i++) arr[i] = nextInt();
			Arrays.sort(arr, null);
			int mid = (n - 1) / 2;
			int[] ans = new int[n];
			int l = mid; int r = mid + 1;
			int curr = 0;
			while(l >= 0 || r < n) {
				if(l >= 0) ans[curr++] = arr[l--];
				if(r < n) ans[curr++] = arr[r++];
			}
			for(int e: ans)
				out.print(e + " ");
			out.println();
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
