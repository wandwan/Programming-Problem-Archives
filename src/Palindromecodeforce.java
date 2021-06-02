import java.util.*;
import java.io.*;
public class Palindromecodeforce {

	static BufferedReader br;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int tc = nextInt();
		PrintWriter out = new PrintWriter(System.out);
		while(tc --> 0) {
			String s = next();
			StringBuilder sb = new StringBuilder(s);
			Str s1 = new Str(s);
			sb.reverse();
			Str s2 = new Str(sb.toString());
			//case 1
			String max = s1.s.substring(0,1);
//			int max1 = -1;
//			int max2 = -1;
//			for(int i = 1; i <= sb.length() / 2; i++) {
//				int l = sb.length() - 2 * (i);
//				int r = l + i;
//				if(l > 0 && s1.subHash(0, i) == s2.subHash(l, r)) {
//					max1 = i;
//				} else if(l - 1 > 0 && s1.subHash(0, i) == s2.subHash(l - 1, r - 1)) {
//					max2 = i;
//				}
//			}
//			if(max2 != -1 && max2 >= max1)
//				max = s2.s.substring(0, 2 * max2 + 1);
//			else if(max1 > max2)
//				max = s1.s.substring(0, 2 * max1);
//			max1 = -1;
//			max2 = -1;
//			for(int i = 1; i <= sb.length() / 2; i++) {
//				int l = sb.length() - 2 * (i);
//				int r = l + i;
//				if(l > 0 && s2.subHash(0, i) == s1.subHash(l, r)) {
//					max1 = i;
//				} else if(l - 1 > 0 && s2.subHash(0, i) == s1.subHash(l - 1, r - 1)) {
//					max2 = i;
//				}
//			}
//			if(max1 * 2 > max.length())
//				max = s1.s.substring(sb.length() - 2 * max1, sb.length());
//			else if(max2 * 2 + 1 > max.length())
//				max = s1.s.substring(sb.length() - 2 * max2 - 1, sb.length());
			if(s.substring(0,(s.length() + 1) / 2).equals( s2.s.substring(0,(s.length() + 1) / 2))) {
				out.println(s);
				continue;
			}
			String t = getMax(s1,s2,s, 0);
			if(t.length() > max.length())
				max = t;
			t = getMax(s2,s1,s, 0);
			if(t.length() > max.length())
				max = t;
			for(int i = 0; i < sb.length() / 2; i++) {
				if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
					String t2 = getMax(s1,s2,s, i);
					if(t2.length() == 0) t2 = s.charAt(i) + "";
					String t1 = getMax(s2,s1,s,i);
					if(t1.length() > t2.length())
						t2 = t1;
					t = (i != 0 ? s.substring(0,i):"") + t2 + (i != 0 ? s.substring(s.length() - i,s.length()):"") ;			
					max = t;
					break;
				}
			}
			out.println(max);
		}
		out.close();
	}
	public static String getMax(Str s1, Str s2, String s, int l1) {
		String max = "";
		int max1 = -1;
		int max2 = -1;
		for(int i = 1; i <= s.length() / 2; i++) {
			int l = s.length() - 2 * (i) - l1;
			int r = l + i;
			if(l > 0 && s1.subHash(l1, i + l1) == s2.subHash(l, r)) {
				max1 = i;
			} if(l - 1 > 0 && s1.subHash(l1, i + l1) == s2.subHash(l - 1, r - 1)) {
				max2 = i;
			}
		}
		if(max2 != -1 && max2 >= max1)
			max = s1.s.substring(l1,l1 + 2 * max2 + 1);
		else if(max1 > max2)
			max = s1.s.substring(l1,l1 + 2 * max1);
		return max;
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
class Str {
	String s;
	int[] pows;
	int[] subHash;
	int[] inv;
	int hash;
	@Override
	public int hashCode() {
		return hash;
	}
	public Str(String s) {
		this.s = s;
		subHash = computeHash(s);
		hash = subHash[s.length() - 1];
		pows = new int[s.length() + 1];
		inv = new int[s.length() + 1];
		pows[0] = 1;
		for(int i = 1; i < pows.length; i++)
			pows[i] = (pows[i - 1] * 53) % 1000000009;
		inv[0] = 1;
		for(int i = 1; i < s.length(); i++)
			inv[i] = modInv(pows[i], 1000000009);
	}
	//returns hash of [l,r)
	public int subHash(int l, int r) {
		if(l == 0)
			return subHash[r - 1];
		int t1 = subHash[r - 1];
		int t2 = subHash[l - 1];
		return (int) (((long) (t1 - t2) * inv[l]) % 1000000009l);
	}
	public int[] computeHash(String s) {
		if(subHash != null)
			return subHash;
		int[] allHash = new int[s.length()];
		int hash = 0;
		int pow = 1;
		for(int i = 0; i < s.length(); i++) {
			hash = (int) (( hash + (long) (s.charAt(i) - 'a' + 1) * pow) % 1000000009);
			pow = (int) (((long) pow * 53) % 1000000009);
			allHash[i] = hash;
		}
		return allHash;
	}
	public static long pow(long base, int exp, int mod) {
		long result = 1;
		while(true) {
			if((exp & 1) == 1)
				result = (result * base) % mod;
			exp >>= 1;
			if(exp == 0)
				break;
			base = (base * base) % mod;
		}
		return result;
	}
	public static int modInv(int a, int m) {
		return (int) pow(a, m - 2, m);
	}
}