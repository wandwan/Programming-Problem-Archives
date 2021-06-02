import java.util.*;
import java.io.*;
public class PattieCakes {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = nextInt();
		while(t --> 0) {
		int n = nextInt();
			int[] temp = new int[n + 1];
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			int t1 = nextInt();
			if(temp[t1] == 0)
				arr.add(t1);
			temp[t1]++;
		}
		arr.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return temp[o2] - temp[o1];
			}
		});
		int max = temp[arr.get(0)] - 1;
		int curr = 0;
		int ans = 0;
		System.out.println(arr);
		for(int i = 1; i < arr.size(); i++) {
			if(curr >= max) {
				curr = 0;
				ans++;
			}
			curr += temp[arr.get(i)];
		}
		if(curr >= max) ans++;
		out.println(ans);
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
