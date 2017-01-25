package com.bayviewglen.binarystuffs;
/*
THIS IS A NODE IN THE TREE
It contains right and left children, as well as an int element
*/

public class BinaryNode {
	
	private BinaryNode rightChild;
	private BinaryNode leftChild;
	private int element;
	private boolean hasElement = false;

	
	BinaryNode (){
	}
	
	public boolean hasElement(){
		return hasElement;
	}
	
	public BinaryNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryNode rightChild) {
		this.rightChild = rightChild;
	}
	public BinaryNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinaryNode leftChild) {
		this.leftChild = leftChild;
	}
	public int getElement() {
		return element;
	}
	public void setElement(int element) {
		this.element = element;
		hasElement = true;
	}
}
