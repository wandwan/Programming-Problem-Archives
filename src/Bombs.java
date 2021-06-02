import java.util.*;

import java.io.*;
public class Bombs {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int[] arr = new int[n];
		int[] q = new int[n];
		int[] idx = new int[n];
		for(int i = 0; i < n; i++)
			arr[i] = nextInt();
		for(int i = 0; i < n; i++)
			q[i] = nextInt();
		RecursiveSegmentTree st = new RecursiveSegmentTree(arr);
		Arrays.fill(idx, Integer.MAX_VALUE / 2);
		for(int i = n - 1; i > -1; i--) {
			int first = q[i];
			int min = st.getMin(first, n - 1);
			if(st.arr[min] > Integer.MAX_VALUE / 2) {
				
			}
			st.inc(min, Integer.MAX_VALUE / 2);
			idx[min] = first;
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
class RecursiveSegmentTree {
	Node root;
	int n;
	int[] arr;
	class Node {
		int min;
		int l, r;
		Node lc, rc, par;
		public Node(int l, int r) {
			this.l = l;
			this.r = r;
		}
		Node getNode(Node curr) {
			if(curr.l == curr.r) {
				curr.min = curr.l;
				return curr;
			}
			Node lc = getNode(new Node(curr.l, (curr.l + curr.r) / 2));
			Node rc = getNode(new Node((curr.l + curr.r) / 2 + 1, curr.r));
			curr.min = getMin(lc,rc);
			curr.lc = lc;
			curr.rc = rc;
			lc.par = curr;
			rc.par = curr;
			return curr;
		}
		public String toString() {
			return l + " " + r + ", ";
		}
	}
	int getMin(Node a, Node b) {
		if(arr[a.min] > arr[b.min])
			return b.min;
		return a.min;
	}
	public RecursiveSegmentTree(int n) {
		this(new int[n]);
	}
	public RecursiveSegmentTree(int[] arr) {
		this.n = arr.length;
		this.arr = arr;
		root = new Node(0, n - 1);
		root.getNode(root);
	}
	public void inc(int idx, int val) {
		arr[idx] += val;
		Node curr = root;
		while(curr.l != curr.r) {
			if(curr.rc.l <= idx && curr.rc.r >= idx)
				curr = curr.rc;
			else curr = curr.lc;
		}
		while(curr != root) {
			curr = curr.par;
			curr.min = getMin(curr.lc, curr.rc);
		}
	}
	public int getMin(int l, int r) {
		return getMin(l,r,root);
	}
	private int getMin(int l, int r, Node curr) {
		if(curr.l > r || curr.r < l)
			return -1;
		if(curr.l >= l && curr.r <= r) return curr.min;
		int a = getMin(l,r,curr.lc);
		int b = getMin(l,r,curr.rc);
		if(b == -1 || (a != -1 && arr[a] < arr[b])) return a;
		return b;
	}
}