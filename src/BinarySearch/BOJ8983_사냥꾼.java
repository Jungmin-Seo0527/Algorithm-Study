
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ8983_사냥꾼
// Bineary Search
// 사대를 오름차순으로 정렬
// 각 동물의 좌표에서 가능한 사대를 이분탐색으로 탐색
// 이분탐색에서 동물과 사대의 거리가 L 이하이면 가능
// 아닐경우 start 혹은 end 좌표를 갱신해주어야 하는데
// 이 과정까지 온 경우라면 현재 동물의 좌표와 현재 비교대상이된 사대의 거리가 L보다 큰 경우이다
// 만약 현재 동물이 비교대상의 사대보다 오른쪽에 있는 경우에는 비교대상의 사대를 mid 기준 오른쪽으로 갱신
// else  현재 동물이 비교대상의 사대보다 왼쪽에 있는 경우는 비교대상의 사대를 mid 기준 왼쪽으로 갱신

public class BOJ8983_사냥꾼
{
	static class Point
	{
		int x, y;

		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	static int M, N, L, arr[];
	static Point points[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		long ans = 0;
		for (int i = 0; i < N; i++)
		{
			if (binearySearch(points[ i ].x, points[ i ].y) != -1) ans++;
		}
		System.out.println(ans);
	}

	static int binearySearch(int x, int y)
	{
		int start = 0;
		int end = M - 1;
		while (start <= end)
		{
			int mid = (start + end) >>> 1;
			int dist = Math.abs(arr[ mid ] - x) + y;
			//System.out.println(x + " " + y + " " + dist + " " + arr[ mid ]);
			if (dist <= L)
			{
				//System.out.println(x + " " + y + " " + arr[ mid ]);
				return mid;
			}
			else
			{
				if (arr[ mid ] - x < 0) start = mid + 1;
				else end = mid - 1;
			}
		}
		return -1;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		M = Integer.parseInt(st.nextToken( ));
		N = Integer.parseInt(st.nextToken( ));
		L = Integer.parseInt(st.nextToken( ));
		arr = new int[ M ];

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < M; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
		Arrays.sort(arr);

		points = new Point[ N ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int x = Integer.parseInt(st.nextToken( ));
			int y = Integer.parseInt(st.nextToken( ));
			points[ i ] = new Point(x, y);
		}

	}
}
