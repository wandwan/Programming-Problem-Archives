import java.util.*;
import java.io.*;
public class Kingdom {

	static BufferedReader br;
	static StringTokenizer tokenizer;
	static int[] par;
	static int[] size;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int k = nextInt();
		ArrayList<Integer>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		par = new int[n];
		size = new int[n];
		int[] off = new int[n];
		int[] depths = new int[n];
		DFS(0, depths, adj);
		long ans = 0;
		TreeSet<Town> arr1 = new TreeSet<Town>();
		Town[] arr2 = new Town[n];
		for(int i = 0; i < n; i++) {
			arr2[i] = new Town(depths[i], i);
			if(adj[i].size() == 1)
				arr1.add(arr2[i]);
		}
//		System.out.println(Arrays.toString(par));
//		for(ArrayList<Integer> e: adj)
//			System.out.println(e);
		for(int i = 0; i < k; i++) {
//			System.out.println(arr1);
			Town top = arr1.pollFirst();
			ans += top.val;
//			System.out.println(arr1);
			off[par[top.ID]]++;
//			System.out.println(off[par[top.ID]] + " " + par[top.ID]);
			arr2[par[top.ID]].val = depths[par[top.ID]] - off[par[top.ID]];
			if(size[par[top.ID]] == off[par[top.ID]])
				arr1.add(arr2[par[top.ID]]);
		}
		System.out.println(ans);
	}
	public static void DFS(int curr, int[] depth, ArrayList<Integer>[] adj) {
		for(int e: adj[curr]) {
			if(e != 0 && depth[e] == 0) {
				par[e] = curr;
				depth[e] = depth[curr] + 1;
				DFS(e, depth, adj);
				size[curr] += size[e] + 1; 
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
class Town implements Comparable<Town>{
	int val, ID;

	public Town(int val, int iD) {
		super();
		this.val = val;
		ID = iD;
	}

	@Override
	public int compareTo(Town o) {
		if(val != o.val)
			return o.val - val;
		return o.ID - ID;
	}

	@Override
	public String toString() {
		return "Town [val=" + val + ", ID=" + ID + "]";
	}
	
}