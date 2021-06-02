import java.util.*;
import java.io.*;
public class Choclate {

	static BufferedReader br;
	static StringTokenizer tokenizer;
//	static ArrayList<Integer> tester = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = nextInt();
//		for(int i = 1; i < n + 1; i++)
//			tester.add(i);
//		Collections.shuffle(tester);
		Stack<Integer> arr = new Stack<Integer>();
		for(int i = 0; i < n; i++)
			arr.push(i + 1);
		int[] ans = new int[n];
		while(arr.size() > 1) {
			int i = arr.pop();
			int j = arr.pop();
			out.println("? " + i + " " + j);
			out.flush();
			int t = nextInt();
//			int t = tester.get(i - 1) % tester.get(j - 1);
			out.println("? " + j + " " + i);
			out.flush();
			int t1 = nextInt();
//			int t1 = tester.get(j - 1) % tester.get(i - 1);
			if(t1 > t) {
				ans[j - 1] = t1;
				arr.push(i);
			} else {
				ans[i - 1] = t;
				arr.push(j);
			}
		}
//		System.out.println(Arrays.toString(ans));
		boolean[] has = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(ans[i] != 0)
				has[ans[i] - 1] = true;
		}
		for(int i = 0; i < n; i++)
			if(!has[i])
				ans[arr.pop() - 1] = i + 1;
		out.print("! ");
		for(int i = 0; i < n; i++)
			out.print(ans[i] + " ");
		out.println();
		out.flush();
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
