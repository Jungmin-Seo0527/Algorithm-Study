
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ2568_전깃줄2
// LIS
public class BOJ2568_전깃줄2
{
	static final int SZ = 500000;

	public static void main(String[ ] args) throws IOException
	{
		//inputAndSettingData( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int arr[][] = new int[ SZ + 1 ][ 2 ];
		int LIS[] = new int[ SZ + 1 ];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int idx = Integer.parseInt(st.nextToken( ));
			int n = Integer.parseInt(st.nextToken( ));
			arr[ idx ][ 0 ] = n;
		}

		// LISx
		int idx = 0;
		for (int i = 1; i <= SZ; i++)
		{
			if (arr[ i ][ 0 ] == 0) continue;
			if (idx == 0)
			{
				arr[ i ][ 1 ] = idx;
				LIS[ idx++ ] = arr[ i ][ 0 ];
			}
			else
			{
				if (LIS[ idx - 1 ] >= arr[ i ][ 0 ])
				{
					int temp = lowerBound(LIS, arr[ i ][ 0 ], idx);
					arr[ i ][ 1 ] = temp;
					LIS[ temp ] = arr[ i ][ 0 ];
				}
				else
				{
					arr[ i ][ 1 ] = idx;
					LIS[ idx++ ] = arr[ i ][ 0 ];
				}
			}
		}
		System.out.println(N - idx);

		// 없애도 되는 전길줄 구하기
		// LIS에 포함되는 숫자들을 없애고 남은 수들이 
		// 교차시키지 않기 위해 없애야 하는 전깃줄이다
		int ret[] = new int[ N - idx ];
		int ret_idx = 0;
		idx--;
		for (int i = SZ; i > 0; i--)
		{
			if (arr[ i ][ 0 ] == 0) continue;
			if (arr[ i ][ 1 ] == idx)
			{
				//System.out.println(arr[i][0]+" "+idx);
				arr[ i ][ 0 ] = 0;
				idx--;
			}
			else
			{
				ret[ ret_idx++ ] = i;
			}
		}

		StringBuilder sb = new StringBuilder( );
		for (int i = ret_idx - 1; i >= 0; i--)
		{
			sb.append(ret[ i ] + "\n");
		}
		System.out.println(sb);
	}

	static int lowerBound(int arr[], int target, int len)
	{
		int start = 0;
		int end = len - 1;
		while (start < end)
		{
			int mid = (start + end) >> 1;
			if (arr[ mid ] >= target) end = mid;
			else start = mid + 1;
		}
		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
	}
}
