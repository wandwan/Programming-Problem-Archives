import java.util.*;
import java.io.*;
public class Check {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int m = nextInt();
		int t = nextInt();
		int[] arr = new int[n];
		Arrays.fill(arr, -1);
		ArrayList<int[]> q = new ArrayList<int[]>();
		q.sort(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0])
					return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
		for(int i = 0; i < m; i++) {
			int l = nextInt();
			int r = nextInt();
			int c = nextInt();
			if(c > 0) {
				for(int j = l; j <= r; j++)
					if(arr[j] != -1 && arr[j] != c) {
						System.out.println("NO");
						return;
					} else
						arr[j] = c;
			} else
				q.add(new int[] {l,r});
		}
		TreeSet<Integer> shift = new TreeSet<Integer>();
		ArrayList<Integer> uc = new ArrayList<Integer>();
		for(int i = n - 1; i > -1; i--) {
			if(arr[i] == -1)
				uc.add(i);
			if(arr[i] != n - 1 && arr[i] != arr[i + 1] && arr[i] != -1 && arr[i + 1] != -1)
				shift.add(i);
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
