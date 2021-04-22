
package twoPointer;

import java.io.*;
import java.util.*;

// BOJ2467_용액
// two pointer
public class BOJ2467_용액
{
	static int N;
	static int arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		int start = 0;
		int end = N - 1;
		int ret_idx1 = start;
		int ret_idx2 = end;
		int sum = 0;
		int ret_sum = Integer.MAX_VALUE;
		while (start < end)
		{
			sum = arr[ start ] + arr[ end ];
			if (Math.abs(sum) < Math.abs(ret_sum))
			{
				ret_sum = sum;
				ret_idx1 = start;
				ret_idx2 = end;
			}
			if (sum < 0)
			{
				start++;
			}
			else if (sum > 0)
			{
				end--;
			}
			else
			{
				ret_idx1 = start;
				ret_idx2 = end;
				break;
			}
		}
		System.out.println(arr[ ret_idx1 ] + " " + arr[ ret_idx2 ]);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
