
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ2110_공유기_설치
// parametric search
public class BOJ2110_공유기_설치
{
	static final int SZ = (int) 1e9;
	static int N, C;
	static int arr[];
	static int end, ans;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
		System.out.println(end);
	}

	static void solve( )
	{
		int start = 1;
		while (start <= end)
		{
			int mid = (start + end) >> 1;
			int cnt = 1;
			int temp = arr[ 0 ];
			for (int i = 1; i < N; i++)
			{
				int d = arr[ i ] - temp;
				if (d >= mid)
				{
					cnt++;
					temp = arr[ i ];
				}
			}
			//System.out.println(mid + " " + cnt);
			if (cnt >= C)
			{
				ans = mid;
				start = mid + 1;
			}
			else end = mid - 1;
			//System.out.println(start + " " + end);
			//System.out.println( );
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		C = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int n = Integer.parseInt(st.nextToken( ));
			arr[ i ] = n;
		}
		Arrays.sort(arr);
		end = arr[ N - 1 ] - arr[ 0 ];
	}
}
