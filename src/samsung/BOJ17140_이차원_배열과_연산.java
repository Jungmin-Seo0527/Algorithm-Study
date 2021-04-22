
package samsung;

import java.io.*;
import java.util.*;

// BOJ17140_이차원_배열과_연산
public class BOJ17140_이차원_배열과_연산
{
	static int graph[][];
	static int r, c, k, rowSZ, colSZ;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		int ans = -1;
		for (int i = 0; i < 101; i++)
		{
			if (graph[ r ][ c ] == k)
			{
				ans = i;
				break;
			}
			if (rowSZ >= colSZ) rowOp( );
			else colOp( );
		}
		System.out.println(ans);
	}

	static void rowOp( )
	{
		int rs = rowSZ;
		for (int i = 1; i <= rs; i++)
		{
			int cnt[] = new int[ 101 ];

			// 각 숫자를 센다
			for (int j = 1; j <= colSZ; j++)
			{
				cnt[ graph[ i ][ j ] ]++;
			}

			LinkedList< Integer > list[] = new LinkedList[ 101 ];
			for (int j = 1; j < 101; j++)
			{
				list[ j ] = new LinkedList<>( );
			}

			// 각 숫자의 횟수에 해당하는 숫자들을 크기순으로 저장
			for (int j = 1; j < 101; j++)
			{
				if (cnt[ j ] == 0) continue; // 0개인것은 고려 안함
				list[ cnt[ j ] ].add(j); // list[숫자의 갯수].add(해당 숫자) 크기순으로 탐색하므로 당연히 크기순으로 저장
			}

			int idx = 1; // 그래프를 채우기 위한 인덱스
			Arrays.fill(graph[ i ], 0); // 미리 해당 열을 0으로 memset
			for (int j = 1; j < 101; j++)
			{
				if (list[ j ].size( ) == 0) continue; // size==0 : 해당 갯수의 숫자는 존재하지 않는다
				for (int e : list[ j ])
				{
					//System.out.print(e + " " + j + "// ");
					graph[ i ][ idx ] = e;
					graph[ i ][ idx + 1 ] = j;
					colSZ = Math.max(colSZ, idx + 1); // colSZ 갱신 (추가되는 인덱스중 가능 큰 인덱스가 됨)
					idx = idx + 2;
					if (idx > 100) break; // 100 초과 인덱스부터는 그냥 버림
				}
				if (idx > 100) break;
			}
		}
	}

	// rowOp와 행 열만 바꿔주고 동일한 과정 수행
	static void colOp( )
	{
		int cz = colSZ;
		for (int i = 1; i <= cz; i++)
		{
			int cnt[] = new int[ 101 ];
			for (int j = 1; j <= rowSZ; j++)
			{
				cnt[ graph[ j ][ i ] ]++;
			}
			LinkedList< Integer > list[] = new LinkedList[ 101 ];
			for (int j = 1; j < 101; j++)
			{
				list[ j ] = new LinkedList<>( );
			}

			for (int j = 1; j < 101; j++)
			{
				if (cnt[ j ] == 0) continue;
				list[ cnt[ j ] ].add(j);
			}

			for (int j = 0; j < 101; j++)
			{
				graph[ j ][ i ] = 0;
			}

			int idx = 1;
			for (int j = 1; j < 101; j++)
			{
				if (list[ j ].size( ) == 0) continue;
				for (int e : list[ j ])
				{
					graph[ idx ][ i ] = e;
					graph[ idx + 1 ][ i ] = j;
					rowSZ = Math.max(rowSZ, idx + 1);
					idx = idx + 2;
					if (idx > 100) break;
				}
				if (idx > 100) break;
			}
		}
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 1; i <= rowSZ; i++)
		{
			for (int j = 1; j <= colSZ; j++)
			{
				sb.append(graph[ i ][ j ] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		r = Integer.parseInt(st.nextToken( ));
		c = Integer.parseInt(st.nextToken( ));
		k = Integer.parseInt(st.nextToken( ));
		rowSZ = 3;
		colSZ = 3;
		graph = new int[ 101 ][ 101 ];
		for (int i = 1; i <= 3; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 1; j <= 3; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
	}
}
