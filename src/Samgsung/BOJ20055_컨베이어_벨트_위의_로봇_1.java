package Samgsung;

import java.io.*;
import java.util.*;

// BOJ20055 컨베이어_벨트_위의_로봇
public class BOJ20055_컨베이어_벨트_위의_로봇_1
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

	//	// 1. 벨트가 한칸 회전한다.
	//	static void moveContainer( )
	//	{
	//		int container_end = container[ conSZ * 2 - 1 ];
	//		for (int i = conSZ * 2 - 1; i >= 1; i--)
	//		{
	//			container[ i ] = container[ i - 1 ];
	//		}
	//		container[ 0 ] = container_end;
	//
	//		// 벨트가 움직일때 벨트 위에 있는 로봇들도 함께 움직임
	//		for (int i = conSZ - 1; i >= 1; i--)
	//		{
	//			container_robot[ i ] = container_robot[ i - 1 ];
	//		}
	//		container_robot[ 0 ] = 0;
	//	}
	//
	//	// 2. 로봇 이동 -> 벨트와 함께 움직이는 것이 아니라 벨트가 움직인 후 로봇이 따로 한칸씩 더 움직인다
	//	static void moveRobot( )
	//	{
	//		container_robot[ conSZ - 1 ] = 0;
	//		for (int i = conSZ - 2; i >= 0; i--)
	//		{
	//			if (container_robot[ i ] == 1) // 컨테이너에 로봇이 있을때
	//			{
	//				if (container[ i + 1 ] > 0 && container_robot[ i + 1 ] == 0) // 다음칸 확인
	//				{
	//					container[ i + 1 ]--;
	//					container_robot[ i ] = 0;
	//					container_robot[ i + 1 ] = 1;
	//				}
	//			}
	//		}
	//		// 컨테이너 첫칸 확인 후 로봇 올리기
	//		if (container_robot[ 0 ] == 0 && container[ 0 ] > 0)
	//		{
	//			container[ 0 ]--;
	//			container_robot[ 0 ] = 1;
	//		}
	//	}

	// 시간초과->
	//
	// 벨트가 움직이는
	// 것과 로봇이
	// 움직이는 반복문을
	// 한번에 처리==>시간초과

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
	//				if (container_robot[ i ] == 1) // 컨테이너에 로봇이 있을때
	//				{
	//					if (container[ i + 1 ] > 0 && container_robot[ i + 1 ] == 0) // 다음칸 확인
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

	// 벨트가 움직일때 벨트 배열을 움직이는 것이 아니라 시작과 끝 인덱스를 변경하는 방법
	static void moveContainer( )
	{
		down--;
		up--;
		if (down == 0) down = conSZ * 2;
		if (up == 0) up = conSZ * 2;
	}

	// que -> robot들의 위치 정보 저장
	static void moveRobot( )
	{
		int que_size = que.size( );
		for (int i = 0; i < que_size; i++)
		{
			int cur_pos = que.poll( );
			if (cur_pos == down) // 로봇 위치가 down
			{
				robots[ cur_pos ] = false;
				continue;
			}
			
			// 현재 위치의 다음 위치(로봇이 움직일 위치 정보) 조사
			int next_pos = cur_pos + 1;
			if (next_pos > conSZ * 2) next_pos = 1;

			// 내구성이 0이상이고 해당 위치에 로봇이 존재하지 않음
			if (container[ next_pos ] > 0 && !robots[ next_pos ])
			{
				container[ next_pos ]--;
				if (next_pos == down) // 다음 위치가 down
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

		// up 위치에 로봇 올리기
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
