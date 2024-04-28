package unl.soc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;

/**
 * A Binary Search Tree (BST) implementation. This implementation does not
 * guarantee a <i>balanced</i> tree and so operations may not be fully
 * O(log(n)).
 *
 * @param <T>
 */
public class BinarySearchTree<T> {

	private TreeNode<T> root;

	/**
	 * The {@link Comparator} used for ordering elements in this tree.
	 * 
	 */
	private final Comparator<T> comparator;

	public BinarySearchTree(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Returns <code>true</code> if this tree contains the given element
	 * <code>item</code>, <code>false</code> if it does not.
	 * 
	 * @param item
	 * @return
	 */
	public boolean containsElement(T item) {
		return (this.findElement(item) != null);
	}

	/**
	 * Computes the depth of this tree (the maximal depth of any node in the tree)
	 * using a "tree-walk" algorithm (no additional data structures).
	 * 
	 * @return
	 */
	public int getMaxDepth() {
		if (root == null || (root.getLeftChild() == null && root.getRightChild() == null)) {
			return 0;
		}

		TreeNode<T> curr = root;
		TreeNode<T> prev = null;
		int depth = 0;
		int maxDepth = depth;
		while (curr != null) {
			if (curr.getParent() == prev) {
				if (curr.getLeftChild() != null) {
					prev = curr;
					curr = curr.getLeftChild();
					depth++;
					maxDepth = Math.max(maxDepth, depth);
				} else if (curr.getRightChild() != null) {
					prev = curr;
					curr = curr.getRightChild();
					depth++;
					maxDepth = Math.max(maxDepth, depth);
				} else {
					prev = curr;
					curr = curr.getParent();
					depth--;
				}
			} else if (curr.getLeftChild() == prev) {
				if (curr.getRightChild() != null) {
					prev = curr;
					curr = curr.getRightChild();
					depth++;
					maxDepth = Math.max(maxDepth, depth);
				} else {
					prev = curr;
					curr = curr.getParent();
					depth--;
				}
			} else if (curr.getRightChild() == prev) {
				prev = curr;
				curr = curr.getParent();
				depth--;
			} else {
				throw new IllegalStateException("Current/Previous: " + curr.getValue() + ", " + prev.getValue());
			}
		}
		return maxDepth;
	}

	/**
	 * Computes the depth of the given tree node in this tree. The depth of a node
	 * <i>u</i> is the length of the unique path between the root and the node
	 * <i>u</i>.
	 * 
	 * @param node
	 * @return
	 */
	private int getDepth(TreeNode<T> node) {
		int depth = 0;
		TreeNode<T> curr = node;
		while (curr != root) {
			curr = curr.getParent();
			depth++;
		}
		return depth;
	}

	/**
	 * (Re)computes the number of nodes in this tree using a stack-based preorder
	 * traversal strategy.
	 * 
	 * @return
	 */
	public int getNumNodes() {

		int count = 0;

		if (root == null) {
			return count;
		}
		Stack<TreeNode<T>> s = new Stack<TreeNode<T>>();
		TreeNode<T> curr = root;
		while (curr != null) {
			if (curr.getRightChild() != null)
				s.push(curr.getRightChild());
			if (curr.getLeftChild() != null)
				s.push(curr.getLeftChild());

			count++;
			if (s.isEmpty())
				curr = null;
			else
				curr = s.pop();
		}
		return count;
	}

	/**
	 * Adds the given element, <code>item</code> to this tree.
	 * 
	 * Throws an {@link IllegalArgumentException} if the given element is
	 * <code>null</code>.
	 * 
	 * Throws an {@link IllegalStateException} if the given element is already in
	 * the tree.
	 * 
	 * @param item
	 */
	public void addElement(T item) {
		if (item == null) {
			throw new IllegalArgumentException("BinarySearchTree does not allow null elements");
		}
		if (containsElement(item)) {
			throw new IllegalStateException("BinarySearchTree does not allow duplicate elements");
		}
		TreeNode<T> newNode = new TreeNode<T>(item);
		if (root == null) {
			root = newNode;
			return;
		}
		TreeNode<T> curr = root;
		TreeNode<T> prev = null;
		while (curr != null) {
			if (this.comparator.compare(curr.getValue(), item) > 0) {
				prev = curr;
				curr = curr.getLeftChild();
			} else {
				prev = curr;
				curr = curr.getRightChild();
			}
		}
		if (this.comparator.compare(prev.getValue(), item) > 0) {
			prev.setLeftChild(newNode);
			newNode.setParent(prev);
		} else {
			prev.setRightChild(newNode);
			newNode.setParent(prev);
		}
	}

	/**
	 * Returns the element in this tree that matches the given element
	 * <code>item</code> according to <code>this</code> tree's {@link #comparator}.
	 * 
	 * @param item
	 * @return
	 */
	public T findElement(T item) {

		T result = null;

		if (root == null) {
			return result;
		}

		// TODO: implement this

		return result;
	}

	/**
	 * Computes the number of <i>leaf</i> nodes in this tree. A node is a leaf if it
	 * has no children.
	 * 
	 * @return
	 */
	public int getNumLeaves() {
		// TODO: implement this
		return 0;
	}

	/**
	 * Produces a {@link java.util.List} of elements in this tree in a preorder
	 * ordering.
	 * 
	 * @return
	 */
	public List<T> preOrderTraverse() {
		List<T> elements = new ArrayList<>();
		preOrderTraverse(this.root, elements);
		return elements;
	}

	/**
	 * Method used by {@link #preOrderTraverse()} to recursively add elements to the
	 * given {@code elements} list.
	 * 
	 * @param u
	 * @param elements
	 */
	private void preOrderTraverse(TreeNode<T> u, List<T> elements) {
		if (u == null) {
			return;
		}
		elements.add(u.getValue());
		preOrderTraverse(u.getLeftChild(), elements);
		preOrderTraverse(u.getRightChild(), elements);
	}

	/**
	 * A stack-based preorder traversal. Returns a {@link java.util.List} of
	 * elements in this tree in a preorder ordering.
	 * 
	 * @return
	 */
	public List<T> preOrderStackTraverse() {
		List<T> elements = new ArrayList<>();
		if (this.root == null) {
			return elements;
		}
		Deque<TreeNode<T>> stack = new LinkedList<>();
		stack.push(this.root);
		while (!stack.isEmpty()) {
			TreeNode<T> u = stack.pop();
			elements.add(u.getValue());
			stack.push(u.getRightChild());
			stack.push(u.getLeftChild());
		}
		return elements;
	}

	/**
	 * Produces a {@link java.util.List} of elements in this tree in an inorder
	 * ordering.
	 * 
	 * @return
	 */
	public List<T> inOrderTraverse() {
		List<T> elements = new ArrayList<>();
		// TODO: implement this

		return elements;
	}

	/**
	 * Produces a {@link java.util.List} of elements in this tree in a postorder
	 * ordering.
	 * 
	 * @return
	 */
	public List<T> postOrderTraverse() {
		List<T> elements = new ArrayList<>();
		// TODO: implement this

		return elements;
	}

	/**
	 * Returns a {@link String} representation of the topology (structure) of this
	 * BST. Elements are "nested" and indented to show parent/child relations in a
	 * vertical display.
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		if (root == null) {
			return "[empty]";
		}

		StringBuilder sb = new StringBuilder();
		Stack<TreeNode<T>> s = new Stack<TreeNode<T>>();
		TreeNode<T> curr = root;
		while (curr != null) {
			if (curr.getRightChild() != null)
				s.push(curr.getRightChild());
			if (curr.getLeftChild() != null)
				s.push(curr.getLeftChild());

			for (int i = 0; i < getDepth(curr); i++)
				sb.append(" ");
			sb.append("|-" + curr.getValue() + "\n");
			if (s.isEmpty())
				curr = null;
			else
				curr = s.pop();
		}
		return sb.toString();
	}

	/**
	 * Produces a LaTeX tikz diagram representation of this tree. To visualize this
	 * diagram, you need to compile it in a LaTeX document/environment.
	 * 
	 * @return
	 */
	public String toTikz() {
		if (root == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\\begin{tikzpicture}[transform shape,scale=1.0,level distance=1.25cm,level/.style={sibling distance=5cm/#1},every node/.style={circle,draw,minimum size=.75cm}]\n");
		sb.append("\\node{" + this.root.getValue() + "}\n");
		toTikz(this.root.getLeftChild(), sb);
		toTikz(this.root.getRightChild(), sb);
		sb.append(";\n");
		sb.append("\\end{tikzpicture}\n");
		return sb.toString();
	}

	/**
	 * Internal recursive method for the {{@link #toTikz()} method.
	 * 
	 * @param u
	 * @param sb
	 */
	private void toTikz(TreeNode<T> u, StringBuilder sb) {
		if (u == null) {
			sb.append("child[draw opacity=0.0] {}\n");
		} else {
			sb.append("child {node {$" + u.getValue() + "$}\n");
			toTikz(u.getLeftChild(), sb);
			toTikz(u.getRightChild(), sb);
			sb.append("}\n");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		BinarySearchTree other = (BinarySearchTree) obj;

		Stack<TreeNode<T>> s = new Stack<TreeNode<T>>();
		Stack<TreeNode<T>> t = new Stack<TreeNode<T>>();

		TreeNode<T> sCurr = this.root;
		TreeNode<T> tCurr = other.root;
		while (sCurr != null && tCurr != null) {

			if (sCurr.getRightChild() != null) {
				s.push(sCurr.getRightChild());
			}
			if (tCurr.getRightChild() != null) {
				t.push(tCurr.getRightChild());
			}

			if (sCurr.getLeftChild() != null) {
				s.push(sCurr.getLeftChild());
			}
			if (tCurr.getLeftChild() != null) {
				t.push(tCurr.getLeftChild());
			}

			if (!sCurr.equals(tCurr)) {
				return false;
			}
			if (s.isEmpty()) {
				sCurr = null;
			} else {
				sCurr = s.pop();
			}
			if (t.isEmpty()) {
				tCurr = null;
			} else {
				tCurr = t.pop();
			}
		}

		if (sCurr != null || tCurr != null) {
			return false;
		}
		return true;
	}

}
