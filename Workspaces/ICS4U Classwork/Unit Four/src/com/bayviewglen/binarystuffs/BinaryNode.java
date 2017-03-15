package com.bayviewglen.binarystuffs;
/*
THIS IS A NODE IN THE TREE
It contains right and left children, a parent, and a comparable element
*/

public class BinaryNode {

	private BinaryNode rightChild;
	private BinaryNode leftChild;
	private BinaryNode parent;
	private Comparable element;

	//constructor takes in the element of the node
	BinaryNode(Comparable newElement) {
		element = newElement;
	}

	//getter for right child
	public BinaryNode getRightChild() {
		return rightChild;
	}

	//setter for right child
	public void setRightChild(BinaryNode rightChild) {
		this.rightChild = rightChild;
	}

	//getter for left child
	public BinaryNode getLeftChild() {
		return leftChild;
	}

	//setter for left child
	public void setLeftChild(BinaryNode leftChild) {
		this.leftChild = leftChild;
	}
	
	//getter for parent
	public BinaryNode getParent() {
		return parent;
	}

	//setter for parent
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

	//getter for element
	public Comparable getElement() {
		return element;
	}
	
	//setter for element
	public void setElement(Comparable element){
		this.element = element;
	}

}
