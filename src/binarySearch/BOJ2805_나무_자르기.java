
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ2805_나무_자르기
public class BOJ2805_나무_자르기
{
	static int N, M, arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	// 나무를 정렬하고
	// 가장 큰 나무를 시작으로
	// 절단기 높이를 -1씩 해주고
	// 절단기 높이보다 큰 나무의 갯수만큼 + 하면서 절단기 높이를 하나씩 줄이는 방식
	static int solve( )
	{
		int ret = arr[ N - 1 ];
		int end = N - 1;
		int sum = 0;
		while (true)
		{
			ret--;
			if (ret < 0) break;
			if (end > 0)
			{
				while (end > 0)
				{
					if (arr[ end - 1 ] > ret) end--;
					else break;
				}
			}
			sum += (N - end); // 절단기 높이보다 큰 나무의 갯수만큼 더한다
			//System.out.println(sum);
			if (sum >= M) return ret;
		}

		return -1;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
		Arrays.sort(arr);
	}
}
