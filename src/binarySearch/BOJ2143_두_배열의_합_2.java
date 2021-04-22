
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ2143_두_배열의_합_2
// Hashmap
// 모든 부 배열들을 구한후
// 1차원 배열에 저장후 오름차순으로 정렬하는 과정대신 hashmap에 저장하여 탐색
// 시간 절약, 대신 메모리는 더 많이 소요
public class BOJ2143_두_배열의_합_2
{
	static int T, n, m, arr1[], arr2[];
	static long ans;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		HashMap< Integer, Integer > map = new HashMap<>( );
		for (int i = 0; i < n; i++)
		{
			int sum = 0;
			for (int j = i; j < n; j++)
			{
				sum += arr1[ j ];
				map.put(sum, map.containsKey(sum) ? map.get(sum) + 1 : 1);
			}
		}

		for (int i = 0; i < m; i++)
		{
			int sum = 0;
			for (int j = i; j < m; j++)
			{
				sum += arr2[ j ];
				if (map.containsKey(T - sum)) ans += map.get(T - sum);
			}
		}
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		T = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		n = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		arr1 = new int[ n ];
		for (int i = 0; i < n; i++)
		{
			arr1[ i ] = Integer.parseInt(st.nextToken( ));
		}
		st = new StringTokenizer(br.readLine( ));
		m = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		arr2 = new int[ m ];
		for (int i = 0; i < m; i++)
		{
			arr2[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
