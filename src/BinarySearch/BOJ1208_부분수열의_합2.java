
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ1208_부분수열의_합2
// 부분수열의 합1에서는 최대길이 20인 수열에 시간제한 2초라서 단순한 DFS 문제
// 이 문제는 최대길이 40에 시간제한 1초
// 관건은 수열을 두개로 분리해서 풀어내는 방법을 생각해내는것이 가장 중요
// 수열을 반으로 나누어 각각의 부분수열들의 합을 모두 구해서 저장한다.
// 두 배열의 합이 S가 되는 경우를 이분탐색으로 구한다.
// 한 배열만으로 S가 되는 경우도 이분탐색으로 구한다.
public class BOJ1208_부분수열의_합2
{
	static int arr[];
	static int SZ = 4000000;
	static int S, N;
	static long ans;
	static ArrayList< Integer > arr1 = new ArrayList<>( );
	static ArrayList< Integer > arr2 = new ArrayList<>( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		for (int i = 0; i < N / 2; i++)
		{
			doDFS(i, N / 2, arr[ i ], 1);
		}
		for (int i = N / 2; i < N; i++)
		{
			doDFS(i, N, arr[ i ], 2);
		}
		Collections.sort(arr1);
		Collections.sort(arr2);

		ans += upper_bound(arr1, S) - lower_bound(arr1, S);
		ans += upper_bound(arr2, S) - lower_bound(arr2, S);

		for (int n : arr1)
		{
			int high = upper_bound(arr2, S - n);
			int low = lower_bound(arr2, S - n);
			ans += (high - low);
		}
		System.out.println(ans);
	}

	static void doDFS(int idx, int end, int sum, int mode)
	{
		if (idx == end) return;
		if (mode == 1) arr1.add(sum);
		else arr2.add(sum);

		for (int i = idx + 1; i < end; i++)
		{
			doDFS(i, end, sum + arr[ i ], mode);
		}
	}

	static int lower_bound(ArrayList< Integer > arr, int target)
	{
		int start = 0;
		int end = arr.size( );
		while (start < end)
		{
			int mid = (start + end) >>> 1;
			if (arr.get(mid) >= target) end = mid;
			else start = mid + 1;
		}
		return end;
	}

	static int upper_bound(ArrayList< Integer > arr, int target)
	{
		int start = 0;
		int end = arr.size( );
		while (start < end)
		{
			int mid = (start + end) >>> 1;
			if (arr.get(mid) > target) end = mid;
			else start = mid + 1;
		}
		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		S = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
