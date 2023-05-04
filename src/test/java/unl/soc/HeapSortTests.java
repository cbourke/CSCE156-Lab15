package unl.soc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class HeapSortTests {

	public static final Random r = new Random();

	public static final Comparator<Integer> INT_CMP = Integer::compareTo;

	/**
	 * Tests that the
	 * {@link HeapSort#heapSort(java.util.List, java.util.Comparator)} method
	 * properly sorts by testing a small fixed list of integers.
	 * 
	 */
	@Test
	public void heapSortFixedTest() {

		List<Integer> actualList = new ArrayList<>(Arrays.asList(1, 6, 2, 7, 3, 8, 4, 9, 5, 10));
		List<Integer> expectedList = new ArrayList<>(actualList);
		HeapSort.heapSort(actualList, INT_CMP);
		Collections.sort(expectedList, INT_CMP);
		Assertions.assertEquals(expectedList, actualList,
				"Heapsort did not match the expected sort order for the given array");
	}

	/**
	 * Tests that the
	 * {@link HeapSort#heapSort(java.util.List, java.util.Comparator)} method
	 * properly sorts by generating a random list of integers.
	 */
	@RepeatedTest(10)
	public void heapSortRandomTest() {

		List<Integer> actualList = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			actualList.add(r.nextInt());
		}
		List<Integer> expectedList = new ArrayList<>(actualList);
		HeapSort.heapSort(actualList, INT_CMP);
		Collections.sort(expectedList, INT_CMP);

		Assertions.assertEquals(expectedList, actualList,
				"Heapsort did not match the expected sort order for the given array");
	}

}
