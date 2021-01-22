
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ3020_개똥벌레
// binary search
public class BOJ3020_개똥벌레
{
	static int N, H;
	static int buttom[], up[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// 각각 종류석과 석순을 오름차순으로 정렬후에
	// 각각의 높이마다 깨야되는 돌들의 합을 구해서 최소값과 최소값들이 갯수를 구한다.
	// 깨야되는 돌들을 구하는 방법은 lower_bound를 이용해 구한다.
	// 각 돌들이 오름차순으로 정렬이 되어있을때 현재 내 높이가 돌들의 배열에서
	// 들어갈수 있는 인덱스중 최소값을 구한다.
	// 그 인덱스 포함 인덱스 보다 큰 인덱스들의 갯수가 돌을 부셔야 하는 갯수이다.
	// 위에 달려있는 돌은 H+1-현재 높이 로 구한다.
	// 즉 위에 달린 돌에서의 높이는 1 -> 5, 2 -> 4, 3 -> 3, 2 -> 2, 1 -> 5 로 변환해서 풀어내는 것이다.

	static void solve( )
	{
		int ans = Integer.MAX_VALUE;
		int cnt = 0;
		for (int i = 1; i <= H; i++)
		{
			int temp = N / 2 - lower_bound(buttom, i);
			temp += (N / 2 - lower_bound(up, H + 1 - i));
			if (ans > temp)
			{
				ans = temp;
				cnt = 1;
			}
			else if (ans == temp)
			{
				cnt++;
			}
		}
		System.out.println(ans + " " + cnt);
	}

	// arr 에서 target이 삽입될 수 있는 가장 작은 인덱스 반환
	static int lower_bound(int arr[], int target)
	{
		int start = 0;
		int end = arr.length;
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
		N = Integer.parseInt(st.nextToken( ));
		H = Integer.parseInt(st.nextToken( ));
		buttom = new int[ N / 2 ];
		up = new int[ N / 2 ];

		for (int i = 0; i < N / 2; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			buttom[ i ] = Integer.parseInt(st.nextToken( ));
			st = new StringTokenizer(br.readLine( ));
			up[ i ] = Integer.parseInt(st.nextToken( ));
		}
		Arrays.sort(buttom);
		Arrays.sort(up);
	}
}
