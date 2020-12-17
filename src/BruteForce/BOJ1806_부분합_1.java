
package BruteForce;

import java.io.*;
import java.util.*;

// BOJ1806_부분합
public class BOJ1806_부분합_1
{
	static int N, S, start, end;
	static int[ ] graph;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		S = Integer.parseInt(st.nextToken( ));
		graph = new int[ N ];

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			graph[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}

	// start+end = sum
	// sum이 목표치보다 크면 길이 확인 후 end++ (길이 증가로 sum이 증가)
	// sum이 목표치보다 작으면 start++ (길이 감소로 sum이 감소)
	// 가장 짧은 길이 반환(목표치 보다 큰적이 없으면 Integer.MAX_VALUE이므로 0으로 바꿈
	static int solve( )
	{
		int ret = Integer.MAX_VALUE;
		int sum = graph[ 0 ];
		while (start <= end)
		{
			//System.out.println(start+" "+end+" "+sum);
			if (sum >= S)
			{
				if (ret > end - start + 1)
				{
					ret = end - start + 1;
				}
				sum -= graph[ start++ ];

			}
			else if (end == N - 1) break;
			else
			{
				sum += graph[ ++end ];
			}
		}
		if (ret == Integer.MAX_VALUE) ret = 0;
		return ret;
	}
}
