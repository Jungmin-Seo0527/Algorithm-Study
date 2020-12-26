
package INU_code_festival_2020;

import java.io.*;
import java.util.*;

// BOJ20363_당근_키우기
public class BOJ20363_당근_키우기
{
	static long X, Y;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		X = Integer.parseInt(st.nextToken( ));
		Y = Integer.parseInt(st.nextToken( ));

		// 채워야 하는 햇빛과 수분중
		// 큰값으로 주어지는 것을 모두 채우고
		// 반대편을 채울때 줄어들게 되는 큰값까지 고려해서
		// 큰값들을 모두모두 채운다
		// 나머지 작은 값들을 채운다
		long t1 = X;
		long t2 = Y;
		X = Long.max(t1, t2);
		Y = Long.min(t1, t2);
		long temp = Y % 10;
		long temp2 = Y / 10;

		long ans = X + temp2 * 11 + temp;
		System.out.println(ans);
	}

}