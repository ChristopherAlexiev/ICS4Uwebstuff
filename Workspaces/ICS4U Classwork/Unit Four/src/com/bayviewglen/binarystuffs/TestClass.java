package com.bayviewglen.binarystuffs;

public class TestClass {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(5, tree.getRoot());
		tree.insert(10, tree.getRoot());
		tree.insert(17, tree.getRoot());
		tree.postOrder(tree.getRoot());
	}

}
