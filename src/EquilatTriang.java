import java.util.*;
import java.io.*;
public class EquilatTriang {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("triangles.in"));
		int n = nextInt();
		int[][] arr = new int[n * 3][n * 3];
		int[][] pre = new int[n * 3][n * 3];
		int[][] pre1 = new int[n * 3][n * 3];
		for(int i = 0; i < n; i++) {
			char[] ch = next().toCharArray();
			for(int j = 0; j < n; j++)
				arr[i + n][j + n] = (ch[j] == '*' ? 1 : 0);
		}
		for(int i = 0; i < 3 * n; i++) {
			for(int j = 0; j < 3 * n; j++) {
				pre[i][j] = arr[i][j];
				pre1[i][j] = arr[i][j];
					if(i > 0 && j > 0)
						pre[i][j] += pre[i - 1][j - 1];
					if(i > 0 && j < 3 * n - 1)
						pre1[i][j] += pre1[i - 1][j + 1];
			}
		}
//		for(int[] e: pre)
//			System.out.println(Arrays.toString(e));
//		for(int[] e: pre1)
//			System.out.println(Arrays.toString(e));
		int sub = 0;
		int ans = 0;
		for(int j = 0; j < n; j++) {
			for(int i = 0; i < n; i++) {
				if(arr[i + n][j + n] == 1) {
//					System.out.println(i + " " + j);
					//down and to the right
					//check for special case, if there is also something up and to the right of same distance we double count (on both loops)
					for(int k = 1; k + i < n && k + j < n; k++) {
						if(arr[i + k + n][j + k + n] == 1) {
//							System.out.println(k + " k1 " + i + " " + j);
							if(i - k + n >= 0 && arr[i - k + n][j + k + n] == 1) {
								sub++;
								if(j + 2 * k + n < 3 * n && arr[i + n][j + 2 * k + n] == 1)
									sub++;
							}
							if(i + 2 * k + n < 3 * n && arr[i + 2 * k + n][j + n] == 1) {
								sub++;
								if(i + k < n && j - k >= 0 && arr[i + k + n][j - k + n] == 1)
									sub++;
							}
							ans += pre[i + n][j + n + 2 * k] - pre[i - k + n][j + k + n] + arr[i - k + n][j + k + n];
							ans += pre[i + n + 2 * k][j + n] - pre[i + k + n][j - k + n] + arr[i + k + n][j - k + n];
						}
					}
					//up and to the right
					for(int k = 1; i - k >= 0 && k + j < n; k++) {
						if(arr[i - k + n][j + k + n] == 1) {
//							System.out.println(k + " k2 " + i + " " + j);
							ans += pre1[i + n - k][j + n - k] - pre1[i - 2 * k + n][j + n] + arr[i - 2 * k + n][j + n];
							ans += pre1[i + n + k][j + n + k] - pre1[i + n][j + 2 * k + n] + arr[i + n][j + 2 * k + n];
						}
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("triangles.out");
		out.println(ans - sub);
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
