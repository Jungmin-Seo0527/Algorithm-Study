
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ2098_외판원_순회
public class BOJ2098_외판원_순회
{
	static int N, w[][], dp[][], mask;
	static int max = 1000000;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		int ret = doDFS(0, 0);
		if (ret == max) ret = -1;
		System.out.println(ret);
	}

	static int doDFS(int cur, int bit)
	{
		// cur 지점으로 왔을때 bit값 갱신
		bit = bit | (1 << cur);

		// bit값을 보니 이미 모든 지점을 지나옴
		if (bit == mask)
		{
			if (w[ cur ][ 0 ] > 0) return w[ cur ][ 0 ]; // 다시 0으로 돌아갈 수 있음(순회 성공)
			else return max;
		}

		// dp값이 이미 존재 -> 반환
		int ret = dp[ cur ][ bit ];
		if (ret > 0) return ret;

		ret = max;
		for (int i = 0; i < N; i++)
		{
			if (i == cur) continue; // 자기 자신에게 가는 경우
			if (w[ cur ][ i ] == 0) continue; // 갈수 없는 경우
			if ((bit & (1 << i)) != 0) continue; // 가려는 지점을 이미 지나옴
			ret = Math.min(ret, doDFS(i, bit) + w[ cur ][ i ]);
		}
		return dp[ cur ][ bit ] = ret;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		w = new int[ N ][ N ];
		mask = (1 << N) - 1;
		dp = new int[ N ][ mask ];
		max = max * N;
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < N; j++)
			{
				w[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
	}
}
