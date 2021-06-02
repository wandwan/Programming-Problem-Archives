import java.util.*;
import java.io.*;
public class BitstringService {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("BitString.txt"));
		int t = nextInt();
		int l = 0;
		File file = new File("C:\\Users\\wanga\\Documents\\validate1.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out1 = new PrintWriter(new FileWriter(file));
		while(t --> 0) {
			int n = nextInt();
			int m = nextInt();
			DSU ds = new DSU(n);
			for(int i = 0; i < m; i++) {
				int left = nextInt() - 1;
				int right = nextInt() - 1;
				for(int j = 0;  2 * j < right - left; j++)
					ds.merge(left + j, right - j);
			}
			HashSet<Integer> set = new HashSet<Integer>();
			for(int i = 0; i < n; i++)
				set.add(ds.find(i));
			ArrayList<Integer> size = new ArrayList<Integer>();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int a = 0;
			for(int i: set) {
				size.add(i);
				map.put(i, a++);
			}
			boolean[][] dp = new boolean[size.size()][n + 1];
			int sum = 0;
			dp[0][0] = true;
			for(int i = 0; i < size.size() - 1; i++) {
				for(int j = 0; j <= sum; j++) {
					if(dp[i][j]) {
						dp[i + 1][j + ds.setSize[size.get(i)]] = true;
						dp[i + 1][j] = true;
					}
				}
				sum += ds.setSize[size.get(i)];
			}
			double min = n;
			double mid = n / 2.0;
			int idx = 0;
			for(int i = 0; i < dp[0].length; i++)
				if(dp[dp.length - 1][i] && Math.abs(mid - i) < min) {
					min = Math.abs(mid - i);
					idx = i;
				}
			boolean[] zeroes = new boolean[ds.sets];
			int curr = size.size() - 1;
			while(idx != 0) {
				if(idx - ds.setSize[size.get(curr)] >= 0 && dp[curr][idx - ds.setSize[size.get(curr)]]) {
					zeroes[curr] = true;
					idx -= ds.setSize[size.get(curr--)];
				} else curr--;
			}
			StringBuilder ans = new StringBuilder();
			for(int i = 0; i < n; i++)
				if(zeroes[map.get(ds.find(i))]) ans.append(0);
				else ans.append(1);
			out1.println("Case #" + ++l + ": " + ans);
		}
		out1.close();
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
class DSU {
	int[] par;
	int[] depth;
	int[] setSize;
	int sets;
	public DSU(int n) {
		this(fix(new int[n]));
	}
	private static int[] fix(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			arr[i] = i;
		return arr;
	}
	public DSU(int[] par) {
		this.par = par;
		depth = new int[par.length];
		setSize = new int[par.length];
		sets = par.length;
		for(int i = 0; i < par.length; i++)
			setSize[find(i)]++;
	}
	public void merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b)
			return;
		if(depth[b] > depth[a]) {
			merge(b,a);
			return;
		}
		par[b] = a;
		if(depth[b] == depth[a])
			depth[a]++;
		setSize[a] += setSize[b];
		sets--;
	}
	public int find(int n) {
		if(par[n] != n)
			par[n] = find(par[n]);
		return par[n];
	}
}
