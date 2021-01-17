
package Algorithm;

import java.io.*;
import java.util.*;

// LCS는 두가지가 있다. 
// Longest Common Strings는 공통되는 문자열이 붙어있어야 한다.
// Longest Common Sequences 는 공통되는 순열로 서로 떨어져 있어도 상관 없다
public class LCS
{
	static class LongestCommonStrings
	{
		String str1, str2;
		int dp[][];

		public LongestCommonStrings(String str1, String str2)
		{
			this.str1 = new String(str1);
			this.str2 = new String(str2);
			this.dp = new int[ str1.length( ) + 1 ][ str2.length( ) + 1 ];
		}

		int solve( )
		{
			int ret = 0;

			for (int i = 1; i <= str1.length( ); i++)
			{
				for (int j = 1; j <= str2.length( ); j++)
				{
					if (str1.charAt(i - 1) == str2.charAt(j - 1))
					{
						dp[ i ][ j ] = dp[ i - 1 ][ j - 1 ] + 1;
					}
					if (ret < dp[ i ][ j ]) ret = dp[ i ][ j ];
				}
			}
			return ret;
		}
	}

	static class LongestCommonSequences
	{
		String str1, str2;
		int dp[][];
		int root[][];

		public LongestCommonSequences(String str1, String str2)
		{
			this.str1 = new String(str1);
			this.str2 = new String(str2);
			this.dp = new int[ str1.length( ) + 1 ][ str2.length( ) + 1 ];
			this.root = new int[ str1.length( ) + 1 ][ str2.length( ) + 1 ];
		}

		int solve( )
		{
			int ret = 0;
			for (int i = 1; i <= str1.length( ); i++)
			{
				for (int j = 1; j <= str2.length( ); j++)
				{
					if (str1.charAt(i - 1) == str2.charAt(j - 1))
					{
						dp[ i ][ j ] = dp[ i - 1 ][ j - 1 ] + 1;
						root[ i ][ j ] = 1;
					}
					else
					{
						dp[ i ][ j ] = Math.max(dp[ i ][ j - 1 ], dp[ i - 1 ][ j ]);
						if (dp[ i ][ j ] == dp[ i ][ j - 1 ]) root[ i ][ j ] = 2;
						else root[ i ][ j ] = 3;
					}
					if (ret < dp[ i ][ j ]) ret = dp[ i ][ j ];
				}
			}
			return ret;
		}

		// 1 : 대각선, 2: 왼쪽, 3: 위로 
		String getLcsString( )
		{
			String ret = "";
			int row = str1.length( );
			int col = str2.length( );
			while (true)
			{
				if (row == 0 || col == 0) break;
				if (root[ row ][ col ] == 1)
				{
					ret = str1.charAt(row - 1) + ret + "";
					row--;
					col--;
				}
				else if (root[ row ][ col ] == 2) col--;
				else if (root[ row ][ col ] == 3) row--;
			}
			return ret;
		}
	}

	public static void main(String[ ] args) throws IOException
	{
		//inputAndSettingData( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine( );
		String str2 = br.readLine( );
		//LongestCommonStrings lcs = new LongestCommonStrings(str1, str2);
		LongestCommonSequences lcs = new LongestCommonSequences(str1, str2);
		int ans = lcs.solve( );
		System.out.println(ans);
		if (ans != 0) System.out.println(lcs.getLcsString( ));
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
	}
}
