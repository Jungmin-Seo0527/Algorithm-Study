
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ17825_주사위_윷놀이
public class BOJ17825_주사위_윷놀이
{
	static class Node
	{
		boolean blue = false;
		boolean finish = false;;
		int row = 0;
		int col = 0;
		int sum = 0;
	}

	static int[ ] red =
	{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 }; // 21

	static int[ ][ ] blue =
	{
	                { 10, 13, 16, 19, 0, 0 },
	                { 20, 22, 24, 25, 30, 35 },
	                { 30, 28, 27, 26, 0, 0 } }; // 6 6

	static int dice[] = new int[ 10 ];

	static Node nodes[] = new Node[ 4 ];

	static int ans;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		doDFS(0);
		System.out.println(ans);
	}

	static void doDFS(int d)
	{
		if (d == 10)
		{
			int sum = 0;
			for (int i = 0; i < 4; i++)
			{
				sum += nodes[ i ].sum;
			}
			if (ans < sum) ans = sum;
			return;
		}

		for (int i = 0; i < 4; i++)
		{

			Node cur = nodes[ i ];
			if (cur.finish) continue;

			Node copy = new Node( );
			copy.blue = cur.blue;
			copy.finish = cur.finish;
			copy.row = cur.row;
			copy.col = cur.col;
			copy.sum = cur.sum;

			cur.col = cur.col + dice[ d ]; // 우선 말판위치를 주사위 만큼 더한다
			changeNode(cur); // 윷놀이판에 맞게 말판 위치 지정 
			if (check(cur, i))
			{
				doDFS(d + 1);
			}

			cur.blue = copy.blue;
			cur.finish = copy.finish;
			cur.row = copy.row;
			cur.col = copy.col;
			cur.sum = copy.sum;
		}
	}

	static void changeNode(Node cur)
	{
		if (cur.blue == false) // red
		{
			if (cur.col >= 21) // 도착지점 그 이상을 감
			{
				cur.finish = true;
				return;
			}
			else if (cur.col % 5 == 0 && cur.col < 20) // red -> blue (10, 20, 30)
			{
				cur.row = cur.col / 5 - 1;
				cur.col = 0;
				cur.blue = true;
			}
		}
		else // blue
		{
			if (cur.col > 3) // 25에서 만나서 그 이상을 감
			{
				// blue 그래프에서 row가 0, 2 인 col이 마지막 지점(3)에서 25로 내려가거나 올라간 후에 계속 간다
				// blue 그래프를 보면 이해 가능
				if (cur.row == 0 || cur.row == 2)
				{
					cur.col--;
					if (cur.row == 0) cur.row++;
					else if (cur.row == 2) cur.row--;
				}
			}

			if (cur.col == 6) // 40 (40인 지점은 red에 있다) blue -> red
			{
				cur.col = 20;
				cur.row = 0;
				cur.blue = false;
			}
			else if (cur.col > 6) // 도착지점 그 이상을 감
			{
				cur.finish = true;
			}
		}

		// 도착지점 혹은 그 이상을 간것이 아니라면 현재 지점의 숫자를 더해준다
		if (cur.finish == false)
		{
			if (cur.blue)
			{
				cur.sum += blue[ cur.row ][ cur.col ];
			}
			else
			{
				cur.sum += red[ cur.col ];
			}
		}
	}

	static boolean check(Node cur, int n)
	{
		if (cur.finish) return true;
		for (int i = 0; i < 4; i++)
		{
			if (i == n) continue; // 나 자신
			if (nodes[ i ].sum == 0) continue; // 비교대상이 아직 시작점에 있음
			if (nodes[ i ].finish) continue; // 비교 대상이 이미 도착지점에 있음
			if (cur.blue == nodes[ i ].blue && cur.row == nodes[ i ].row && cur.col == nodes[ i ].col) // 동일지점
			{
				return false;
			}
		}

		return true;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < 10; i++)
		{
			dice[ i ] = Integer.parseInt(st.nextToken( ));
		}
		for (int i = 0; i < 4; i++)
		{
			nodes[ i ] = new Node( );
		}
	}
}
