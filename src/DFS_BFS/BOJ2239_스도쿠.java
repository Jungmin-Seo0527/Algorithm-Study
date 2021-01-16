
package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ2239_스도쿠
public class BOJ2239_스도쿠
{
	static int graph[][] = new int[ 9 ][ 9 ];
	static boolean end = false;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		doDFS(0);
	}

	static void doDFS(int cur)
	{
		if (end) return;
		if (cur == 81)
		{
			end = true;
			show( );
			return;
		}
		int cur_row = cur / 9;
		int cur_col = cur % 9;
		if (graph[ cur_row ][ cur_col ] != 0) doDFS(cur + 1);
		else
		{
			for (int i = 1; i <= 9; i++)
			{
				if (check(cur_row, cur_col, i))
				{
					graph[ cur_row ][ cur_col ] = i;
					doDFS(cur + 1);
					graph[ cur_row ][ cur_col ] = 0;
				}
			}
		}

	}

	static boolean check(int r, int c, int n)
	{
		for (int i = 0; i < 9; i++)
		{
			if (graph[ i ][ c ] == n && i != r) return false;
			if (graph[ r ][ i ] == n && i != c) return false;
		}

		int start_row = r / 3 * 3;
		int start_col = c / 3 * 3;
		for (int i = start_row; i < start_row + 3; i++)
		{
			for (int j = start_col; j < start_col + 3; j++)
			{
				if (i != r && j != c && graph[ i ][ j ] == n) return false;
			}
		}
		return true;
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				sb.append(graph[ i ][ j ]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++)
		{
			String input = br.readLine( );
			for (int j = 0; j < 9; j++)
			{
				graph[ i ][ j ] = input.charAt(j) - '0';
			}
		}
	}
}
