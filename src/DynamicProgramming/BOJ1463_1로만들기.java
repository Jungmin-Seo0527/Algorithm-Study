
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ1463_1�θ����
public class BOJ1463_1�θ����
{
	static int N;
	static int[ ] dp;

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
		dp = new int[ N + 1 ];
	}

	static void solve( )
	{
		for (int i = N; i >= 1; i--)
		{
			// 3���� ���
			for (int j = 1; j <= 3; j++)
			{
				// -1
				if (j == 1)
				{
					if (i - 1 > 0) // �� Ȯ���Ϸ��� ���� 1�̻�
					{
						if ((dp[ i - 1 ] > dp[ i ] + 1) || dp[ i - 1 ] == 0)
						{
							dp[ i - 1 ] = dp[ i ] + 1;
						}
					}
				}
				else // 2�Ǵ� 3���� ������ �������� 0�̸� �׼��� ����
				{
					if (i % j == 0 && i / j > 0)
					{
						int temp = i / j;
						if (dp[ temp ] > dp[ i ] + 1 || dp[ temp ] == 0)
						{
							dp[ temp ] = dp[ i ] + 1;
						}
					}
				}
			}
		}
		System.out.println(dp[ 1 ]);
	}
}
