
package TwoPointer;

import java.io.*;
import java.util.*;

// BOJ14921_용액_합성하기
public class BOJ14921_용액_합성하기
{
	static int N, arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static int solve( )
	{
		int start = 0;
		int end = N - 1;
		int sum = 0;
		int ret = Integer.MAX_VALUE;
		while (start < end)
		{
			sum = arr[ start ] + arr[ end ];
			if (Math.abs(sum) < Math.abs(ret))
			{
				ret = sum;
			}
			if (sum < 0) start++;
			else if (sum > 0) end--;
			else
			{
				return 0;
			}
		}
		return ret;
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
