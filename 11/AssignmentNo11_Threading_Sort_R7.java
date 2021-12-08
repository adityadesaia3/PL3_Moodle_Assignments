import java.lang.*;
import java.util.*;

class Thread1MergeSort implements Runnable
{
	static int size = 10;
	static int a[] = { 124, 98, 84, 8, 16, 346, 24, 2124, 321, 324 };
	
	Thread t1;

	Thread1MergeSort()
	{
		t1 = new Thread(this);
	}
	
	public synchronized void run()
	{
		try
		{
			MergeSort(a, 0, size - 1);
		}
		catch (Exception e) {}
	}
	
	static void MergeSort(int a[], int low, int high)
	{
		int mid;
		if (low < high)
		{
			mid = (low + high) / 2;
			MergeSort(a, low, mid);
			MergeSort(a, mid + 1, high);
			Merge(a, low, mid, high);
		}
	}
		
	static void Merge(int a[], int low, int mid, int high)
	{
		int h = low;
		int i = low;
		int j = mid + 1;

		int b [] = new int[a.length];
		
		while((h <= mid) && (j <= high))
		{
			if (a[h] <= a[j])
			{
				b[i] = a[h];
				h++;
			}
			else
			{
				b[i] = a[j];
				j++;
			}
			i++;
		}

		if (h > mid)
		{
			for (int k = j; k <= high; k++)
			{
				b[i] = a[k];
				i++;
			}
		}
		else
		{
			for (int k = h; k <= mid; k++)
			{
				b[i] = a[k];
				i++;
			}
		}

		for (int k = low; k <= high; k++) a[k] = b[k];
	}
}

class Thread2HeapSort implements Runnable
{
	static int size = 10;
	static int a[] = { 123, 97, 83, 7, 15, 345, 23, 2123, 320, 323 };
	
	static Thread t2;

	Thread2HeapSort()
	{
		t2 = new Thread(this);
	}
	
	public synchronized void run()
	{
		try
		{
			Heapify(a, size - 1);
			HeapSort(a, size - 1);
		}
		catch (Exception e) {}
	}
	static void HeapSort(int a[], int n)
	{
		Heapify(a, n);
		for (int i = n; i >= 1; i--)
		{
			int t = a[i];
			a[i] = a[0];
			a[0] = t;
			Adjust(a, 0, i - 1);
		}
	}
	static void Heapify(int a[], int n)
	{
		for (int i = (n / 2); i >= 0; i--) Adjust(a, i, n);
	}
	static void Adjust(int a[], int i, int n)
	{
		int j = 2 * i;
		int item = a[i];
	
		while(j <= n)
		{
			if ((j < n) && (a[j] < a[j + 1])) j = j + 1;
			if (item >= a[j]) break;
		
			int x = j / 2;
			a[x] = a[j];
			j = 2 * j;
		}
		int x = j / 2;
		a[x] = item;
	}
}

class FinalMerge implements Runnable
{
	static int size = 20;
	static int b[] = new int[size];
	
	Thread tf;

	FinalMerge()
	{
		tf = new Thread(this);	
	}
	
	public void run()
	{
		int i = 0;
		for (int j = 0; j < Thread1MergeSort.size; j++)
		{
			b[i] = Thread1MergeSort.a[j];
			i++;
		}
		for (int j = 0; j < Thread2HeapSort.size; j++)
		{
			b[i] = Thread2HeapSort.a[j];
			i++;
		}
		
		try
		{
			Merge(b, 0, 9, size - 1);
		}
		catch (Exception e) {}
	}
	
	static void Merge(int a[], int low, int mid, int high)
	{
		int h = low;
		int i = low;
		int j = mid + 1;

		int b [] = new int[a.length];
		
		while((h <= mid) && (j <= high))
		{
			if (a[h] <= a[j])
			{
				b[i] = a[h];
				h++;
			}
			else
			{
				b[i] = a[j];
				j++;
			}
			i++;
		}

		if (h > mid)
		{
			for (int k = j; k <= high; k++)
			{
				b[i] = a[k];
				i++;
			}
		}
		else
		{
			for (int k = h; k <= mid; k++)
			{
				b[i] = a[k];
				i++;
			}
		}

		for (int k = low; k <= high; k++) a[k] = b[k];
	}
}

public class AssignmentNo11_Threading_Sort_R7
{
	public static void main(String args [])
	{		
		System.out.println("\nFirst Unsorted array: ");
		for (int i = 0; i < Thread1MergeSort.size; i++)
		{
			System.out.print(Thread1MergeSort.a[i] + " ");
		}
		System.out.println();

		System.out.println("\nSecond Unsorted array: ");
		for (int i = 0; i < Thread2HeapSort.size; i++)
		{
			System.out.print(Thread2HeapSort.a[i] + " ");
		}
		System.out.println();
		
		Thread1MergeSort tms1 = new Thread1MergeSort();
		Thread2HeapSort	ths1  = new Thread2HeapSort();
		FinalMerge tfm = new FinalMerge();
		
		try
		{
			tms1.t1.start();
			ths1.t2.start();
			
			tms1.t1.join();
			ths1.t2.join();
			
			tfm.tf.start();
			tfm.tf.join();
		}
		catch (Exception e) {}
		
		System.out.println("\n\nFinal Sorted array: ");
		for (int i = 0; i < FinalMerge.size; i++)
		{
			System.out.print(FinalMerge.b[i] + " ");
		}
		System.out.println();
	}
}
