
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ1365_꼬인_전깃줄
public class BOJ1365_꼬인_전깃줄
{
	public static void main(String[ ] args) throws IOException
	{
		//inputAndSettingData( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int LIS[] = new int[ N ];
		int idx = 0;

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			int here = Integer.parseInt(st.nextToken( ));
			if (idx == 0)
			{
				LIS[ idx++ ] = here;
			}
			else
			{
				if (LIS[ idx - 1 ] >= here) LIS[ lowerbound(LIS, here, idx) ] = here;
				else LIS[ idx++ ] = here;
			}
		}
		System.out.println(N - idx);
	}

	static int lowerbound(int arr[], int target, int len)
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
