
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ14003_가장_긴_증가하는_부분_수열5
// 기존 LIS 문제에서 각 숫자들이 LIS index를 받을때 그 인덱스를 기록한다.
// 전제 수열들이 받은 인덱스들을 오른쪽부터 왼쪽순으로 진행시키면서 내림차순으로 가장 처음 만나는 숫자들을 기록

public class BOJ14003_가장_긴_증가하는_부분_수열5
{
	static final int SZ = 1000000;

	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int arr[] = new int[ N ];
		int ret[][] = new int[ N ][ 2 ];
		st = new StringTokenizer(br.readLine( ));

		int idx = 0;
		for (int i = 0; i < N; i++)
		{
			int here = Integer.parseInt(st.nextToken( ));
			ret[ i ][ 0 ] = here;

			if (idx == 0)
			{
				arr[ idx ] = here;
				ret[ i ][ 1 ] = idx;
				idx++;
			}
			else
			{
				if (arr[ idx - 1 ] >= here)
				{
					int temp = lowerBound(arr, here, idx);
					arr[ temp ] = here;
					ret[ i ][ 1 ] = temp;
				}
				else
				{
					ret[ i ][ 1 ] = idx;
					arr[ idx++ ] = here;
				}
			}
			//show(arr);
		}
		System.out.println(idx);
		int temp[] = new int[ idx ];
		idx--;
		for (int i = N - 1; i >= 0; i--)
		{
			if (ret[ i ][ 1 ] == idx)
			{
				temp[ idx ] = ret[ i ][ 0 ];
				idx--;
			}
		}
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < temp.length; i++)
		{
			sb.append(temp[ i ] + " ");
		}
		System.out.println(sb);
	}

	static int lowerBound(int[ ] arr, int target, int len)
	{
		int begin = 0;
		int end = len - 1;
		while (begin < end)
		{
			int mid = (begin + end) >> 1;
			if (arr[ mid ] >= target) end = mid;
			else begin = mid + 1;
		}
		return end;
	}

	static void show(int[ ] temp)
	{
		StringBuilder sb = new StringBuilder( );
		int N = temp.length;
		for (int i = 0; i < N; i++)
		{
			sb.append(temp[ i ] + " ");
		}
		System.out.println(sb);
	}

}
