
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ2352_반도체_설계
// LIS
public class BOJ2352_반도체_설계
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
			int cur = Integer.parseInt(st.nextToken( ));
			if (idx == 0)
			{
				LIS[ idx++ ] = cur;
			}
			else
			{
				if (LIS[ idx - 1 ] >= cur)
				{
					LIS[ lowerbound(LIS, cur, idx) ] = cur;
				}
				else
				{
					LIS[ idx++ ] = cur;
				}
			}
		}
		System.out.println(idx);
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
}
