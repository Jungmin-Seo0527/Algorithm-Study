package Samgsung;

import java.io.*;
import java.util.*;

// BOJ20055_컨베이어_벨트_위의_로봇_2
public class BOJ20055_컨베이어_벨트_위의_로봇_2
{
	private static class Belt
	{
		private boolean robot;
		private int durability;

		public Belt(int _d, boolean _r)
		{
			this.robot = _r;
			this.durability = _d;
		}

		public boolean haveRobot( )
		{
			return robot;
		}

		public void upRobot( )
		{
			durability--;
			robot = true;
		}

		public void downRobot( )
		{
			robot = false;
		}

		public int getDurability( )
		{
			return durability;
		}
	}

	static int N, K, beltLen, up, down, broken, step;
	static Belt[ ] conveyor;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );

		while (broken < K)
		{
			step++;
			moveConveyor( );
			moveRobot( );
		}
		System.out.println(step);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		beltLen = (N << 1) + 1;
		conveyor = new Belt[beltLen];
		broken = 0;
		step = 0;
		up = 1;
		down = N;

		st = new StringTokenizer(br.readLine( ));
		for (int i = 1; i < beltLen; i++)
		{
			conveyor[ i ] = new Belt(Integer.parseInt(st.nextToken( )), false);
		}
	}

	static void moveConveyor( )
	{
		if (--up == 0) up = N << 1;
		if (--down == 0) down = N << 1;
		conveyor[ down ].downRobot( );
	}

	static void moveRobot( )
	{
		int cur_pos = down - 1 == 0 ? N << 1 : down - 1;
		int next_pos = cur_pos == N << 1 ? 1 : cur_pos + 1;
		int len = N;
		while (len > 0)
		{
			if (conveyor[ cur_pos ].haveRobot( ) == true && conveyor[ next_pos ].haveRobot( ) == false
					&& conveyor[ next_pos ].getDurability( ) > 0)
			{
				conveyor[ cur_pos ].downRobot( );
				conveyor[ next_pos ].upRobot( );

				if (conveyor[ next_pos ].getDurability( ) == 0)
				{
					broken++;
				}
				if (next_pos == down)
				{
					conveyor[ next_pos ].downRobot( );
				}
			}

			if (--cur_pos == 0)
			{
				cur_pos = N << 1;
				next_pos = 1;
			}
			else
			{
				next_pos = cur_pos + 1;
			}
			len--;
		}

		if (conveyor[ up ].getDurability( ) > 0 && conveyor[ up ].haveRobot( ) == false)
		{
			conveyor[ up ].upRobot( );
			if (conveyor[ up ].getDurability( ) == 0)
			{
				broken++;
			}
		}
	}

}