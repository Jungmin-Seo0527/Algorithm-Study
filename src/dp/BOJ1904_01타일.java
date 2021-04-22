package dp;

import java.io.*;
import java.util.*;

// BOJ1904_01타일
public class BOJ1904_01타일
{
	static int N;
	static int[ ] dp;
	static final int f = 15746;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		dp = new int[ N + 3 ];
		dp[ 0 ] = 0;
		dp[ 1 ] = 1;
		dp[ 2 ] = 2;
	}

	static void solve( )
	{
		for (int i = 3; i <= N; i++)
		{
			dp[ i ] = (dp[ i - 1 ] % f + dp[ i - 2 ] % f) % f;
		}
		System.out.println(dp[ N ]);
	}
}
