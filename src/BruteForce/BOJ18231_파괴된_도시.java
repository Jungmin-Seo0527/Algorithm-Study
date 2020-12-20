
package BruteForce;

import java.io.*;
import java.util.*;

// BOJ18231_파괴된_도시
public class BOJ18231_파괴된_도시
{
	static int N, M, K;
	static boolean[ ][ ] graph;
	static boolean destroy[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		graph = new boolean[ N + 1 ][ N + 1 ];
		destroy = new boolean[ N + 1 ];
		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int c1 = Integer.parseInt(st.nextToken( ));
			int c2 = Integer.parseInt(st.nextToken( ));
			graph[ c1 ][ c2 ] = true;
			graph[ c2 ][ c1 ] = true;
		}
		st = new StringTokenizer(br.readLine( ));
		K = Integer.parseInt(st.nextToken( ));

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < K; i++)
		{
			int city = Integer.parseInt(st.nextToken( ));
			destroy[ city ] = true;
		}
	}

	// graph 의 row =0 -> 현재 도시 상태(파괴 유무)
	static void solve( )
	{
		int cnt = 0; // 폭탄 투하 갯수
		StringBuilder sb = new StringBuilder( );
		for (int i = 1; i <= N; i++)
		{
			if (check(i)) // i 도시에 폭탄 투하 가능 한가?
			{
				cnt++;
				graph[ 0 ][ i ] = true;
				sb.append(i + " ");
			}
		}

		for (int i = 1; i <= N; i++)
		{
			if (graph[ 0 ][ i ] != destroy[ i ]) // 현재 도시들 상태와 폭파 도시 비교
			{
				System.out.println("-1");
				return;
			}

		}
		System.out.println(cnt);
		System.out.println(sb);
	}

	// c 도시에 폭탄 투하시 연결되어 있는 도시중에 파괴되면 안되는 도시가 있는지 판별
	// c 도시에 폭탄 투하 가능하면 graph의 row=0 즉 해당 도시의 상태를 폭파로 변경
	static boolean check(int c)
	{
		if (destroy[ c ] == false) return false;
		for (int i = 1; i <= N; i++)
		{
			if (i == c) continue;
			if (graph[ i ][ c ] == true && destroy[ i ] == false) return false;
		}

		// 투하 가능, 도시 상태 변경(폭파)
		for (int i = 1; i <= N; i++)
		{
			if (graph[ i ][ c ] == true && destroy[ i ] == true)
			{
				graph[ 0 ][ i ] = true;
			}
		}
		return true;
	}
}
