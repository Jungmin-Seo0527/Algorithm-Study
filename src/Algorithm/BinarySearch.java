
package Algorithm;

import java.io.*;
import java.util.*;

// BinarySearch
public class BinarySearch
{
	public static void main(String[ ] args) throws IOException
	{
		int arr[] = new int[ 100 ];
		for (int i = 0; i < 100; i++)
		{
			arr[ i ] = i;
		}
		int ret = binarySearch(arr, 50);
		System.out.println(ret);
	}

	static int binarySearch(int arr[], int target)
	{
		int start = 0;
		int end = arr.length - 1;
		int mid = (start + end) / 2;
		while (start <= end)
		{
			if (arr[ mid ] == target) return mid;
			else if (arr[ mid ] <= target) start = mid + 1;
			else end = mid - 1;

			mid = (start + end) / 2;
		}
		return -1;
	}

}
