
package samsung;

import java.io.*;
import java.util.*;

// BOJ17873_새로운_게임2
// 조건을 제대로 파악하자
// 파란색에는 말판이 존재할수 없다
public class BOJ17873_새로운_게임2
{
	static class Node
	{
		int row, col, dir, num, top = 0;

		public Node(int row, int col, int dir, int num)
		{
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.num = num;
		}

	}

	static int N, K;

	// 오 왼 위 아래
	static int v_r[] =
	{ 0, 0, -1, 1 };
	static int v_c[] =
	{ 1, -1, 0, 0 };
	static int chess[][];
	static Node nodes[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		for (int i = 0; i < 1000; i++)
		{
			//System.out.println("turn" + i);
			for (int j = 1; j <= K; j++)
			{
				moveNode(nodes[ j ]);
				//show( );
				//show2( );
				//System.out.println( );
				if (check( ))
				{
					System.out.println(i + 1);
					return;
				}
			}
		}
		System.out.println("-1");
	}

	static void moveNode(Node cur)
	{
		int next_row = cur.row + v_r[ cur.dir ];
		int next_col = cur.col + v_c[ cur.dir ];
		//System.out.println(next_row + " " + next_col);
		if (next_row <= 0 || next_row > N || next_col <= 0 || next_col > N) // 경계밖
		{
			blue(cur);
		}
		else if (chess[ next_row ][ next_col ] == 2)
		{
			blue(cur);
		}
		else if (chess[ next_row ][ next_col ] == 0)
		{
			white(cur);
		}
		else if (chess[ next_row ][ next_col ] == 1)
		{
			red(cur);
		}
	}

	static void red(Node cur)
	{
		int next_row = cur.row + v_r[ cur.dir ];
		int next_col = cur.col + v_c[ cur.dir ];
		for (int i = 1; i <= K; i++) // cur 밑에 있던 말과 연결 고리를 끊는다
		{
			if (nodes[ i ].top == cur.num) nodes[ i ].top = 0;
		}

		Node node = reverse(cur);
		//System.out.println(node.num+" "+node.top);

		// 가려는 칸에 이미 존재하는 말 위에 cur를 쌓아준다
		for (int i = 1; i <= K; i++)
		{
			if (nodes[ i ].row == next_row && nodes[ i ].col == next_col && nodes[ i ].top == 0)
			{
				nodes[ i ].top = node.num;
			}
		}

		// cur 말부터 그 위에 있는 말 전부 next_row, next_col로 옮겨준다
		while (true)
		{
			//System.out.println(node.num);
			node.row = next_row;
			node.col = next_col;
			if (node.top == 0) break;
			else node = nodes[ node.top ];
		}
	}

	// 쌓여져 있는 말들의 순서를 바꾸고 가장 아래에 위치하는 말을 반환한다
	static Node reverse(Node cur)
	{
		if (cur.top == 0) return cur;
		int temp[] = new int[ K ];
		int i = 0;

		// 현재 위치에 쌓여있는 말들을
		for (i = 0; i < K; i++)
		{
			temp[ i ] = cur.num;
			//System.out.println(temp[i]+" ");
			cur = nodes[ cur.top ];
			if (cur.num == 0) break;
		}
		for (int j = i; j > 0; j--)
		{
			nodes[ temp[ j ] ].top = temp[ j - 1 ];
		}
		nodes[ temp[ 0 ] ].top = 0;

		return nodes[ temp[ i ] ];
	}

	static void white(Node cur)
	{
		int next_row = cur.row + v_r[ cur.dir ];
		int next_col = cur.col + v_c[ cur.dir ];
		for (int i = 1; i <= K; i++) // cur 밑에 있던 말과 연결 고리를 끊는다
		{
			if (nodes[ i ].top == cur.num) nodes[ i ].top = 0;
		}

		// 가려는 칸에 이미 존재하는 말 위에 cur를 쌓아준다
		for (int i = 1; i <= K; i++)
		{
			//System.out.println(nodes[ i ].row + " " + nodes[ i ].col);
			if (nodes[ i ].row == next_row && nodes[ i ].col == next_col && nodes[ i ].top == 0)
			{
				nodes[ i ].top = cur.num;
				break;
			}
		}

		// cur 말부터 그 위에 있는 말 전부 next_row, next_col로 옮겨준다
		Node node = cur;
		//System.out.println(next_row+" "+next_col);
		while (true)
		{
			node.row = next_row;
			node.col = next_col;
			//System.out.println(node.num);
			if (node.top == 0) break;
			else node = nodes[ node.top ];
		}

	}

	static void blue(Node cur)
	{
		// 방향 전환
		if (cur.dir == 0) cur.dir = 1;
		else if (cur.dir == 1) cur.dir = 0;
		else if (cur.dir == 2) cur.dir = 3;
		else if (cur.dir == 3) cur.dir = 2;

		int next_row = cur.row + v_r[ cur.dir ];
		int next_col = cur.col + v_c[ cur.dir ];
		// 가려는 칸이 다시 파란색 혹은 경계를 벗아난 경우에는 이동하지 않고 가만히 있는다.
		if (next_row <= 0 || next_row > N || next_col <= 0 || next_col > N) return;
		if (chess[ next_row ][ next_col ] == 2) return;
		else // 가려는 칸이 파란색이 아니므로 이동
		{
			if (chess[ next_row ][ next_col ] == 0) white(cur); // 가려는 곳이 흰색
			else if (chess[ next_row ][ next_col ] == 1) red(cur); // 가려는 곳이 빨강색
		}

	}

	static boolean check( )
	{
		for (int i = 1; i <= K; i++)
		{
			Node cur = nodes[ i ];
			int cnt = 1;
			while (true)
			{
				cur = nodes[ cur.top ];
				if (cur.num == 0) break;
				cnt++;
			}
			if (cnt >= 4) return true;
		}
		return false;
	}

	static void show( )
	{
		for (int i = 1; i <= K; i++)
		{
			System.out.println(i + ":  (" + nodes[ i ].row + ", " + nodes[ i ].col + ")  dir: "
			                + (nodes[ i ].dir + 1) + " top: " + nodes[ i ].top);
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		chess = new int[ N + 1 ][ N + 1 ];
		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 1; j <= N; j++)
			{
				chess[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
		nodes = new Node[ K + 1 ];
		nodes[ 0 ] = new Node(-1, -1, -1, 0);
		for (int i = 1; i <= K; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int r = Integer.parseInt(st.nextToken( ));
			int c = Integer.parseInt(st.nextToken( ));
			int d = Integer.parseInt(st.nextToken( )) - 1;
			nodes[ i ] = new Node(r, c, d, i);
		}
	}
}
