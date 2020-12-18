import java.io.*;
import java.util.*;

public class Main
{
	static int N, A, B, ans;
	static Integer[ ] tile1, tile2;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		A = Integer.parseInt(st.nextToken( ));
		B = Integer.parseInt(st.nextToken( ));
		tile1 = new Integer[ A ];
		tile2 = new Integer[ B ];

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < A; i++)
		{
			tile1[ i ] = Integer.parseInt(st.nextToken( ));
		}

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < B; i++)
		{
			tile2[ i ] = Integer.parseInt(st.nextToken( ));
		}
		Arrays.sort(tile1, Collections.reverseOrder( ));
		Arrays.sort(tile2, Collections.reverseOrder( ));

	}

	static void solve( )
	{
		int tile1_num = 0;

		for (int i = 0; i <= B; i++)
		{
			int sum = 0;
			tile1_num = N - i * 2;
			if (tile1_num > A) continue;
			if (tile1_num < 0) continue;
			for (int j = 0; j < tile1_num; j++)
			{
				sum += tile1[ j ];
			}
			for (int j = 0; j < i; j++)
			{
				sum += tile2[ j ];
			}
			if (ans < sum) ans = sum;
		}
	}
}
