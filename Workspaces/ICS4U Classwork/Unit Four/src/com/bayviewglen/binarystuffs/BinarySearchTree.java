package com.bayviewglen.binarystuffs;

public class BinarySearchTree {

		//This is the root (top) node of the tree
		private BinaryNode root = new BinaryNode();

		//Add a node to the tree given a value to be in the new node
		//Nody should be the root of the tree
		public void insert (int numberAdd, BinaryNode nody){
			if (!nody.hasElement()){
				nody.setElement(numberAdd);
				return;
			}
			if (numberAdd <= nody.getElement()){
				if (nody.getLeftChild() != null){
					insert(numberAdd, nody.getLeftChild());
				} else {
					nody.setLeftChild(new BinaryNode());
					nody.getLeftChild().setElement(numberAdd);
				}
			} else {
				if (nody.getRightChild() !=null){
					insert(numberAdd, nody.getRightChild());
				} else {
					nody.setRightChild(new BinaryNode());
					nody.getRightChild().setElement(numberAdd);
				}
			}
		}

		//Show the nodes in preOrder
		public void preOrder(BinaryNode nody){
			processNode(nody);
			if (nody.getLeftChild()!=null){
				preOrder(nody.getLeftChild());
			}
			if (nody.getLeftChild()!=null){
				preOrder(nody.getRightChild());
			}
		}
		
		//Show the nodes in inOrder
		public void inOrder(BinaryNode nody){
			if (nody.getLeftChild()!=null){
				inOrder(nody.getLeftChild());
			}
			processNode(nody);
			if (nody.getLeftChild()!=null){
				inOrder(nody.getRightChild());
			}
		}
		
		//Show the nodes in postOrder
		public void postOrder(BinaryNode nody){
			if (nody.getLeftChild() != null){
				postOrder(nody.getLeftChild());
			} 
			if (nody.getRightChild() != null){
				postOrder(nody.getRightChild());
			}
			processNode(nody);
		}
		
		//Print out the value in a node
		private void processNode(BinaryNode nody){
			System.out.println(nody.getElement() + "  ");
		}
		
		//return the root (top element) of the tree
		public BinaryNode getRoot(){
			return root;
		}

}
