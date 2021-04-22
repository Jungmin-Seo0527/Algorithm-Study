
package algorithm;

import java.io.*;

public class lower_upper_bound
{
	// 범위 안의 원소들 중 특정 target보다 크거나 같은 첫번째 원소의 인덱스를 반환
	// 오름차순의 배열이 있을때 target이 들어갈 수 있는 가장 작은 인덱스
	static int lowerBound(int arr[], int target)
	{
		int begin = 0;
		int end = arr.length;

		while (begin < end)
		{
			int mid = (begin + end) >> 1;
			if (arr[ mid ] >= target) end = mid;
			else begin = mid + 1;
		}
		return end;
	}

	// 범위 안의 원소들 중 특정 target 보다 큰 첫번째 원소의 인덱스를 반환
	// 오름차순의 배열이 있을때 target이 들어갈 수 있는 가장 큰 인덱스
	static int upperBound(int arr[], int target)
	{
		int begin = 0;
		int end = arr.length;

		while (begin < end)
		{
			int mid = (begin + end) >> 1;
			if (arr[ mid ] > target) end = mid;
			else begin = mid + 1;
		}
		return end;
	}

	public static void main(String[ ] args) throws IOException
	{
		int arr[] =
		{ 1, 2, 2, 3, 4, 4, 4, 5, 5, 5 };
		System.out.println(lowerBound(arr, 1));
		System.out.println(upperBound(arr, 4));

	}
}
