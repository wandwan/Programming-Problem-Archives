import java.util.*;
import java.io.*;
public class Storage {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = nextInt();
		int[] arr = new int[100000 + 1];
		for(int i = 0; i < n; i++) {
			arr[nextInt()]++;
		}
		TreeSet<Integer> max = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(arr[o2] != arr[o1])
					return arr[o2] - arr[o1];
				return o1 - o2;
			}
		});
		for(int i = 1; i < arr.length; i++)
			max.add(i);
//		System.out.println(max.peek());
//		System.out.println(arr[1]);
		int q = nextInt();
		for(int i = 0; i < q; i++) {
//			System.out.println(max);
			if(next().charAt(0) == '+') {
				int idx = nextInt();
				max.remove(idx);
				arr[idx]++;
				max.add(idx);
			} else {
				int idx = nextInt();
				max.remove(idx);
				arr[idx]--;
				max.add(idx);
			}
			int top = 0, sec = 0, thir = 0;
			if(max.size() != 0)
				top = max.pollFirst();
			if(max.size() != 0)
				sec = max.pollFirst();
			if(max.size()!= 0)
				thir = max.pollFirst();
			if(arr[top] >= 4 && (arr[top] >= 6 && arr[sec] >= 2 || arr[sec] >= 2 && arr[thir] >= 2 || arr[sec] >= 4 || arr[top] >= 8))
				out.println("YES");
			else out.println("NO");
			max.add(top);
			max.add(sec);
			max.add(thir);
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
class Pair implements Comparable<Pair>{
	int a, b;

	public Pair(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Pair o) {
		if(a != o.a)
			return a - o.a;
		return b - o.b;
	}

	@Override
	public String toString() {
		return "Pair [a=" + a + ", b=" + b + "]";
	}
	
}
