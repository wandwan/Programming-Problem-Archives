import java.util.*;
import java.io.*;
public class EdgeWeight {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		ArrayList<Integer>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
		if(n == 2) {
			System.out.println(1 + " " + 1);
			return;
		}
		for(int i = 0; i < n - 1; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		int root = 0;
		while(adj[root].size() == 1)
			root++;
		int[] depths = new int[n];
		Arrays.fill(depths, -1);
		depths[root] = 0;
		getDepths(depths, root, adj);
		boolean odd = false;
		boolean even = false;
		for(int i = 0; i < n; i++) {
			if(adj[i].size() == 1) {
				if(depths[i] % 2 == 1)
					odd = true;
				else even = true;
			}
		}
		int[] children = new int[n];
		for(int i = 0; i < n; i++)
			if(adj[i].size() == 1)
				children[adj[i].get(0)]++;
		int tot = 0;
		for(int i = 0; i < children.length; i++)
			if(children[i] > 1) tot += children[i] - 1;
		if(odd && even) System.out.println(3 + " " + (n - tot - 1));
		else System.out.println(1 + " " + (n - tot - 1));
	}
	public static void getDepths(int[] depths, int curr, ArrayList<Integer>[] adj) {
		for(int i = 0; i < adj[curr].size(); i++) {
			if(depths[adj[curr].get(i)] == -1) {
				depths[adj[curr].get(i)] = depths[curr] + 1;
				getDepths(depths, adj[curr].get(i), adj);
			}
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
