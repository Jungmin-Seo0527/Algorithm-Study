
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ2512_예산
public class BOJ2512_예산
{
	static int N, arr[], M, arr_sum, max;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	// 정해진 총액 이하에서 가능한 한 최대의 총 예산
	static int solve( )
	{
		// 단 M으로 이미 모든 총액을 충당할 수 있는 경우 존재
		// 그때도 할당하는 예산중 가장 최대값( 즉 arr 에서 최대값) 을 반환해주면 된다. 
		// 이 부분을 놓치기 쉬움
		if (arr_sum <= M) return max;
		int left = 1;
		int right = M;
		while (left <= right)
		{
			int mid = (left + right) >> 1;
			int sum = 0;
			for (int i = 0; i < N; i++)
			{
				if (arr[ i ] <= mid)
				{
					sum += arr[ i ];
				}
				else sum += mid;
			}
			if (sum <= M) left = mid + 1;
			else right = mid - 1;
			//System.out.println(left + " " + right + " " + sum);
		}
		return right;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
			arr_sum += arr[ i ];
			if (max < arr[ i ]) max = arr[ i ];
		}
		st = new StringTokenizer(br.readLine( ));
		M = Integer.parseInt(st.nextToken( ));
	}
}
