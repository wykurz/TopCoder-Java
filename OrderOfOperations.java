// BEGIN CUT HERE
// END CUT HERE
import java.util.*;
public class OrderOfOperations
{
	public int minTime(String[] ss)
    {
    	int n = ss.length;
    	int[] s = new int[n];
    	int last = 0;
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < ss[i].length(); ++j) {
    			if (ss[i].charAt(j) == '1') s[i] |= 1 << j;
    		}
    		last |= s[i];
    	}
    	int[] m = new int[1 << 20]; // index to d, t
    	m[0] = 1;
    	int[] d = new int[(1 << 20) + 1]; // distance
    	int[] t = new int[(1 << 20) + 1]; // state
    	int r = Integer.MAX_VALUE;
    	int c = 1;
    	long debug = 0;
    	for (int i = 0; i < n; ++i) {
    		++debug;
        	int c2 = c;
    		for (int j = 1; j <= c2; ++j) {
    			++debug;
    			int s1 = t[j];
    			int d1 = d[j];
				if (r <= d1 + Integer.bitCount(s1 ^ last)) {
					continue;
				}
    			for (int k = 0; k < n; ++k) {
    				++debug;
    				int s2 = s1 | s[k];
    				int b = Integer.bitCount(s1 ^ s2);
    				int d2 = d1 + b * b;
    				if (m[s2] == 0) {
    					m[s2] = ++c;
    					d[m[s2]] = d2;
    					t[m[s2]] = s2;
    				}
    				else if (d2 < d[m[s2]]) {
    					d[m[s2]] = d2;
    				}
	    			if (last == s2) {
	    				r = d[m[s2]];
	    			}
    			}
    		}
    	}
    	// System.err.println(debug);
    	return r;
    }
// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new OrderOfOperations()).minTime(new String[] {
                "111",
                "001",
                "010"
               }),3);
            eq(1,(new OrderOfOperations()).minTime(new String[] {
                "11101",
                "00111",
                "10101",
                "00000",
                "11000"
               }),9);
            eq(2,(new OrderOfOperations()).minTime(new String[] {
                 "11111111111111111111"
               }),400);
            eq(3,(new OrderOfOperations()).minTime(new String[] {
                 "1000",
                 "1100",
                 "1110"
               }),3);
            eq(4,(new OrderOfOperations()).minTime(new String[] {
                 "111",
                 "111",
                 "110",
                 "100"
               }),3);
            eq(5,(new OrderOfOperations()).minTime(new String[] {
            		"00001010000000100000", "00010101000110000001", "00010000001000000010", "00000001100000000100", "00000001000010000000", "10001001010000100101", "00000100110010000000", "10001010001000100010", "00000100011000000010", "00000000000000010000", "00010001001000000011", "00010011000011010000", "01000001000000000000", "00000100111001000010", "00000000000000011000", "00000010000000001000", "00000001001000000000", "01111000000011010000", "00101110000010000000", "00010101000000010101", "00000000000100000010", "00000000001000000100", "00100010010000000110", "00011001101000000110", "00000000011000001011", "10000010000000001110", "00100000100000010011", "00100100100000000000", "11000101010001000000", "00000000100100000000", "01110000100001000100", "00010001000000101001", "00010100000000000010", "00010000000000000100", "00000010000000000100", "00000010100000011001", "01000000000000000100", "10001010000100000000", "00010000110000100000", "01001100100000000001", "10010000110001000100", "00100010100010010000", "10000001010000010100", "10000000000000101110", "00001101000001000000", "00000000100000010101", "00100100000001011000", "00000000001101010000", "11000000001000110001", "10000001001001100000"
                  }),20);
        } catch( Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }
    private static void eq( int n, int a, int b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, char a, char b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected '"+b+"', received '"+a+"'.");
    }
    private static void eq( int n, long a, long b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"L, received "+a+"L.");
    }
    private static void eq( int n, boolean a, boolean b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, String a, String b ) {
        if ( a != null && a.equals(b) )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"\", received \""+a+"\".");
    }
    private static void eq( int n, int[] a, int[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++)
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, long[] a, long[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, String[] a, String[] b ) {
        if ( a.length != b.length) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if( !a[i].equals( b[i])) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void print( int a ) {
        System.err.print(a+" ");
    }
    private static void print( long a ) {
        System.err.print(a+"L ");
    }
    private static void print( String s ) {
        System.err.print("\""+s+"\" ");
    }
    private static void print( int[] rs ) {
        if ( rs == null) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( long[] rs) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( String[] rs ) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print( "\""+rs[i]+"\"" );
            if( i != rs.length-1)
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void nl() {
        System.err.println();
    }
// END CUT HERE
}
