import java.util.*;
import java.io.*;
public class HashTreesd {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt() + 1;
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = nextInt();
		}
		for(int i = 0; i < n - 1; i++) {
			if(arr[i] > 1 && arr[i + 1] > 1) {
				System.out.println("ambiguous");
			int par = 0;
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < arr[j]; k++) {
					if(j == i + 1 && k == 0)
						System.out.print(par - 1 + " ");
					else
						System.out.print(par + " ");
				}
				par += arr[j];
			}
			System.out.println();
			par = 0;
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < arr[j]; k++) {
					
						System.out.print(par + " ");
				}
				par += arr[j];
			}
			System.out.println();
			return;
			}
		}
		System.out.println("perfect");
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
