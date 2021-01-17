
package String;

import java.io.*;
import java.util.*;

// BOJ9251_LCS
public class BOJ9251_LCS
{
	static class LongestCommomStrings
	{
		String str1, str2;

		public LongestCommomStrings(String str1, String str2)
		{
			this.str1 = new String(str1);
			this.str2 = new String(str2);
		}

		int solve( )
		{
			int ret = 0;

			int dp[][] = new int[ str1.length( ) + 1 ][ str2.length( ) + 1 ];
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

		public LongestCommonSequences(String str1, String str2)
		{
			this.str1 = new String(str1);
			this.str2 = new String(str2);
		}

		int solve( )
		{
			int ret = 0;
			int dp[][] = new int[ str1.length( ) + 1 ][ str2.length( ) + 1 ];
			for (int i = 1; i <= str1.length( ); i++)
			{
				for (int j = 1; j <= str2.length( ); j++)
				{
					if (str1.charAt(i - 1) == str2.charAt(j - 1))
					{
						dp[ i ][ j ] = dp[ i - 1 ][ j - 1 ] + 1;
					}
					else
					{
						dp[ i ][ j ] = Math.max(dp[ i ][ j - 1 ], dp[ i - 1 ][ j ]);
					}
					if (ret < dp[ i ][ j ]) ret = dp[ i ][ j ];
				}
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
		System.out.println(lcs.solve( ));

	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
	}
}
