import java.util.*;
import java.io.*;
public class Logs {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("Log.txt"));
		File file = new File("C:\\Users\\wanga\\Documents\\Log.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out = new PrintWriter(new FileWriter(file));
		int t = nextInt();
		int caseID = 1;
		while(t --> 0) {
			int n = nextInt();
			int m = nextInt();
			int k = nextInt();
			int s = nextInt();
			int[] pos = new int[m];
			int[] driver = new int[n];
			{
			for(int i = 0; i < k; i++)
				driver[i] = nextInt();
			int a = nextInt(), b = nextInt(), c = nextInt(), d = nextInt();
			for(int i = k; i < n; i++)
				driver[i] = (int) (((long) driver[i - 2] * a + (long) driver[i - 1] * b + (long) c) % d) + 1;
			for(int i = 0; i < k; i++)
				pos[i] = nextInt();
			 a = nextInt(); b = nextInt(); c = nextInt(); d = nextInt();
				for(int i = k; i < m; i++)
					pos[i] = (int) (((long) pos[i - 2] * a + (long) pos[i - 1] * b + (long) c) % d) + 1;
			}
			Arrays.sort(pos);
			Arrays.sort(driver);
			int l = 0;
			int r = 1000000001;
			while(l < r) {
				int mid = (l + r) / 2;
				if(ok(mid, pos, driver)) r = mid;
				else l = mid + 1;
			}
			out.println("Case #" + caseID++ + ": " + l);
			System.out.println(l);
		}
		out.close();
	}
	public static boolean ok(int time, int[] pos, int[] driver) {
		int l = 0;
		for(int i = 0; i < driver.length; i++) {
			if(l == pos.length) return true;
			if(pos[l] > driver[i]) {
				if(pos[l] - driver[i] > time)
					continue;
				else {
					while(l < pos.length && pos[l] - driver[i] <= time)
						l++;
				}
			} else {
				int left = driver[i] - pos[l];
				if(left > time) return false;
				while(l < pos.length && pos[l] < driver[i])
					l++;
				while(l < pos.length && (pos[l] - driver[i] + 2 * left <= time || 2 * (pos[l] - driver[i]) + left <= time))
					l++;
			}
		}
		if(l == pos.length) return true;
		return false;
	}
	public static void update(int[] t, TreeSet<Integer> pos) {
		t[1] = Integer.MAX_VALUE;
		if(pos.higher(t[0]) != null) t[1] = pos.higher(t[0]) - t[0];
		if(pos.lower(t[0]) != null) t[1] = t[0] - pos.lower(t[0]);
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
