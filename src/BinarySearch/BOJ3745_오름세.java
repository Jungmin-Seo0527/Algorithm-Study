
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ3745_오름세
// Bineary Search
// 테스크 케이스가 여러개 있는데 그 갯수는 주어지지 않음
// 지금 코드 자체는 종료가 안됨(이유를 모르겠음 while문을 빠져나오지를 못함)
// 온라인 저지에서는 답 출력은 되니깐 통과되는것 같은데 뭐가 문제인지는 모르겠음

// LIS 문제
public class BOJ3745_오름세
{
	static int N, arr[];

	public static void main(String[ ] args) throws IOException
	{
		//inputAndSettingData( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true)
		{
			String line = br.readLine( );
			if (line == null) return;
			if (line.equals("") || line.length( ) == 0) break;

			N = Integer.parseInt(line.trim( ));
			arr = new int[ N ];

			st = new StringTokenizer(br.readLine( ));
			int idx = 0;
			for (int i = 0; i < N; i++)
			{
				int input = Integer.parseInt(st.nextToken( ));
				if (i == 0 || arr[ idx - 1 ] < input) arr[ idx++ ] = input;
				else
				{
					int temp = lower_bound(arr, input, idx);
					arr[ temp ] = input;
				}
			}
			System.out.println(idx);
		}
	}

	static int lower_bound(int arr[], int target, int len)
	{
		int start = 0;
		int end = len;
		while (start < end)
		{
			int mid = (start + end) >>> 1;
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
