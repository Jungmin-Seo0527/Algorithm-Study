
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
		System.out.println(solve( ));
	}

	static int solve( )
	{
		int start = 1;
		while (start <= end)
		{
			int mid = (start + end) >> 1;
			int cnt = 1;
			int temp = arr[ 0 ];
			for (int i = 1; i < N; i++)
			{
				// 오름차순으로 집들의 좌표를 정렬하고
				// 가장 인접한 두 공유기 사이의 거리의 최대값을 구할때
				// 무조건 첫번째 집부터 시작할수 밖에 없다.
				// 두번째 집부터 시작하는것 보다 무조건 첫번째 시작이
				// 다음 공유기와의 간격이 크거나 같다
				int d = arr[ i ] - temp;
				if (d >= mid)
				{
					cnt++;
					temp = arr[ i ];
				}
			}
			//System.out.println(start + " " + end + " " + mid + " " + cnt);
			if (cnt >= C) start = mid + 1;
			else end = mid - 1;
		}
		return end;
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
