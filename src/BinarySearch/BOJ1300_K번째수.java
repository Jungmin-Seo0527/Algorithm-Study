
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ1300_K번째수
public class BOJ1300_K번째수
{
	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		int k = Integer.parseInt(st.nextToken( ));

		// 숫자를 하나 정한다. mid
		// 이차원 배열에서 각 행에서 mid보다 작거나 같은 수들의 갯수들을 모두 더한다.
		// 그 값이 mid가 일차원 배열 B에서 위치할 수 있는 인덱스이다
		// 이진탐색으로 그 인덱스가 K일때의 mid를 찾아낸다
		int start = 1;
		// 각 인덱스에 해당하는 숫자는 인덱스보다 크거나 같다 따라서 K부터 시작해도 무방 (혹은 10^9)
		int end = k;

		while (start < end)
		{
			int mid = (start + end) >> 1;
			int idx = 0;
			for (int i = 1; i <= N; i++)
			{
				idx += (Math.min(mid / i, N));
			}
			if (idx >= k) end = mid;
			else start = mid + 1;
		}
		System.out.println(end);
	}
}
