import java.util.*;
public class ABBADiv1 {
	String t;
	HashSet<String> m = new HashSet<String>();
	boolean f(boolean startReversed, int i, String s, boolean reversed)
	{
		if (i < 0 || t.length() < i + s.length()) return false;
		String hash = i + ":" + s + ":" + reversed;
		if (m.contains(hash)) return false;
 		for (int j = 0; j < s.length(); ++j) {
 			if (t.charAt(i + j) != s.charAt(j)) {
 				m.add(hash);
 				return false;
 			}
		}
		if (t.length() == s.length() && !reversed) return true;
		if (reversed) {
			return f(startReversed, i - 1, 'A' + s, reversed) || f(startReversed, i - 1, 'B' + s, !reversed);
		}
		return f(startReversed, i, s + 'A', reversed) || f(startReversed, i, s + 'B', !reversed);
	}

	public String canObtain(String s, String target)
    {
    	t = target;
    	boolean reversed = false;
    	do {
        	for (int i = 0; i < t.length(); ++i) {
        		if (f(reversed, i, s, reversed)) {
        			return "Possible";
        		}
        	}
        	s = new StringBuilder(s).reverse().toString();
    		reversed = !reversed;
    		m.clear();
    	} while (reversed);
    	return "Impossible";
    }
// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
        	eq(5,(new ABBADiv1()).canObtain("A", "AB"),"Impossible");
        	eq(6,(new ABBADiv1()).canObtain("B", "AB"),"Impossible");
        	eq(6,(new ABBADiv1()).canObtain("B", "B"),"Possible");
        	eq(1,(new ABBADiv1()).canObtain("BAAAAABAA", "BAABAAAAAB"),"Possible");
        	eq(0,(new ABBADiv1()).canObtain("A", "BABA"),"Possible");
            eq(2,(new ABBADiv1()).canObtain("A", "ABBA"),"Impossible");
            eq(3,(new ABBADiv1()).canObtain("AAABBAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"),"Possible");
            eq(4,(new ABBADiv1()).canObtain("AAABAAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"),"Impossible");
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
