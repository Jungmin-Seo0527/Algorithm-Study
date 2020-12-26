package INU_code_festival_2020;

import java.io.*;
import java.util.*;

// BOJ20366_같이_눈사람_만들래
public class BOJ20366_같이_눈사람_만들래
{
	static int N;
	static Integer graph[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// 눈사람 하나를 기준으로 하나 만든다
	// 비교할 눈사람을 two pointer로 기준가 비교하면서 만든다.
	static void solve( )
	{
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < N - 1; i++)
		{
			for (int j = i; j < N; j++)
			{
				int snow1 = graph[ i ] + graph[ j ];
				int s2_top = i + 1;
				int s2_bottom = j - 1;
				
				// two pointer
				while (s2_top < s2_bottom)
				{
					int snow2 = graph[ s2_top ] + graph[ s2_bottom ];

					// 눈사람1이 더크면 눈사람 2의 top++(머리의 크기 증가)
					if (snow2 < snow1)
					{
						ans = Math.min(ans, snow1 - snow2);
						s2_top++;
					}
					else // 눈사람2가 더 크면 눈사람 2의 bottom--(하체의 크기 감소)
					{
						ans = Math.min(ans, snow2 - snow1);
						s2_bottom--;
					}
				}

			}
		}
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		graph = new Integer[ N ];

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			graph[ i ] = Integer.parseInt(st.nextToken( ));
		}
		// 오름차순으로 정렬
		Arrays.sort(graph);
	}
}
