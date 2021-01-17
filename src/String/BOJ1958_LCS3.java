
package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ1958_LCS3
//3개의 문자열의 LCS는 dp를 3차원으로 생성하여 풀이
public class BOJ1958_LCS3
{
	static String str1, str2, str3;
	static int dp[][][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static int solve( )
	{
		for (int i = 1; i <= str1.length( ); i++)
		{
			for (int j = 1; j <= str2.length( ); j++)
			{
				for (int k = 1; k <= str3.length( ); k++)
				{
					if (str1.charAt(i - 1) == str2.charAt(j - 1)
					                && str2.charAt(j - 1) == str3.charAt(k - 1))
					{
						dp[ i ][ j ][ k ] = dp[ i - 1 ][ j - 1 ][ k - 1 ] + 1;
					}
					else
					{
						int max = Math.max(dp[ i - 1 ][ j ][ k ], dp[ i ][ j - 1 ][ k ]);
						dp[ i ][ j ][ k ] = Math.max(max, dp[ i ][ j ][ k - 1 ]);
					}
				}
			}
		}

		return dp[ str1.length( ) ][ str2.length( ) ][ str3.length( ) ];
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = new String(br.readLine( ));
		str2 = new String(br.readLine( ));
		str3 = new String(br.readLine( ));
		dp = new int[ str1.length( ) + 1 ][ str2.length( ) + 1 ][ str3.length( ) + 1 ];
	}
}
