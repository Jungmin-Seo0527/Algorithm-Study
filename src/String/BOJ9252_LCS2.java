
package String;

import java.io.*;
import java.util.*;

// BOJ9252_LCS2
// LCS의 문자열을 출력
public class BOJ9252_LCS2
{
	static class LongestCommomStrings
	{
		String str1, str2;
		int dp[][];

		public LongestCommomStrings(String str1, String str2)
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
		String lcsString( )
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
		//LongestCommomStrings lcs = new LongestCommomStrings(str1, str2);
		LongestCommonSequences lcs = new LongestCommonSequences(str1, str2);
		int ans = lcs.solve( );
		System.out.println(ans);
		if (ans != 0) System.out.println(lcs.lcsString( ));
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
	}
}
