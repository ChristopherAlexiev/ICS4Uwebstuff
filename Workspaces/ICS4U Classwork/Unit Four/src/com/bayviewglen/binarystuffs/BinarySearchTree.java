package com.bayviewglen.binarystuffs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BinarySearchTree {

	// This is the root (top) node of the tree
	private BinaryNode root;

	// These are various variable and objects used in methods in the binarytree:
	private String savingString;
	private boolean searchVariable;
	private ArrayList<Comparable> balancingList;

	// constructor
	BinarySearchTree() {
	}

	///////////////////////////////////////////// INSERT

	// This public method adds a node to the tree given a comparable to be in
	// the new node
	public void insert(Comparable element) {
		if (root == null) {
			root = new BinaryNode(element);
			root.setParent(null);
			// only root has null parent, useful for identification of root
			return;
		}
		// calling the private recursive method to insert
		insertOrder(element, root);

	}

	// This is the private recursive insert method that adds a node to the right
	// spot in the tree and sets its parents properly
	private void insertOrder(Comparable element, BinaryNode nody) {
		if (element.compareTo(nody.getElement()) == -1 || element.compareTo(nody.getElement()) == 0) {
			if (nody.getLeftChild() != null) {
				insertOrder(element, nody.getLeftChild());
			} else {
				nody.setLeftChild(new BinaryNode(element));
				nody.getLeftChild().setParent(nody);// parent stuff
			}
		} else {
			if (nody.getRightChild() != null) {
				insertOrder(element, nody.getRightChild());
			} else {
				nody.setRightChild(new BinaryNode(element));
				nody.getRightChild().setParent(nody);// parent stuff
			}
		}
	}

	/////////////////////////////////////// TRAVERSAL FOR PRINTING
	// the public method to print the nodes' elements in order
	public void print() {
		if (root != null) {
			inOrderTraverse(root);
		}
	}

	// the private recursive method called by print()
	private void inOrderTraverse(BinaryNode nody) {
		if (nody.getLeftChild() != null) {
			inOrderTraverse(nody.getLeftChild());
		}
		processNode(nody);
		if (nody.getRightChild() != null) {
			inOrderTraverse(nody.getRightChild());
		}
	}

	////////////////////////////// TRAVERSAL FOR FINDING MATCHING CONTACTS

	// This pubic method traverses the tree and returns whether or not the tree
	// contains a
	// contact that contains the inputted contact's phone number as a substring
	// of its own phone, first name, or last name
	// it uses the variable searchVariable to know whether or not the recursive
	// method it calls found any matching contacts
	public boolean findContact(Comparable object) {
		searchVariable = false;
		if (root != null) {
			traverseForFindContact(root, object);
		}
		return searchVariable;
	}

	// this is the recursive method called by findContact. It compares objects
	// using the method contactContainsInfo, which checks for matching
	// substrings in the values of the elements
	private void traverseForFindContact(BinaryNode nody, Comparable object) {
		
		if (nody.getLeftChild() != null) {
			traverseForFindContact(nody.getLeftChild(), object);
		}
		if (nody.getElement().contactContainsInfo(object) == true) {
			processNode(nody);
			searchVariable = true;
		}
		if (nody.getRightChild() != null) {
			traverseForFindContact(nody.getRightChild(), object);
		}
	}

	/////////////////////////////////////////////// DELETING

	// The public method for deleting an element from the tree
	public void delete(Comparable element) {
		deleteSearch(root, element, true);
	}

	// Search the nodes, then run the removeNode method on the node it is told
	// to delete
	// The method also has an isLeftChild parameter to keep track of whether the
	// current node is a left or right child, making it easier to manipulate in
	// the removeNode method
	private void deleteSearch(BinaryNode nody, Comparable element, boolean isLeftChild) {
		if (nody != null) {
			if (nody.getElement().compareTo(element) == 0) {
				removeNode(nody, isLeftChild);
			} else if (nody.getElement().compareTo(element) == 1) {
				deleteSearch(nody.getLeftChild(), element, true);
			} else {
				deleteSearch(nody.getRightChild(), element, false);
			}
		}
	}

	// Actually delete the node, through one of 3 cases: if the node has one
	// child, two children, or zero children
	private void removeNode(BinaryNode nody, boolean isLeftChild) {
		if (nody.getLeftChild() == null && nody.getRightChild() == null) {
			// if the node has one child just set node to null
			if (nody.getParent() == null) {
				// if the node has a null parent it means it is the root
				root = null;
			} else if (isLeftChild) {
				// if the node is a left child then set it's parent's left child
				// to null
				nody.getParent().setLeftChild(null);
			} else {
				// if the node is a right child...
				nody.getParent().setRightChild(null);
			}
		} else if (nody.getLeftChild() == null && nody.getRightChild() != null) {
			// if only the node's right child is occupied then set the current
			// node to its right child
			if (nody.getParent() == null) {
				// if it is the root...
				root = root.getRightChild();
				root.setParent(null);
			} else if (isLeftChild) {
				// if the node is a left child then set its parent's left child
				// to the new node
				// also modify the parent
				BinaryNode tempParent = nody.getParent();
				tempParent.setLeftChild(nody.getRightChild());
				tempParent.getLeftChild().setParent(tempParent);
			} else {
				// if the nodeis a right child
				BinaryNode tempParent = nody.getParent();
				tempParent.setRightChild(nody.getRightChild());
				tempParent.getRightChild().setParent(tempParent);
			}
		} else if (nody.getLeftChild() != null && nody.getRightChild() == null) {
			// if only the node's left child is occupied then set the current
			// node to its left child
			if (nody.getParent() == null) {
				// if it is the root
				root = root.getLeftChild();
				root.setParent(null);
			} else if (isLeftChild) {
				// if the node is a left child then set its parent's left child
				// to the new node
				// also modify the parent
				BinaryNode tempParent = nody.getParent();
				tempParent.setLeftChild(nody.getLeftChild());
				tempParent.getLeftChild().setParent(tempParent);
			} else {
				// if the node is a right child
				BinaryNode tempParent = nody.getParent();
				tempParent.setRightChild(nody.getLeftChild());
				tempParent.getRightChild().setParent(tempParent);
			}
		} else {
			// If the node has 2 children, then find the min from the right
			// subtree and set the node's element to the minNode's element
			BinaryNode minNode = findMin(nody.getRightChild());
			nody.setElement(minNode.getElement());
			// If the left child of the right child of the current node is null
			// then the
			// minNode is a right child, otherwise it is a left child. This
			// tells us whether to use true or falls when calling the method to
			// remove the minNode below
			if (nody.getRightChild().getLeftChild() == null) {
				removeNode(minNode, false);
			} else {
				removeNode(minNode, true);
			}
		}
	}

	// This method returns the binaryNode with the smallest element in the
	// subtree of a given node
	private BinaryNode findMin(BinaryNode nody) {
		if (nody.getLeftChild() == null) {
			return nody;
		} else {
			return findMin(nody.getLeftChild());
		}
	}

	////////////////////////////////////////////////// SAVING
	// The public method for saving
	public void saveTree() {
		savingString = "";
		if (root != null) {
			saveTraverse(root);
		}
		saveStringToFile();
	}

	// Traverse the nodes in order and add a string of each element to the
	// savingString
	private void saveTraverse(BinaryNode nody) {
		if (nody.getLeftChild() != null) {
			saveTraverse(nody.getLeftChild());
		}
		savingString += nody.getElement().toStringForSave();
		if (nody.getRightChild() != null) {
			saveTraverse(nody.getRightChild());
		}
	}

	// this method writes the contacts in savingString to the file
	public void saveStringToFile() {
		try {
			FileWriter writer = new FileWriter("input/contacts.dat");
			String[] savingArray = savingString.split("~");
			for (int i = 1; i < savingArray.length - 2; i += 3) {
				writer.write(savingArray[i] + "\n");
				writer.write(savingArray[i + 1] + "\n");
				writer.write(savingArray[i + 2] + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/////////////////////////////////////////////// PRINTING
	// Print out the element in a node
	private void processNode(BinaryNode nody) {
		System.out.println(nody.getElement().toString() + "  ");
	}

	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////// NOT USED FOR CONTACTBOOK:

	// -----------------Search
	// the publicly accessible search method, input parameter is the comparable
	// object, and it returns whether the tree contains it
	public boolean search(Comparable object) {
		return searchThrough(root, object);
	}

	// Recursively search the nodes based on results from compareTo, returns
	// true if tree contains given element
	private boolean searchThrough(BinaryNode nody, Comparable element) {
		if (nody != null) {
			if (nody.getElement().compareTo(element) == 0) {
				return true;
			} else if (nody.getElement().compareTo(element) == 1) {
				return searchThrough(nody.getLeftChild(), element);
			} else {
				return searchThrough(nody.getRightChild(), element);
			}
		}
		return false;
	}

	// -----------------Extra traversals
	// pre-order traversal
	private void preOrderTraverse(BinaryNode nody) {
		processNode(nody);
		if (nody.getLeftChild() != null) {
			inOrderTraverse(nody.getLeftChild());
		}
		if (nody.getRightChild() != null) {
			inOrderTraverse(nody.getRightChild());
		}
	}

	// post-order traversal
	private void postOrderTraverse(BinaryNode nody) {
		if (nody.getLeftChild() != null) {
			inOrderTraverse(nody.getLeftChild());
		}
		if (nody.getRightChild() != null) {
			inOrderTraverse(nody.getRightChild());
		}
		processNode(nody);
	}

	// -----------------Check Balance

	// check whether tree is balanced using the getHeight method
	public boolean treeBalanced() {
		if (root == null) {
			return true;
		}
		if (getHeight(root) == -1) {
			return false;
		}
		return true;
	}

	// Recursively finds the height of the tree, useful for treeBalanced.
	// Returns -1 if tree unbalanced
	private int getHeight(BinaryNode nody) {
		if (nody == null) {
			return 0;
		}
		int left = getHeight(nody.getLeftChild());
		int right = getHeight(nody.getRightChild());

		if (left == -1 || right == -1) {
			return -1;
		}
		if (Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

	// ------------Balance the tree

	// The following method uses other methods below to make a sorted arraylist
	// out of the binary tree and then remake it into a balanced tree
	public void balanceTree() {
		balancingList = new ArrayList<Comparable>();
		if (root != null) {
			makeSortedListFromTree(root);
			root = makeBalancedBSTFromList(0, balancingList.size() - 1);
			root.setParent(null);
			setParents(root);
		}
	}

	// Traverse the nodes in order and add each element to the arraylist
	// balancingList
	private void makeSortedListFromTree(BinaryNode nody) {
		if (nody.getLeftChild() != null) {
			makeSortedListFromTree(nody.getLeftChild());
		}
		balancingList.add(nody.getElement());
		if (nody.getRightChild() != null) {
			makeSortedListFromTree(nody.getRightChild());
		}
	}

	// The following recursive method makes a new balanced binarytree from the
	// balancingList, and returns the root node
	private BinaryNode makeBalancedBSTFromList(int startingIndex, int endingIndex) {
		if (startingIndex > endingIndex) {
			return null;
		} else {
			int middleIndex = startingIndex + (endingIndex - startingIndex) / 2;
			BinaryNode nody = new BinaryNode(balancingList.get(middleIndex));
			nody.setLeftChild(makeBalancedBSTFromList(startingIndex, middleIndex - 1));
			// nody.getLeftChild().setParent(nody);
			nody.setRightChild(makeBalancedBSTFromList(middleIndex + 1, endingIndex));
			// nody.getRightChild().setParent(nody);
			return nody;
		}
	}

	// This sets the parents of nodes of a newly balanced binary search tree
	private void setParents(BinaryNode nody) {
		if (nody.getLeftChild() != null) {
			nody.getLeftChild().setParent(nody);
			setParents(nody.getLeftChild());
		}
		if (nody.getRightChild() != null) {
			nody.getRightChild().setParent(nody);
			setParents(nody.getRightChild());
		}
	}
}
