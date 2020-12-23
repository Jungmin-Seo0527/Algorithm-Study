
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ20056_마법사_상어와_파이어볼
public class BOJ20056_마법사_상어와_파이어볼_2
{
	static class Ball
	{
		int massive, speed, dir, cnt;
		boolean even, odd;

		public Ball(int massive, int speed, int dir)
		{
			this.massive = massive;
			this.speed = speed;
			this.dir = dir;
			this.cnt = 1;
			if (this.dir % 2 == 0) even = true;
			else odd = true;
		}
	}

	static int N, M, K;

	static int[ ] v_r =
	{ -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[ ] v_c =
	{ 0, 1, 1, 1, 0, -1, -1, -1 };

	static Ball graph[][], temp[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		for (int i = 0; i < K; i++)
		{
			moveBalls( );
		}
		System.out.println(countMassive( ));
	}

	// 볼의 이동과 중복의 과정을 하나의 반복문에 한꺼번에 처리
	// LinkedList 가 아닌 단순 배열을 이용
	// 중복되는 부분은 중량과 속도를 누적으로 더하고 중복 갯수만큼 cnt++;
	// 볼이 이동을 할때마다 cnt를 조사
	// cnt==1 -> 중복된것 없음
	// cnt >1  -> cnt 만큼 ball이 중복됨
	// 중복될때마다 만약 최근에 오는 볼의 방향이 홀수이면 홀수 true -> 마지막에 중복값 조사할때 false -> 한번도 홀수 없었음
	// 짝수이면 짝수 true -> 마지막 중복값 조사때 false -> 한번도 짝수값 없었음
	static void moveBalls( )
	{
		// temp 에 저장하는 이유는 이동하려는 볼이 이동전의 볼과 만나는 경우를 방지하기 위해
		// 즉 모든 볼이 일괄적인 이동후 한 장소에 모이는 볼을 처리해야 한다.
		temp = new Ball[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (graph[ i ][ j ] == null) continue; // 아무것도 없는곳

				Ball cur_ball = graph[ i ][ j ];
				int flag = 1;
				if (cur_ball.cnt > 1) // 만약 중복되는 곳
				{
					if (!cur_ball.even || !cur_ball.odd) cur_ball.dir = 0; // 0, 2, 4, 6
					else cur_ball.dir = 1; // 1, 3, 5, 7
					cur_ball.massive /= 5;
					cur_ball.speed /= cur_ball.cnt;
					flag = 4; // 0, 2, 4, 6 혹은 1, 3, 5, 7 의 뱡향을 가지는 하나의 중복되는 곳에서 부터 나눠지는 방향
					if (cur_ball.massive == 0) continue; // 질량이 0이므로 없애버림
				}

				// 만약 위에서 중복되는 곳이라는 조건에 걸리면
				// 누적되어 합쳐졌던 볼들이 조건에 맞게 4가지 방향을 가진 볼들로 나눠저서 이동
				// 중복이 아닌 오직 한개만 있는 곳이라면 존재하는 볼의 방향과 속도대로 이동
				for (int k = 0; k < flag; k++, cur_ball.dir += 2)
				{
					int next_row = i + v_r[ cur_ball.dir ] * cur_ball.speed % N;
					int next_col = j + v_c[ cur_ball.dir ] * cur_ball.speed % N;

					// 각 행과 열의 맨 끝부분은 서로 이어져 있음
					if (next_row >= N) next_row -= N;
					else if (next_row < 0) next_row += N;
					if (next_col >= N) next_col -= N;
					else if (next_col < 0) next_col += N;

					// 이동하려는 곳에 아무것도 없는 경우
					if (temp[ next_row ][ next_col ] == null)
					{
						temp[ next_row ][ next_col ] = new Ball(cur_ball.massive,
						                cur_ball.speed, cur_ball.dir);
					}
					else // 이동하려는 곳에 이미 볼이 존재 -> 볼의 질량, 속도 누적, 지금까지의 방향의 홀수 짝수 조사
					{
						Ball temp_ball = temp[ next_row ][ next_col ];
						temp_ball.massive += cur_ball.massive;
						temp_ball.speed += cur_ball.speed;
						temp_ball.cnt++;

						// 현재의 볼의 방향이 짝수이면 저장하려는 볼의 짝수에 true -> 한번이라도 짝수가 존재
						// 현재의 볼의 향향이 홀수이면 저장하려는 볼의 혹수에 true -> 한번이라도 홀수가 존재
						if (cur_ball.dir % 2 == 0) temp_ball.even = true;
						else temp_ball.odd = true;

						temp[ next_row ][ next_col ] = temp_ball;
					}
				}
			}
		}
		// 복사의 개념이 아님
		// graph 포인터가 temp를 가르키는 원리
		// 즉 graph와 temp가 가르키는 곳이 같은 곳이다.(graph와 temp은 동일함)
		// 이후 moveBalls() 함수가 끝나면 temp는 사라지지만 graph가 가르키던 곳은 사라지지 않음
		// 이후 다시 moveBalls()가 실행되면 새로운 temp가 생성
		graph = temp;
	}

	static int countMassive( )
	{
		int ret = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				Ball cur = graph[ i ][ j ];
				if (cur == null) continue;
				if (cur.cnt == 1) ret += cur.massive;
				else ret += (cur.massive / 5 * 4);
			}
		}
		return ret;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		graph = new Ball[ N ][ N ];

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int r = Integer.parseInt(st.nextToken( )) - 1;
			int c = Integer.parseInt(st.nextToken( )) - 1;
			int m = Integer.parseInt(st.nextToken( ));
			int s = Integer.parseInt(st.nextToken( ));
			int d = Integer.parseInt(st.nextToken( ));
			graph[ r ][ c ] = new Ball(m, s, d);
		}

	}
}