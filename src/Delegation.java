import java.util.*;

import java.io.*;
public class Delegation {

	static BufferedReader br;
	static StringTokenizer tokenizer;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("deleg.in"));
//		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		ArrayList<Integer>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++)
			adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
//		Tree t = Tree.getTestTree(30, 0);
//		System.out.println(t);
//		int n = 30;
//		ArrayList<Integer>[] adj = t.adj;
//		for(int i = 0; i < n; i++) System.out.println(i + " " + adj[i]);
		int l = 0;
		int r = n - 1;
		int m;
		while(l < r) {
			m = (l + r) / 2 + ((r - l) & 1);
			int ans = solve(0, adj, new boolean[n], m);
			if(ans < m && ans != 0) r = m - 1;
			else l = m;
		}
		PrintWriter out = new PrintWriter("deleg.out");
//		System.out.println(l);
		out.println(l);
		out.close();
	}
	public static int solve(int curr, ArrayList<Integer>[] adj, boolean[] visited, int k) {
		visited[curr] = true;
		TreeSet<Node> children = new TreeSet<Node>();
		for(int e: adj[curr])
			if(!visited[e])
				children.add(new Node(solve(e,adj,visited, k)));
		if(children.size() > 0 && children.first().val == -1)
			return -1;
		int haveToTake = -1;
		while(children.size() > 0) {
			int t = children.pollFirst().val;
			if(t >= k - 1)
				return k;
			Node t1 = children.higher(new Node(0, k - 2 - t));
			if(t1 != null)
				children.remove(t1);
			else if(haveToTake == -1)
				haveToTake = t + 1;
			else
				return -1;
		}
		if(haveToTake == -1)
			return 0;
		return haveToTake;
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
class Node implements Comparable<Node> {
	int ID, val;
	static int assigner = 1;
	public Node(int val) {
		this(assigner++, val);
	}

	public Node(int iD, int val) {
		super();
		ID = iD;
		this.val = val;
	}

	@Override
	public int compareTo(Node o) {
		if(val != o.val)
			return val - o.val;
		return ID - o.ID;
	}
	public String toString() {
		return "[" + ID + ", " + val + "]";
	}
}
//class Tree {
//	int root;
//	ArrayList<Integer>[] adj;
//	int[] par;
//	public Tree(ArrayList<Integer>[] adj) {
//		this.adj = adj;
//	}
//	public Tree(ArrayList<Integer>[] adj, int root) {
//		this(adj);
//		this.root = root;
//	}
//	public Tree(int[] par) {
//		this.par = par;
//	}
//	// get a random tree of with size number of nodes
//	// root can be specified or -1 if random root is needed
//	public static Tree getTestTree(int size, int root) {
//		Random r = new Random(System.currentTimeMillis());
//		ArrayList<Integer>[] adj = new ArrayList[size];
//		int[] par = new int[size];
//		for(int i = 0; i < size; i++)
//			adj[i] = new ArrayList<Integer>();
//		if(root == -1)
//			root = r.nextInt(size);
//		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
//		ArrayList<Integer> remain = new ArrayList<Integer>();
//		for(int i = 0; i < size; i++)
//			if(i != root)
//				remain.add(i);
//		q.add(root);
//		while(!q.isEmpty()) {
//			int curr = q.remove();
//			if(remain.size() == 0)
//				break;
//			// max amount of children you can have
//			int children = Math.max(1, r.nextInt(Math.min(remain.size(), 3)));
//			for(int i = 0; i < children; i++) {
//				adj[curr].add(remain.remove(r.nextInt(remain.size())));
//				q.add(adj[curr].get(adj[curr].size() - 1));
//				par[adj[curr].get(adj[curr].size() - 1)] = curr;
//			}
//		}
//		Tree t = new Tree(adj, root);
//		t.par = par;
//		return t;
//	}
//	//converts undirected adj tree to one directed downwards from root
//	public static ArrayList<Integer>[] convertToDirected(ArrayList<Integer>[] adj, int root) {
//		ArrayList<Integer>[] adj1 = new ArrayList[adj.length];
//		for(int i = 0; i < adj.length; i++)
//			adj1[i] = new ArrayList<Integer>();
//		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
//		q.add(root);
//		boolean[] visited = new boolean[adj.length];
//		while(!q.isEmpty()) {
//			int curr = q.remove();
//			visited[curr] = true;
//			for(int i = 0; i < adj[curr].size(); i++) {
//				if(!visited[adj[curr].get(i)]) {
//					adj1[curr].add(adj[curr].get(i));
//					q.add(adj[curr].get(i));
//				}
//			}
//		}
//		return adj1;
//	}
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
//		q.add(root);
//		int num = 1;
//		while(!q.isEmpty()) {
//			int curr = q.remove();
//			sb.append(curr + " ");
//			for(int i = 0; i < adj[curr].size(); i++)
//				q.add(adj[curr].get(i));
//			num--;
//			if(num == 0) {
//				num = q.size();
//				sb.append("\n");
//			}
//		}
//		return sb.toString();
//	}
//}
