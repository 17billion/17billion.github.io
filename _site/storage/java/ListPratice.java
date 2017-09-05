
import java.util.ArrayList;
import java.util.Vector;

public class ListPratice {
	private static final int NUM = 1000000;
	private static final int TCCNT = 10;

	private static long[] allInsertTimeArr = new long[TCCNT];
	private static long[] middleInsertTimeArr = new long[TCCNT];
	private static long[] middleGetTimeArr = new long[TCCNT];

	public static void main(String[] args) {
		System.out.println("--------VECTOR--------");
		testTC("vector");
		System.out.println("--------ARRAYLIST--------");
		testTC("arraylist");
		System.out.println("--------LINKEDLIST--------");
		testTC("linkedlist");
	}

	/**
	  * Vector insert, get practice
	  * @param int cnt(testcase cnt)
	  */
	public static void vecTest(int cnt) {
		Vector<Integer> a = new Vector<Integer>();
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			a.add(i);
		}
		long endTime = System.nanoTime();
		allInsertTimeArr[cnt] = endTime - startTime;
		startTime = System.nanoTime();
		a.add(1, 100);
		endTime = System.nanoTime();
		middleInsertTimeArr[cnt] = endTime - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			a.get(i);
		}
		endTime = System.nanoTime();
		middleGetTimeArr[cnt] = endTime - startTime;

	}

	/**
	  * arraylist insert, get practice
	  * @param int cnt(testcase cnt)
	  */
	public static void arrListTest(int cnt) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			a.add(i);
		}
		long endTime = System.nanoTime();
		allInsertTimeArr[cnt] = endTime - startTime;
		startTime = System.nanoTime();
		a.add(1, 100);
		endTime = System.nanoTime();
		middleInsertTimeArr[cnt] = endTime - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			a.get(i);
		}
		endTime = System.nanoTime();
		middleGetTimeArr[cnt] = endTime - startTime;
	}

	/**
	  * linkedlist insert, get practice
	  * @param int cnt(testcase cnt)
	  */
	public static void linListTest(int cnt) {
		java.util.LinkedList<Integer> a = new java.util.LinkedList<Integer>();
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			a.add(i);
		}
		long endTime = System.nanoTime();
		allInsertTimeArr[cnt] = endTime - startTime;
		startTime = System.nanoTime();
		a.add(1, 100);
		endTime = System.nanoTime();
		middleInsertTimeArr[cnt] = endTime - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			a.get(i);
		}
		endTime = System.nanoTime();
		middleGetTimeArr[cnt] = endTime - startTime;
	}

	/**
	  * print time (ns)
	  */
	static void outputArr() {
		System.out.println("---allInsertTimeArr---");
		for (int i = 0; i < TCCNT; i++) {
			System.out.println(allInsertTimeArr[i] + "ns");
		}

		System.out.println("---middleInsertTimeArr---");
		for (int i = 0; i < TCCNT; i++) {
			System.out.println(middleInsertTimeArr[i] + "ns");
		}
		
		System.out.println("---middleGetTimeArr---");
		for (int i = 0; i < TCCNT; i++) {
			System.out.println(middleGetTimeArr[i] + "ns");
		}
	}

	/**
	  * call list
	  */
	static void testTC(String type) {
		for (int i = 0; i < TCCNT; i++) {
			if (type == "vector") {
				ListPratice.vecTest(i);
			} else if (type == "arraylist") {
				ListPratice.arrListTest(i);
			} else {
				ListPratice.linListTest(i);
			}
		}
		ListPratice.outputArr();
	}
}
