
package TwoPointer;

import java.io.*;
import java.util.*;

// BOJ2473_세_용액
public class BOJ2473_세_용액
{
	static int N, ret_idx0, ret_idx1, ret_idx2;
	static long arr[];
	static long ret = Long.MAX_VALUE;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		int i = 0;
		for (i = 0; i < N - 2; i++)
		{
			solve(i);
			if (ret == 0) break;
		}
		System.out.println(arr[ ret_idx0 ] + " " + arr[ ret_idx1 ] + " " + arr[ ret_idx2 ]);
	}

	// 한용액은 고정시키고
	// 나머지 두 용액을 two pointer
	static void solve(int n)
	{
		long sum = arr[ n ];
		int start = n + 1;
		int end = N - 1;

		while (start < end)
		{
			sum = arr[ n ] + arr[ start ] + arr[ end ];
			//System.out.println(sum);
			if (Math.abs(sum) < Math.abs(ret))
			{
				ret = sum;
				ret_idx0 = n;
				ret_idx1 = start;
				ret_idx2 = end;
			}

			if (sum < 0) start++;
			else if (sum > 0) end--;
			else
			{
				ret = 0;
				ret_idx0 = n;
				ret_idx1 = start;
				ret_idx2 = end;
				return;
			}
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		st = new StringTokenizer(br.readLine( ));
		arr = new long[ N ];
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
		Arrays.sort(arr);
	}
}
