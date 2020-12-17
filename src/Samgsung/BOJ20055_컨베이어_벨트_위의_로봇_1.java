package Samgsung;

import java.io.*;
import java.util.*;

// BOJ20055 �����̾�_��Ʈ_����_�κ�
public class BOJ20055_�����̾�_��Ʈ_����_�κ�_1
{
	static int conSZ, end, up, down, ans;
	static int[ ] container;
	static boolean robots[];

	static Queue<Integer> que = new LinkedList<>( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );

		while (true)
		{
			ans++;
			moveContainer( );
			moveRobot( );
			// move( );
			if (isEnd( )) break;

		}
		System.out.println(ans);
	} //// end main

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		conSZ = Integer.parseInt(st.nextToken( ));
		end = Integer.parseInt(st.nextToken( ));
		container = new int[conSZ * 2 + 1];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 1; i < conSZ * 2 + 1; i++)
		{
			container[ i ] = Integer.parseInt(st.nextToken( ));
		}
		robots = new boolean[conSZ * 2 + 1];
		up = 1;
		down = conSZ;
	}

	//	// 1. ��Ʈ�� ��ĭ ȸ���Ѵ�.
	//	static void moveContainer( )
	//	{
	//		int container_end = container[ conSZ * 2 - 1 ];
	//		for (int i = conSZ * 2 - 1; i >= 1; i--)
	//		{
	//			container[ i ] = container[ i - 1 ];
	//		}
	//		container[ 0 ] = container_end;
	//
	//		// ��Ʈ�� �����϶� ��Ʈ ���� �ִ� �κ��鵵 �Բ� ������
	//		for (int i = conSZ - 1; i >= 1; i--)
	//		{
	//			container_robot[ i ] = container_robot[ i - 1 ];
	//		}
	//		container_robot[ 0 ] = 0;
	//	}
	//
	//	// 2. �κ� �̵� -> ��Ʈ�� �Բ� �����̴� ���� �ƴ϶� ��Ʈ�� ������ �� �κ��� ���� ��ĭ�� �� �����δ�
	//	static void moveRobot( )
	//	{
	//		container_robot[ conSZ - 1 ] = 0;
	//		for (int i = conSZ - 2; i >= 0; i--)
	//		{
	//			if (container_robot[ i ] == 1) // �����̳ʿ� �κ��� ������
	//			{
	//				if (container[ i + 1 ] > 0 && container_robot[ i + 1 ] == 0) // ����ĭ Ȯ��
	//				{
	//					container[ i + 1 ]--;
	//					container_robot[ i ] = 0;
	//					container_robot[ i + 1 ] = 1;
	//				}
	//			}
	//		}
	//		// �����̳� ùĭ Ȯ�� �� �κ� �ø���
	//		if (container_robot[ 0 ] == 0 && container[ 0 ] > 0)
	//		{
	//			container[ 0 ]--;
	//			container_robot[ 0 ] = 1;
	//		}
	//	}

	// �ð��ʰ�->
	//
	// ��Ʈ�� �����̴�
	// �Ͱ� �κ���
	// �����̴� �ݺ�����
	// �ѹ��� ó��==>�ð��ʰ�

	//	static void move( )
	//	{
	//		int zero = 0;
	//		int container_end = container[ conSZ * 2 - 1 ];
	//		container_robot[ conSZ - 1 ] = 0;
	//		for (int i = conSZ * 2 - 1; i >= 1; i--)
	//		{
	//			container[ i ] = container[ i - 1 ];
	//			if (i <= conSZ - 2)
	//			{
	//				container_robot[ i ] = container_robot[ i - 1 ];
	//				if (container_robot[ i ] == 1) // �����̳ʿ� �κ��� ������
	//				{
	//					if (container[ i + 1 ] > 0 && container_robot[ i + 1 ] == 0) // ����ĭ Ȯ��
	//					{
	//						container[ i + 1 ]--;
	//						container_robot[ i ] = 0;
	//						container_robot[ i + 1 ] = 1;
	//					}
	//				}
	//			}
	//			if (i + 1 < conSZ * 2)
	//			{
	//				if (container[ i + 1 ] == 0) zero++;
	//			}
	//		}
	//		container[ 0 ] = container_end;
	//		container_robot[ 0 ] = 0;
	//		if (container_robot[ 0 ] == 0 && container[ 0 ] > 0)
	//		{
	//			container[ 0 ]--;
	//			container_robot[ 0 ] = 1;
	//		}
	//		if (container[ 0 ] == 0) zero++;
	//		if (container[ 1 ] == 0) zero++;
	//
	//		if (zero == end) isEnd = true;
	//
	//	}

	// ��Ʈ�� �����϶� ��Ʈ �迭�� �����̴� ���� �ƴ϶� ���۰� �� �ε����� �����ϴ� ���
	static void moveContainer( )
	{
		down--;
		up--;
		if (down == 0) down = conSZ * 2;
		if (up == 0) up = conSZ * 2;
	}

	// que -> robot���� ��ġ ���� ����
	static void moveRobot( )
	{
		int que_size = que.size( );
		for (int i = 0; i < que_size; i++)
		{
			int cur_pos = que.poll( );
			if (cur_pos == down) // �κ� ��ġ�� down
			{
				robots[ cur_pos ] = false;
				continue;
			}
			
			// ���� ��ġ�� ���� ��ġ(�κ��� ������ ��ġ ����) ����
			int next_pos = cur_pos + 1;
			if (next_pos > conSZ * 2) next_pos = 1;

			// �������� 0�̻��̰� �ش� ��ġ�� �κ��� �������� ����
			if (container[ next_pos ] > 0 && !robots[ next_pos ])
			{
				container[ next_pos ]--;
				if (next_pos == down) // ���� ��ġ�� down
				{
					robots[ cur_pos ] = false;
					continue;
				}

				robots[ next_pos ] = true;
				robots[ cur_pos ] = false;
				que.add(next_pos);
			}
			else
			{
				que.add(cur_pos);
			}
		}

		// up ��ġ�� �κ� �ø���
		if (container[ up ] > 0 && robots[ up ] == false)
		{
			container[ up ]--;
			robots[ up ] = true;
			que.add(up);
		}
	}
	
	static boolean isEnd( )
	{
		int cnt = 0;
		for (int i = 1; i <= conSZ * 2; i++)
		{
			if (container[ i ] == 0) cnt++;
			if (cnt == end) return true;
		}
		return false;
	}

} //// end class
