package Samgsung;

import java.io.*;
import java.util.*;

// BOJ16236_�Ʊ���
public class BOJ16236_�Ʊ���
{
	private static class Point implements Comparable< Point >
	{
		int row, col, dist;

		Point(int _r, int _c)
		{
			this.row = _r;
			this.col = _c;
		}

		@Override
		// priority queue �̿�
		// �켱���� ���� : 1. �Ÿ� 2. row  3. col
		public int compareTo(Point o)
		{
			// TODO Auto-generated method stub
			if (this.dist < o.dist)
			{
				return -1;
			}
			else if (this.dist == o.dist)
			{
				if (this.row < o.row)
				{
					return -1;
				}
				else if (this.row == o.row)
				{
					if (this.col < o.col)
					{
						return -1;
					}
					else if (this.col == o.col)
					{
						return 0;
					}
				}
			}
			return 1;
		}
	}

	static int N, cur_size, num, ans;
	static int[ ][ ] graph;

	static Point start;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		while (true)
		{
			if (doBFS( ) == false)
			{
				break;
			}
		}
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		graph = new int[ N ][ N ];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < N; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
				if (graph[ i ][ j ] == 9)
				{
					start = new Point(i, j);
					graph[ i ][ j ] = 0;
				}
			}
		}
		cur_size = 2;
	}

	// ���� ã��
	static boolean doBFS( )
	{
		PriorityQueue< Point > pq = new PriorityQueue<>( );
		boolean[ ][ ] visited = new boolean[ N ][ N ];
		visited[ start.row ][ start.col ] = true;
		pq.offer(start);
		int[ ] v_r =
		{ 0, 0, 1, -1 };
		int[ ] v_c =
		{ -1, 1, 0, 0 };

		while (pq.isEmpty( ) == false)
		{
			Point cur = pq.poll( );

			// ������ �ִ� ���� ����
			if (graph[ cur.row ][ cur.col ] < cur_size && graph[ cur.row ][ cur.col ] != 0)
			{
				num++;
				if (num == cur_size)
				{
					cur_size++;
					num = 0;
				}
				graph[ cur.row ][ cur.col ] = 0;
				start = cur;
				ans += cur.dist;
				start.dist = 0;
				//System.out.println(cur.dist + " " + start.dist);
				return true;
			}

			for (int i = 0; i < 4; i++)
			{
				Point next = new Point(cur.row + v_r[ i ], cur.col + v_c[ i ]);
				if (check(next, visited))
				{
					// ���� ���� ��(������ ���̰� �� ū��)�� ��� �ؾ��ϱ� ������ ����Ʈ���� ���������κ�����
					// �Ÿ��� ������Ű�鼭 ���� ����Ʈ�� �̵��Ҷ� ���� +1�� ���ش�.
					// start������������ �׳� rowĭ, colĭ ���ؼ� �Ÿ��� ���ϸ� ���� ���� ���� ��� �ȵ�(Ʋ�� ��������)
					next.dist = cur.dist + 1;
					pq.offer(next);
				}
			}
		}

		return false;
	}

	static boolean check(Point _p, boolean[ ][ ] v)
	{
		if (_p.row < 0 || _p.row >= N) return false;
		if (_p.col < 0 || _p.col >= N) return false;
		if (graph[ _p.row ][ _p.col ] > cur_size) return false;
		if (v[ _p.row ][ _p.col ] == true) return false;
		v[ _p.row ][ _p.col ] = true;
		return true;
	}

	static void showGraph( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				sb.append(graph[ i ][ j ] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
