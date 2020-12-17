
public class test
{
	static int [] t1= {1, 2, 3};
	static int [] t2= {4, 5, 6};
	
	public static void main(String[ ] args)
	{
		t1=t2;
		t2[0]=999;
		System.out.println(t1[0]);
	}
}
