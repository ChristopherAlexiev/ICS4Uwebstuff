package com.bayviewglen.binarystuffs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactBook

{

	// Create a new binary search tree
	private static BinarySearchTree tree = new BinarySearchTree();
	private static Scanner scanner = new Scanner(System.in);

	// This is the address book user-interface structure
	public static void main(String[] args) {

		boolean inGame = true;

		// occupy the binary tree with contacts saved on the file
		contactsFromFile();

		System.out.println("Welcome to ADDRESS BOOK WITH BINARY SEARCH TREE.");

		while (inGame) {
			System.out.println("Here is your menu, enter the number to choose an option.");
			System.out.println(
					" 1) Add a contact \n 2) Display all contacts \n 3) Search for or delete a contact \n 4) Save address book");
			int input = inputer();
			if (input == 1) {
				// add a contact
				addContact();
			} else if (input == 2) {
				// print out all contacts
				System.out.println("Here are all contacts:");
				tree.print();
			} else if (input == 3) {
				// search for contacts and give the option to delete them as
				// well
				System.out.println(
						"Please enter part of a first name, part of a last name, or part of a phone number to search for contacts:");
				String checkName = scanner.nextLine();
				showContact(checkName);
			} else if (input == 4) {
				// saves the tree onto the file
				tree.saveTree();
				System.out.println("Address book saved.");
			}

			System.out.println("///////////////////////////////////////////////");
			System.out.println("Thank you for using ADDRESS BOOK WITH BST.");
			System.out.println("///////////////////////////////////////////////");
		}

	}

	// This adds a contact to the addressbook
	public static void addContact() {
		System.out.println("Please enter first name.");
		String first = scanner.nextLine();
		System.out.println("Please enter last name.");
		String last = scanner.nextLine();
		System.out.println("Please enter phone number.");
		String phone = scanner.nextLine();
		// calling the insert method on the tree
		tree.insert(new Contact(first, last, phone));
		System.out.println("Congratulations!!!! You just added a contact.");
	}

	// shows all the contacts containing a given first name, last name, or phone
	// with the input parameter substring, and then run the
	// deleteContact function
	public static void showContact(String checkName) {
		System.out.println("Here is a list of contacts containing \"" + checkName + "\":");
		if (tree.findContact(new Contact("", "", checkName)) == true) {
			deleteContact();
		} else {
			System.out.println("Sorry, there is no contact containing the inputted string of text.");
		}
	}

	// deletes a contact given its phone number
	private static void deleteContact() {
		System.out.println("Would you like to delete one of the above contacts? (Y or N)");
		while (true) {
			String input = scanner.nextLine();
			if (input.equals("Y") || input.equals("y")) {
				System.out.println(
						"Delete which one? Please enter the contact's phone number. If you want to cancel, enter cancel.");
				String inputTwo = scanner.nextLine();
				if (inputTwo == "cancel") {
					System.out.println("No problem, have a nice day.");
					break;
				}
				// call the delete function in the tree, with a Contact
				// containing the right phone number
				tree.delete(new Contact("", "", inputTwo));
				System.out.println("Ok, you have deleted the contact with that phone number, if it existed.");
				break;
			} else if (input.equals("N") || input.equals("n")) {
				System.out.println("No problem, have a nice day.");
				break;
			} else {
				System.out.println("Please enter a valid response (Y or N)");
			}
		}
	}

	// This returns the value typed into the console, forcing the user to type
	// an integer from 1 to 4 inclusive
	private static int inputer() {
		boolean bad = true;
		int choice = 0;
		while (bad) {
			System.out.println("Please enter a valid choice:");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
			}
			if (choice >= 1 && choice <= 4) {
				bad = false;
			}
		}
		return choice;
	}

	// gets the contacts from the file and adds them to the binary search tree
	static void contactsFromFile() {
		try {
			Scanner fileScan = new Scanner(new File("input/contacts.dat"));
			String fname;
			String phone;
			String lname;
			while (true) {
				try {
					fname = fileScan.nextLine();
					lname = fileScan.nextLine();
					phone = fileScan.nextLine();
				} catch (NoSuchElementException e) {
					break;
				}
				tree.insert(new Contact(fname, lname, phone));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
