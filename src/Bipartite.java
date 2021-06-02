import java.util.*;


import java.io.*;
public class Bipartite {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int m = nextInt();
		ArrayList<Integer>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++)
			adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		int[] group = SCC.findBCC(adj);
		int[] min = new int[SCC.comp];
		int[] max = new int[SCC.comp];
		Arrays.fill(min, Integer.MAX_VALUE / 2);
		for(int i = 0; i < n; i++) {
			min[group[i]] = Math.min(min[group[i]], i);
			max[group[i]] = Math.max(max[group[i]], i);
		}
		TreeSet<Set> t = new TreeSet<Set>();
		for(int i = 0; i < min.length; i++)
			t.add(new Set(min[i], max[i]));
		int[] sum = new int[n];
		Set temp = t.first();
		sum[0] = (temp.r + 1);
		for(int i = 1; i < n; i++) {
			while(!t.isEmpty() && t.first().l < i) t.pollFirst();
			sum[i] = sum[i - 1];
			if(!t.isEmpty())
				sum[i] += t.first().r - i + 1;
		}
		int q = nextInt();
		while(q-- > 0) {
			int l = nextInt() - 1;
			int r = nextInt() - 1;
			int ans = sum[r];
			if(l != 0) ans -= sum[l - 1];
			System.out.println(ans);
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
class SCC {
	static Stack<Integer> s;
	static int[] disc, low, group;
	static int comp;
	static boolean[] onStack;
	static ArrayList<Integer>[] adj;
	static int t;
	public static int[] findSCC(ArrayList<Integer>[] adj) {
		s = new Stack<Integer>();
		disc = new int[adj.length];
		Arrays.fill(disc, -1);
		low = new int[adj.length];
		group = new int[adj.length];
		comp = 1;
		t = 0;
		onStack = new boolean[adj.length];
		SCC.adj = adj;
		for(int i = 0; i < adj.length; i++)
			if(disc[i] == -1)
				assign(i, -2);
		return group;
	}
	public static int[] findBCC(ArrayList<Integer>[] adj) {
		s = new Stack<Integer>();
		disc = new int[adj.length];
		Arrays.fill(disc, -1);
		low = new int[adj.length];
		group = new int[adj.length];
		comp = 0;
		onStack = new boolean[adj.length];
		SCC.adj = adj;
		for(int i = 0; i < adj.length; i++)
			if(disc[i] == -1)
				assign(i, -1);
		return group;
	}
	public static ArrayList<Integer>[] findEdgeBCC(ArrayList<Integer>[] adj) {
		findBCC(adj);
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] edge = new ArrayList[adj.length];
		for(int i = 0; i < adj.length; i++)
			for(int j = 0; j < adj[i].size(); j++)
				if(group[i] != group[adj[i].get(j)]) {
					edge[i].add(adj[i].get(j));
					edge[j].add(adj[j].get(i));
				}
		return edge;
	}
	public static void assign(int idx, int par) {
		disc[idx] = t;
		low[idx] = t;
		t++;
		s.push(idx);
		onStack[idx] = true;
		for(int i = 0; i < adj[idx].size(); i++) {
			int next = adj[idx].get(i);
			if(next == par)
				continue;
			if(disc[next] == -1) {
				if(par == -2)
					assign(next, -2);
				else
					assign(next, idx);
				low[idx] = Math.min(low[idx], low[next]);
			}
			else if(onStack[next])
				low[idx] = Math.min(low[idx], disc[next]);
		}
		if(low[idx] == disc[idx]) {
			while(true) {
				int n = s.pop();
				onStack[n] = false;
				group[n] = comp;
				if(n == idx)
					break;
			}
			comp++;
		}
	}
	public static ArrayList<Integer>[] getCondenseGraph(ArrayList<Integer>[] adj) {
		findSCC(adj);
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] cond = new ArrayList[comp];
		for(int i = 0; i < cond.length; i++)
			cond[i] = new ArrayList<Integer>();
		for(int i = 0; i < adj.length; i++)
			for(int j = 0; j < adj[i].size(); j++)
				if(group[i] != group[adj[i].get(j)])
					cond[group[i] - 1].add(group[adj[i].get(j)] - 1);
		return cond;
	}
}
class Set implements Comparable<Set>{
	int l, r;

	public Set(int l, int r) {
		super();
		this.l = l;
		this.r = r;
	}

	@Override
	public int compareTo(Set o) {
		return r - o.r;
	}
	
}