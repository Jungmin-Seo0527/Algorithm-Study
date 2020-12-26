package INU_code_festival_2020;

import java.io.*;
import java.util.*;

// BOJ20361_일우는_야바위꾼
public class BOJ20361_일우는_야바위꾼
{

	static int N, X, K;
	static int[ ][ ] op;

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
		X = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		op = new int[ 2 ][ K ];
		for (int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			op[ 0 ][ i ] = Integer.parseInt(st.nextToken( ));
			op[ 1 ][ i ] = Integer.parseInt(st.nextToken( ));

		}
	}
	
	
	// 바꾸려는 컵안에 공이 들어 있으면 공의 위치를 바꾼다.
	static void solve( )
	{
		for (int i = 0; i < K; i++)
		{
			if (op[ 0 ][ i ] == X)
			{
				X = op[ 1 ][ i ];
			}
			else if (op[ 1 ][ i ] == X)
			{
				X = op[ 0 ][ i ];
			}
		}
		System.out.println(X);
	}

}
