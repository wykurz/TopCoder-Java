import java.util.*;
public class FallingSand {
    public String[] simulate(String[] board)
    {
    	final int n = board.length;
    	final int m = board[0].length();
    	boolean[][] sand = new boolean[n][m];
    	boolean[][] obst = new boolean[n][m];
    	
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			if ('o' == board[i].charAt(j)) {
    				sand[i][j] = true;
    			}
    			else if ('x' == board[i].charAt(j)) {
    				obst[i][j] = true;
    			}
    		}
    	}
    	
    	boolean modified = true;
    	while (modified) {
    		modified = false;
        	for (int i = 0; i < n - 1; ++i) {
        		for (int j = 0; j < m; ++j) {
        			if (sand[i][j] && !(sand[i + 1][j] || obst[i + 1][j])) {
        				sand[i][j] = false;
        				sand[i + 1][j] = true;
        				modified = true;
        			}
        		}
        	}
    	}
    	
    	String[] endBoard = new String[n];
    	for (int i = 0; i < n; ++i) {
    		String s = "";
    		for (int j = 0; j < m; ++j) {
    			if (sand[i][j]) {
    				s = s + 'o';
    			}
    			else if (obst[i][j]) {
    				s = s + 'x';
    				
    			}
    			else {
    				s = s + '.';
    			}
    		}
    		endBoard[i] = s;
    	}
    	
    	return endBoard;
    }
// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new FallingSand()).simulate(new String[] {"ooooo",
                "..x..",
                "....x",
                ".....",
                "....o"}),new String[] {"..o..", "..x.o", "....x", ".....", "oo.oo" });
            eq(1,(new FallingSand()).simulate(new String[] {"..o..", 
                "..x.o", 
                "....x", 
                ".....", 
                "oo.oo" }),new String[] {"..o..", "..x.o", "....x", ".....", "oo.oo" });
            eq(2,(new FallingSand()).simulate(new String[] {"ooooxooo.ooxo.oxoxoooox.....x.oo"}),new String[] {"ooooxooo.ooxo.oxoxoooox.....x.oo" });
            eq(3,(new FallingSand()).simulate(new String[] {"o",
                ".",
                "o",
                ".",
                "o",
                ".",
                "."}),new String[] {".", ".", ".", ".", "o", "o", "o" });
            eq(4,(new FallingSand()).simulate(new String[] {"oxxxxooo",
                "xooooxxx",
                "..xx.ooo",
                "oooox.o.",
                "..x....."}),new String[] {"oxxxxooo", "x.oo.xxx", "..xxo...", ".oo.x.o.", "ooxo.ooo" });
            eq(5,(new FallingSand()).simulate(new String[] {"..o..o..o..o..o..o..o..o..o..o..o",
                "o..o..o..o..o..o..o..o..o..o..o..",
                ".o..o..o..o..o..o..o..o..o..o..o.",
                "...xxx...xxx...xxxxxxxxx...xxx...",
                "...xxx...xxx...xxxxxxxxx...xxx...",
                "...xxx...xxx......xxx......xxx...",
                "...xxxxxxxxx......xxx......xxx...",
                "...xxxxxxxxx......xxx......xxx...",
                "...xxxxxxxxx......xxx......xxx...",
                "...xxx...xxx......xxx............",
                "...xxx...xxx...xxxxxxxxx...xxx...",
                "...xxx...xxx...xxxxxxxxx...xxx...",
                "..o..o..o..o..o..o..o..o..o..o..o",
                "o..o..o..o..o..o..o..o..o..o..o..",
                ".o..o..o..o..o..o..o..o..o..o..o."}),new String[] {".................................", ".................................", "...ooo...ooo...ooooooooo...ooo...", "...xxx...xxx...xxxxxxxxx...xxx...", "...xxx...xxx...xxxxxxxxx...xxx...", "...xxxoooxxx......xxx......xxx...", "...xxxxxxxxx......xxx......xxx...", "...xxxxxxxxx......xxx......xxx...", "...xxxxxxxxx......xxx......xxx...", "...xxx...xxx......xxx............", "...xxx...xxx...xxxxxxxxx...xxx...", "...xxx...xxx...xxxxxxxxx...xxx...", ".................................", "ooo.........ooo.........ooo...ooo", "ooooooooooooooooooooooooooooooooo" });
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
