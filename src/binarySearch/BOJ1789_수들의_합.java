
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ1789_수들의_합
public class BOJ1789_수들의_합
{
	static long N;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	// 1부터 mid까지의 수의 합: sum
	// 만약에 N - sum > mid 즉 1부터 mid까지의 수의 합에서 mid 이하의 수를 빼고 mid보다 큰 수를 더해서 
	// N을 만들수 있으므로 mid길이로 N을 만들수 있다는 의미
	// 사실 그리드 알고리즘으로도 풀린다.
	// 1 부터 N의 제곱근까지의  합이 N이 되면 N의 제곱근이 답이고 아닐시 제곱근+1이 답이된다.
	static long solve( )
	{
		long start = 1;
		long end = N;
		while (start < end)
		{
			long mid = (start + end) >> 1;
			long sum = (mid * (mid + 1)) >> 1;
			if ((N - sum) <= mid) end = mid;
			else start = mid + 1;
			//System.out.println(start + " " + end + " " + mid+" "+sum);
		}
		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Long.parseLong(st.nextToken( ));
	}
}
