package unl.soc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BinarySearchTreeTests {

	BinarySearchTree<Integer> testBST;

	private static final Comparator<Integer> INT_CMP = Integer::compareTo;

	/**
	 * Setup the {@link BinarySearchTree} we will be using
	 */
	@BeforeEach
	public void bstSetup() {
		testBST = new BinarySearchTree<>(INT_CMP);
	}

	/**
	 * Test adding elements to a tree
	 */
	@Test
	public void bstFindElementTest() {
		testBST.addElement(1);
		testBST.addElement(2);
		testBST.addElement(3);
		testBST.addElement(4);
		testBST.addElement(5);
		testBST.addElement(6);
		testBST.addElement(7);

		assertEquals(1, testBST.findElement(1), "This exists in the tree, and so should be returned");
		assertEquals(2, testBST.findElement(2), "This exists in the tree, and so should be returned");
		assertEquals(3, testBST.findElement(3), "This exists in the tree, and so should be returned");
		assertEquals(4, testBST.findElement(4), "This exists in the tree, and so should be returned");
		assertEquals(5, testBST.findElement(5), "This exists in the tree, and so should be returned");
		assertTrue(testBST.containsElement(6),
				"containsElement() returns true if it can find the element. 6 exists in the tree, so this should return true");
		assertTrue(testBST.containsElement(7),
				"containsElement() returns true if it can find the element. 7 exists in the tree, so this should return true");

		assertNull(testBST.findElement(0), "This does not exist in this tree, so it should return null");
		assertNull(testBST.findElement(10), "This does not exist in this tree, so it should return null");
		assertNull(testBST.findElement(-3), "This does not exist in this tree, so it should return null");
		assertFalse(testBST.containsElement(-2),
				"containsElement() returns true if it can find the element. -2 does not exist in the tree, so this should return false");
	}

	/**
	 * Test the ability to count the number of leaves in a tree
	 */
	@Test
	public void bstGetNumberOfLeavesTest() {
		testBST.addElement(2);
		testBST.addElement(1);
		testBST.addElement(3);
		assertEquals(2, testBST.getNumLeaves(), "This is a balanced tree with 3 nodes. This makes 2 leaves");

		testBST = new BinarySearchTree<>(INT_CMP);
		testBST.addElement(1);
		testBST.addElement(2);
		testBST.addElement(3);
		assertEquals(1, testBST.getNumLeaves(), "This is an unbalanced tree with 1 leaf");

		testBST = new BinarySearchTree<>(INT_CMP);
		testBST.addElement(3);
		testBST.addElement(2);
		testBST.addElement(1);
		assertEquals(1, testBST.getNumLeaves(), "This is an unbalanced tree with 1 leaf");

		testBST = new BinarySearchTree<>(INT_CMP);
		testBST.addElement(7);
		testBST.addElement(3);
		testBST.addElement(11);
		testBST.addElement(1);
		testBST.addElement(5);
		testBST.addElement(9);
		testBST.addElement(13);
		testBST.addElement(0);
		testBST.addElement(2);
		testBST.addElement(4);
		testBST.addElement(6);
		testBST.addElement(8);
		testBST.addElement(10);
		testBST.addElement(12);
		testBST.addElement(14);
		assertEquals(8, testBST.getNumLeaves(), "This is a balanced tree with 15 nodes. This makes 8 leaves");
	}

	/**
	 * Test the ability to preorder traverse a tree
	 */
	@Test
	public void preOrderTraversalTest() {
		testBST.addElement(48);
		testBST.addElement(28);
		testBST.addElement(58);
		testBST.addElement(17);
		testBST.addElement(42);
		testBST.addElement(51);
		testBST.addElement(64);
		testBST.addElement(59);

		List<Integer> expectedOrder = new ArrayList<>(Arrays.asList(48, 28, 17, 42, 58, 51, 64, 59));

		assertEquals(expectedOrder, testBST.preOrderTraverse(), "The list did not match on the preorder traversal");
	}

	/**
	 * Test the ability to inorder traverse a tree
	 */
	@Test
	public void inOrderTraversalTest() {
		testBST.addElement(48);
		testBST.addElement(28);
		testBST.addElement(58);
		testBST.addElement(17);
		testBST.addElement(42);
		testBST.addElement(51);
		testBST.addElement(64);
		testBST.addElement(59);

		List<Integer> expectedOrder = new ArrayList<>(Arrays.asList(17, 28, 42, 48, 51, 58, 59, 64));

		assertEquals(expectedOrder, testBST.inOrderTraverse(), "The list did not match on the inorder traversal");
	}

	/**
	 * Test the ability to postorder traverse a tree
	 */
	@Test
	public void postOrderTraversalTest() {
		testBST.addElement(48);
		testBST.addElement(28);
		testBST.addElement(58);
		testBST.addElement(17);
		testBST.addElement(42);
		testBST.addElement(51);
		testBST.addElement(64);
		testBST.addElement(59);

		List<Integer> expectedOrder = new ArrayList<>(Arrays.asList(17, 42, 28, 51, 59, 64, 58, 48));

		assertEquals(expectedOrder, testBST.postOrderTraverse(), "The list did not match on the postorder traversal");
	}
}
