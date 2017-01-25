package com.bayviewglen.algorithms;

import java.util.Scanner;

public class QuestOne {
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * Essentially how this program works is that it goes through the array
		 * of numbers, and when the difference between numbers changes sign it
		 * means that the maximum length of the biggest zigzag sequence gets one
		 * bigger. Each subproblem is the numbers in the array from the
		 * beginning to index i, which is a counter variable that increases with
		 * every iteration of a loop. The answer from the previous subproblem is
		 * used to calculate the solution to the current subproblem, by adding
		 * one to it if the last difference in the current subproblem has the
		 * opposite sign of the last difference in the previous subproblem.
		 */

		// Ask the user to enter a sequence of numbers and use a for loop to
		// create an int array with these numbers
		System.out.println("Please enter a zig zag sequence with spaces in between the numbers.");
		String[] arryString = (keyboard.nextLine()).split(" ");
		int[] zigzag = new int[arryString.length];
		for (int i = 0; i < arryString.length; i++) {
			zigzag[i] = Integer.parseInt(arryString[i]);
		}

		if (zigzag.length == 1) {
			//In case there is only one number the answer is one
			System.out.println("The longest zigzag sequence has a length of: 1");
		} else {
			//If there is more than one number...
			/*
			 * This variable, previous, stores the previous difference between
			 * values. true means it is positive, and false means it is less
			 * than or equal to 0 Here we set previous to the opposite of the
			 * first difference in the sequence. This makes the algorithm count
			 * the second index of solutions as a solution, even though there
			 * was no prior difference
			 */

			boolean previous = !(zigzag[1] - zigzag[0] > 0);
			/*
			 * This int array is the array that holds the solutions to previous
			 * subproblems, each index in this array corresponding to an index
			 * of the inputted array of numbers
			 */
			int[] zigzagSolutions = new int[zigzag.length];

			// Setting the first value of the solutions array to 1, since
			// technically the first value would count as part of a zigzag
			// sequence
			zigzagSolutions[0] = 1;

			// This is the dynamic part of the program, which fills in the
			// solutions
			// array with the answers to each subproblem, up to the last one
			for (int i = 1; i < zigzag.length; i++) {
				/*
				 * This for loop starts at 1 and goes through each index of the
				 * user-inputted array of numbers. Each iteration is one
				 * subproblem.
				 */
				if (zigzag[i] - zigzag[i - 1] != 0) {
					// Ensure that the difference between the value of zigzag at
					// this index and the one before is nonzero
					if ((zigzag[i] - zigzag[i - 1] > 0) != previous
							|| (zigzag[1] - zigzag[0] == 0 && zigzagSolutions[i - 1] == 1)) {
						/*
						 * **IF** the difference between this index of solutions
						 * and the previous are opposite (one positive and the
						 * other negative) **OR** this is the first number in
						 * zigzag array that has a nonzero difference before it
						 * (Checking for this case avoids the mess-up that
						 * occurs when zigzag starts with consecutive numbers
						 * that are the same and the first previous difference
						 * is initialized as 0) **THEN** Make the solution to
						 * this subproblem one greater than the subproblem
						 * before it.
						 */
						zigzagSolutions[i] = zigzagSolutions[i - 1] + 1;
					} else {
						/*
						 * If the difference is the same sign as before then
						 * this doesn't increase the solution, therefore the
						 * solution for this subproblem is the same as for the
						 * last.
						 */
						zigzagSolutions[i] = zigzagSolutions[i - 1];
					}
					// Set this difference as the previous, for the next
					// iteration
					previous = zigzag[i] - zigzag[i - 1] > 0;
				} else {
					/*
					 * If the difference between the value of zigzag at this
					 * index and the one before is zero then the solution to
					 * this subproblem is the same as the previous one. Also, we
					 * don't change the previous difference because having a 0
					 * difference messes things up and we can ignore it.
					 */
					zigzagSolutions[i] = zigzagSolutions[i - 1];
				}
			}

			// Print out the solution, the number held in the last index of the
			// solutions array.
			System.out.println(
					"The longest zigzag sequence has a length of: " + zigzagSolutions[zigzagSolutions.length - 1]);
		}
	}

}
