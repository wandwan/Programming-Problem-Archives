import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class Solution {
    public int maxPoints(int[][] points) {
        if(points.length == 0) return 0;
    	HashMap<Line, Integer> arr = new HashMap<Line, Integer>();
    	int[] dupes = new int[points.length];
    	for(int i = 0; i < points.length; i++) {
    		if(points[i] == null) continue;
    		for(int j = i + 1; j < points.length; j++) {
    			if(points[j] == null) continue;
    			if(points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
    				points[j] = null;
    				dupes[i]++;
    			}
    		}
    	}
        for(int i = 0; i < points.length; i++) {
        	if(points[i] == null) continue;
        	HashSet<Line> used = new HashSet<Line>();
        	for(int j = i + 1; j < points.length; j++) {
        		if(points[j] == null) continue;
        		int a = points[j][1] - points[i][1];
        		int b = points[j][0] - points[i][0];
        		int g = GCD(Math.abs(a),Math.abs(b));
                if(g != 0) {
        		a /= g;
        		b /= g;
                }
        		int c = b*points[i][1]-a*points[i][0];
        		g = GCD(Math.abs(c), Math.abs(b));
                if(g != 0) {
        		g = b / g;
        		c *= g;
        		a *= g;
        		b *= g;
               }
        		if(a < 0) {
        			a *= -1; b *= -1; c *= -1;
        		}
        		Line l = new Line(a,b,c);
        		if(used.contains(l)) continue;
        		used.add(l);
        		if(!arr.containsKey(l))
        			arr.put(l, 0);
        		arr.replace(l, arr.get(l) + 1 + dupes[i] + dupes[j]);
        		if(arr.get(l) == 54) System.out.println(l);
        	}
        }
        int max = 0;
        for(int i = 0; i < points.length; i++)
        	max = Math.max(max, dupes[i]);
        Iterator<Line> t = arr.keySet().iterator();
        while(t.hasNext())
        	max = Math.max(max, arr.get(t.next()));
        return max + 1;
    }
    public int GCD(int a, int b) {
    	if(a == 0) return b;
    	if(b == 0) return a;
    	return GCD(b, a % b);
    }
}
class Line {
	int a, b, c;
	
	public Line(int a, int b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + c;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		if (c != other.c)
			return false;
		return true;
	}
	public String toString() {
		return a + " " + b + " " + c;
	}
}