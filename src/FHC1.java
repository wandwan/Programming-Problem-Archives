import java.util.*;
import java.io.*;
public class FHC1 {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("fumes.txt"));
		int t = nextInt();
		int l = 0;
		File file = new File("C:\\Users\\wanga\\Documents\\validate1.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out1 = new PrintWriter(new FileWriter(file));
		outer:
		while(t --> 0) {
			//sol sketch, dp[node] = least cost path that takes node and returns to our path from a to b
			//takes shortest route to return to our a to b path (converting this problem to d1)
			//shortest route is going up the subtree until you find a parent
			//we can query all prev dp[nodes] on a minimum segment tree
			//O(nlogn)
			int n = nextInt();
			int m = nextInt();
			int a = 0;
			int b = n - 1;
			int[] par = new int[n];
			long[] cost = new long[n];
			nextLong();
			for(int i = 1; i < n; i++) {
				par[i] = i - 1;
				cost[i] = nextLong();
			}
			
			//generate adjacency list for tree
			ArrayList<Integer>[] adj = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adj[i] = new ArrayList<Integer>();
			for(int i = 1; i < n; i++)
				adj[par[i]].add(i);
			
			//generate depths for all nodes
			int[] depth = new int[n];
			DFS(0,depth,adj);
//			System.out.println(Arrays.toString(par));
			//generate all nodes on path from a to b
			ArrayList<Integer> path = new ArrayList<Integer>();
			getPath(a,b,path,depth,par);
			//get stations
			ArrayList<Station> stations = new ArrayList<Station>();
			ArrayList<Station> order = new ArrayList<Station>();
			boolean[] visited = new boolean[n];
			{
				ArrayDeque<Integer> q = new ArrayDeque<Integer>();
				for(int i = 1; i < path.size() - 1; i++) {
					int curr = path.get(i);
					q.add(curr);
					int dist = 0;
					int num = 1;
					int next = 0;
					while(!q.isEmpty()) {
						if(num == 0) {
							num = next;
							next = 0;
							dist++;
						}
						curr = q.poll();
						num--;
						if(curr == -1|| visited[curr] || curr == path.get	(i - 1) || curr == path.get(i + 1)|| i + depth[curr] - depth[path.get(i)] >= path.size() - 1)
							continue;
						visited[curr] = true;
						next += adj[curr].size() + 1;
//						System.out.println(adj[curr]);
//						System.out.println(next);
						for(int e: adj[curr])
							q.add(e);
						q.add(par[curr]);
						if(cost[curr] == 0) continue;
						if(i + m - dist <= m) continue;
						stations.add(new Station(curr, i + m - dist, i + dist));
					}
				}
				stations.add(new Station(a,m,0));
				stations.add(new Station(b,Integer.MAX_VALUE / 2,path.size() - 1));
				stations.sort(null);
			}
//			System.out.println(stations);
			order.addAll(stations);
			order.sort(new Comparator<Station>() {
				@Override
				public int compare(Station o1, Station o2) {
					if(o1.dist != o2.dist)
						return o1.dist - o2.dist;
					return o1.reach - o2.reach;
				}
			});
			cost[a] = 0;
			cost[b] = 0;
//			System.out.println("a " + a);
//			System.out.println(Arrays.toString(cost));
//			System.out.println(stations);
//			System.out.println(slowSolve(stations, 0, new long[stations.size()], cost));
			long[] st1 = new long[stations.size()];
			Arrays.fill(st1, Long.MAX_VALUE);
			st1[0] = 0;
			MinSegmentTree st = new MinSegmentTree(st1);
			for(int i = 0; i < order.size(); i++) {
				Station t1 = order.get(i);
				int l1 = 0, r = stations.size() - 1;
				while(l1 < r) {
					int mid = (l1 + r) / 2;
					if(stations.get(mid).reach < t1.dist)
						l1 = mid + 1;
					else r = mid;
				}
//				System.out.println(t1);
				int idx = Collections.binarySearch(stations, t1);
//				System.out.println(l1 + " " + idx);
				long min = st.query(l1, stations.size());
				if(min == Long.MAX_VALUE) {out1.println("Case #" + (++l) + ": -1"); continue outer;}
//				System.out.println(stations.get(idx) + " " + cost[t1.id] +" " + min);
//				System.out.println();
				st.modify(idx, cost[t1.id] + min);
//				if(cost[t1.id] + min == 0) System.out.println("s " + t1);
			}
//			for(int i = st.n; i < 2 * st.n; i++)
//				System.out.print(i - st.n + " " + st.segTree[i] + ", ");
//			System.out.println();
			out1.println("Case #" + (++l) + ": " + st.query(stations.size() - 1, stations.size()));
		}
		out1.close();
	}
	public static long slowSolve(ArrayList<Station> stations, int curr, long[] dp, long[] costs) {
		if(curr == stations.size() - 1) return 0;
		if(dp[curr] != 0) return dp[curr];
		long min = Long.MAX_VALUE;
//		if(costs[curr] == 0 && curr != 0) {System.out.println(stations.get(curr));return -1;}
		for(int i = curr + 1; i < stations.size(); i++) {
			if(stations.get(curr).reach >= stations.get(i).dist)
				min = Math.min(slowSolve(stations, i, dp, costs), min);
		}
		if(min == Long.MAX_VALUE) return Long.MAX_VALUE;
		dp[curr] = min + costs[stations.get(curr).id];
//		if(curr == 0)
//			System.out.println(Arrays.toString(dp));
		return min + costs[stations.get(curr).id];
	}
	public static void getPath(int a, int b, ArrayList<Integer> path, int[] depth, int[] par) {
		ArrayList<Integer> upB = new ArrayList<Integer>();
		while(depth[a] > depth[b]) { path.add(a); a = par[a];}
		while(depth[b] > depth[a]) {upB.add(b); b = par[b];}
		while(a != b) { path.add(a); upB.add(b); a = par[a]; b = par[b];}
		path.add(a);
		for(int i = upB.size() - 1; i >= 0; i--)
			path.add(upB.get(i));
	}
	public static void DFS(int curr, int[] depth, ArrayList<Integer>[] adj) {
		for(int i = 0; i < adj[curr].size(); i++) {
			depth[adj[curr].get(i)] = depth[curr] + 1;
			DFS(adj[curr].get(i), depth, adj);
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
class Station implements Comparable<Station> {
	int id, reach, dist;
	
	public Station(int id, int reach, int dist) {
		super();
		this.id = id;
		this.reach = reach;
		this.dist = dist;
	}

	@Override
	public int compareTo(Station o) {
		if(reach != o.reach)
			return reach - o.reach;
		return dist - o.dist;
	}

	@Override
	public String toString() {
		return  "[" + id + "," + reach + "," + dist + "]";
	}
	
}
class MinSegmentTree {
	//from https://codeforces.com/blog/entry/18051
		int n;
		long[] segTree;
		public MinSegmentTree(long[] arr) {
			init(arr);
		}
		public void init(long[] arr) {
			n = arr.length;
			segTree = new long[n * 2];
			for(int i = 0; i < n ;i++)
				segTree[n + i] = arr[i];
			for(int i = n - 1; i >= 0; i--)
				segTree[i] = Math.min(segTree[i<<1], segTree[i<<1 | 1]);
		}
		void modify(int p, long value) {  // set value at position p
			  for (segTree[p += n] = value; p > 1; p >>= 1) segTree[p>>1] = Math.min(segTree[p], segTree[p^1]);
		}

		//interval [l, r)
		public long query(int l, int r) {
			if(r <= l)
				return query(r,l);
			long res = Long.MAX_VALUE;
			for(l += n, r += n; l < r; l >>= 1, r >>= 1) {
				if((l & 1) > 0) res = Math.min(res, segTree[l++]);
				if((r & 1) > 0) res = Math.min(res, segTree[--r]);
			}
			return res;
		}
	}
