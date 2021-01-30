
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ3649_로봇_프로젝트
// Bineary Search
// 두개의 레고로 두멍을 막아야 하는 문제
// 주어진 레고들중 하나를 선택, 그것보다 크면서 더할때 구멍의 크기와 맞는 레고를 이분탐색으로 찾는다
// 답이 여러개가 있을수 있으므로 가능한 모든 경우를 찾아서 그 차이가 가장 큰 경우를 출력

// 테스트케이스의 갯수가 주어지지 않은 문제
// 자바 버전으로 인해 프로그램이 종료가 되지 않는듯 하다(맞은 사람들의 코드를 돌려봐도 마찬가지)

public class BOJ3649_로봇_프로젝트
{
	static int x, n, arr[];
	static final int mod = (int) 1e7;

	public static void main(String[ ] args) throws IOException
	{
		//inputAndSettingData( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (br.ready( ))
		{
			x = Integer.parseInt(br.readLine( ));
			n = Integer.parseInt(br.readLine( ));
			arr = new int[ n ];
			for (int i = 0; i < n; i++)
			{
				arr[ i ] = Integer.parseInt(br.readLine( ));
			}
			Arrays.sort(arr);
			solve( );
		}
	}

	static void solve( )
	{
		int ret_l1 = -1;
		int ret_l2 = -1;
		int ret = -1;

		for (int i = 0; i < n; i++)
		{
			int l1 = arr[ i ];
			int l2_idx = bineary_search(arr, i + 1, n - 1, x * mod - l1);
			if (l2_idx == -1) continue;
			int l2 = arr[ l2_idx ];
			if (l2 >= 0 && Math.abs(l1 - l2) > ret)
			{
				ret = Math.abs(l1 - l2);
				ret_l1 = l1;
				ret_l2 = l2;
			}
		}
		if (ret == -1) System.out.println("danger ");
		else System.out.println("yes " + ret_l1 + " " + ret_l2);
	}

	static int bineary_search(int arr[], int start, int end, int target)
	{
		while (start <= end)
		{
			int mid = (start + end) >>> 1;
			if (arr[ mid ] == target)
			{
				return mid;
			}
			else if (arr[ mid ] > target) end = mid - 1;
			else start = mid + 1;
		}
		return -1;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
	}
}
