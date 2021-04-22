
package dp;

import java.io.*;
import java.util.*;

// BOJ12852_1로_만들기2
public class BOJ12852_1로_만들기2
{

	private static int[ ] dp;
	static int root[];
	private static int num;

	public static void main(String[ ] args) throws IOException
	{
		//System.out.println ("st");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		num = Integer.parseInt(st.nextToken( ));

		dp = new int[ num + 1 ];
		root = new int[ num + 1 ];

		for (int i = 2; i <= num; i++)
		{
			dp[ i ] = findMin(i) + 1;
		}
		System.out.println(dp[ num ]);
		System.out.print(num + " ");
		while (true)
		{
			int temp = root[ num ];
			if (temp == 0) break;
			System.out.print(temp + " ");
			num = temp;
		}

	} //// end main

	private static int findMin(int _num)
	{
		int ret;
		int ret_idx = 0;
		int num1 = Integer.MAX_VALUE;
		int num2 = Integer.MAX_VALUE;
		int num3 = Integer.MAX_VALUE;
		if (_num % 3 == 0) num1 = dp[ _num / 3 ];
		if (_num % 2 == 0) num2 = dp[ _num / 2 ];
		num3 = dp[ _num - 1 ];

		if (num1 < num2)
		{
			ret = num1;
			ret_idx = _num / 3;
		}
		else
		{
			ret = num2;
			ret_idx = _num / 2;
		}
		if (ret > num3)
		{
			ret = num3;
			ret_idx = _num - 1;
		}
		// 3가지중 최단 거리의 인덱스를 저장
		root[ _num ] = ret_idx;
		return ret;
	}

} //// end class