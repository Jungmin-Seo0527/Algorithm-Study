
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ1939_중량제한
// Bineary Search
public class BOJ1939_중량제한
{
	static class Pair
	{
		int city, weight;

		public Pair(int city, int weight)
		{
			this.city = city;
			this.weight = weight;
		}
	}

	static int N, M, fac1, fac2, max;
	static boolean possible, visited[];
	static LinkedList< Pair > graph[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static int solve( )
	{
		int start = 1;
		int end = max;
		while (start <= end)
		{
			int mid = (start + end) >> 1;
			if (doBFS(mid)) start = mid + 1;
			else end = mid - 1;
		}
		return end;
	}

	static boolean doBFS(int weight)
	{
		visited = new boolean[ N + 1 ];
		visited[ fac1 ] = true;
		Queue< Integer > que = new LinkedList<>( );
		que.add(fac1);
		while (que.isEmpty( ) == false)
		{
			int cur = que.poll( );
			if (cur == fac2) return true;
			for (Pair p : graph[ cur ])
			{
				if (visited[ p.city ] == false && p.weight >= weight)
				{
					visited[ p.city ] = true;
					que.add(p.city);
				}
			}
		}
		return false;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		graph = new LinkedList[ N + 1 ];
		for (int i = 0; i <= N; i++)
		{
			graph[ i ] = new LinkedList<>( );
		}

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int A = Integer.parseInt(st.nextToken( ));
			int B = Integer.parseInt(st.nextToken( ));
			int C = Integer.parseInt(st.nextToken( ));
			graph[ A ].add(new Pair(B, C));
			graph[ B ].add(new Pair(A, C));
			if (max < C) max = C;
		}
		st = new StringTokenizer(br.readLine( ));
		fac1 = Integer.parseInt(st.nextToken( ));
		fac2 = Integer.parseInt(st.nextToken( ));
	}
}
