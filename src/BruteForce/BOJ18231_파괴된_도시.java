
package BruteForce;

import java.io.*;
import java.util.*;

// BOJ18231_�ı���_����
public class BOJ18231_�ı���_����
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

	// graph �� row =0 -> ���� ���� ����(�ı� ����)
	static void solve( )
	{
		int cnt = 0; // ��ź ���� ����
		StringBuilder sb = new StringBuilder( );
		for (int i = 1; i <= N; i++)
		{
			if (check(i)) // i ���ÿ� ��ź ���� ���� �Ѱ�?
			{
				cnt++;
				graph[ 0 ][ i ] = true;
				sb.append(i + " ");
			}
		}

		for (int i = 1; i <= N; i++)
		{
			if (graph[ 0 ][ i ] != destroy[ i ]) // ���� ���õ� ���¿� ���� ���� ��
			{
				System.out.println("-1");
				return;
			}

		}
		System.out.println(cnt);
		System.out.println(sb);
	}

	// c ���ÿ� ��ź ���Ͻ� ����Ǿ� �ִ� �����߿� �ı��Ǹ� �ȵǴ� ���ð� �ִ��� �Ǻ�
	// c ���ÿ� ��ź ���� �����ϸ� graph�� row=0 �� �ش� ������ ���¸� ���ķ� ����
	static boolean check(int c)
	{
		if (destroy[ c ] == false) return false;
		for (int i = 1; i <= N; i++)
		{
			if (i == c) continue;
			if (graph[ i ][ c ] == true && destroy[ i ] == false) return false;
		}

		// ���� ����, ���� ���� ����(����)
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
