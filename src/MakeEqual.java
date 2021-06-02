import java.util.*;
import java.io.*;
public class MakeEqual {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int k = nextInt();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> jump = new ArrayList<Integer>();
		for(int i = 0; i < n; i++)
			arr.add(nextInt());
		arr.sort(null);
		long m = arr.get(n / 2);
//		System.out.println(m);
		long min = 0;
		for(int i = 0; i < n / 2; i++)
			min += m - arr.get(i);
		for(int i = n / 2; i < n; i++)
			min += arr.get(i) - m;
		if(min >= n - k)
			min -= n - k;
		for(int i = 0; i < n; i++) {
			jump.add(i);
			int j = i + 1;
			while(j < n && arr.get(j) == arr.get(i))
				j++;
			if(j - i >= k) {
				System.out.println(0);
				return;
			}
			jump.add(j - 1);
			i = j - 1;
		}
		long curr = 0;
		int prev = arr.get(0);
		long num = 0;
		for(int i = 0; i < jump.size(); i+= 2) {
			curr += (arr.get(jump.get(i)) - prev) * num;
			if(num + jump.get(i + 1) - jump.get(i) + 1 >= k) {
				curr -= num + jump.get(i + 1) - jump.get(i) + 1 - k;
				System.out.println(num + jump.get(i + 1) - jump.get(i) + 1 - k);
				break;
			}
			num += jump.get(i + 1) - jump.get(i) + 1;
			prev = arr.get(jump.get(i));
		}
		min = Math.min(min, curr);
		curr = 0;
		prev = arr.get(n - 1);
		num = 0;
		for(int i = jump.size() - 2; i >= 0; i-= 2) {
			curr -= (arr.get(jump.get(i)) - prev) * num;
			if(num + jump.get(i + 1) - jump.get(i) + 1 >= k) {
				curr -= num + jump.get(i + 1) - jump.get(i) + 1 - k;
				break;
			}
			num += jump.get(i + 1) - jump.get(i) + 1;
			prev = arr.get(jump.get(i));
		}
		min = Math.min(min, curr);
		System.out.println(min);
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
