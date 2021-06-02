import java.util.*;
import java.awt.Rectangle;
import java.io.*;
public class Perimetric {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("Perimeter.txt"));
		File file = new File("C:\\Users\\wanga\\Documents\\Perimeter.txt");
		file.createNewFile();
		file.setWritable(true);
		PrintWriter out = new PrintWriter(new FileWriter(file));
		int t = nextInt();
		int caseID = 1;
		while(t --> 0) {
			long n = nextLong(), k = nextLong();
			long[] l = new long[(int) n];
			long[] w = new long[(int) n];
			long[] h = new long[(int) n];
			{
				for(int i = 0; i < k; i++)
					l[i] = nextLong();
				long a = nextLong(), b = nextLong(), c = nextLong(), d = nextLong();
				for(int i = (int) k; i < n; i++)
					l[i] = (a * l[i - 2] + b * l[i - 1] + c) % d + 1;
				for(int i = 0; i < k; i++)
					w[i] = nextLong();
				a = nextLong(); b = nextLong(); c = nextLong(); d = nextLong();
				for(int i = (int) k; i < n; i++)
					w[i] = (a * w[i - 2] + b * w[i - 1] + c) % d + 1;
				for(int i = 0; i < k; i++)
					h[i] = nextLong();
				a = nextLong(); b = nextLong(); c = nextLong(); d = nextLong();
				for(int i = (int) k; i < n; i++)
					h[i] = (a * h[i - 2] + b * h[i - 1] + c) % d + 1;
			}
//			System.out.println(Arrays.toString(l));
//			System.out.println(Arrays.toString(w));
//			System.out.println(Arrays.toString(h));
			long[] p = new long[(int) n];
			TreeSet<Interval> arr = new TreeSet<Interval>();
			for(int i = 0; i < n; i++) {
				if(i != 0) p[i] += p[i - 1];
				p[i] += w[i] * 2 + h[i] * 2;
				Interval curr = new Interval(l[i], l[i] + w[i], h[i]);
				p[i] -= removeIntersectingIntervals(curr, arr);
				p[i] = Math.floorMod(p[i], 1000000007);
			}
//			System.out.println(arr);
//			System.out.println(Arrays.toString(p));
			long ans = 1;
			for(int i = 0; i < n; i++) {
				ans *= p[i];
				ans = Math.floorMod(ans, 1000000007);
			}
			out.println("Case #" + caseID++ + ": " + ans);
//			System.out.println(ans);
		}
		out.close();
	}
	private static long removeIntersectingIntervals(Interval curr, TreeSet<Interval> arr) {
		long sum = 0;
		Interval l = new Interval(0, curr.l, curr.height);
		Interval t = arr.higher(l);
		Interval next = null;
		if(t != null && t.l <= curr.r) {
			next = arr.higher(t);
			sum += t.height;
		}
//		System.out.println(arr);
		while(t != null) {
			if(t.l <= curr.r) {
				sum += 2 * (Math.min(t.r, curr.r) - Math.max(t.l, curr.l));
				if(next != null && t.r <= curr.r && t.r == next.l)
					sum += Math.max(next.height, t.height) - Math.min(next.height, t.height);
				else
					sum += t.height;
				if(next != null) {
					if(next.l != t.r && next.l <= curr.r)
						sum += next.height;
					if(next.l == t.r && ((t.r == curr.l && next.height < t.height) || (t.r == curr.r && next.height > t.height)))
						sum += Math.max(next.height, t.height) - Math.min(next.height, t.height);
				}
				Interval temp = t;
				t = next;
				if(next != null)
					next = arr.higher(next);
				arr.remove(temp);
				if(temp.l <= curr.l && temp.r >= curr.r) {
					if(temp.r > curr.r)
						arr.add(new Interval(curr.r, temp.r, temp.height));
					if(temp.l < curr.l)
						arr.add(new Interval(temp.l, curr.l, temp.height));
				} else {
					if(temp.l < curr.l) temp.r = curr.l;
					else if(temp.r > curr.r) temp.l = curr.r;
					if(temp.r > curr.r || temp.l < curr.l) arr.add(temp);
				}
			} else t = null;
		}
		arr.add(curr);
		return sum;
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
class Interval implements Comparable<Interval> {
	long l, r;
	long height;
	
	public Interval(long l, long r, long height) {
		super();
		this.l = l;
		this.r = r;
		this.height = height;
	}
	@Override
	public int compareTo(Interval o) {
		if(r != o.r)
			return Long.compare(r, o.r);
		return Long.compare(l, o.l);
	}
	@Override
	public String toString() {
		return "[l=" + l + ", r=" + r + ", h=" + height +"]";
	}
	
}
