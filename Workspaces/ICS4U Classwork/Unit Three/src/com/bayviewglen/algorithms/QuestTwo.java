package com.bayviewglen.algorithms;

import java.util.Scanner;

public class QuestTwo {
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * Ask the user to enter a sequence of donations and use a for loop to
		 * create 2D int array with these numbers in the first index of each row
		 * of the array. The reason that a 2D array is used is to simplify the
		 * dynamic programming. It has dimension 5*the number of donations. The
		 * first column of the array holds the donations, the second 2 hold
		 * dynamic programming solutions using the first donation and not the
		 * last, and the last 2 columns hold dynamic programming solutions using
		 * the last donation but not the first.
		 */
		System.out.println("Please enter a donations sequence with spaces in between the numbers.");
		String[] arryString = (keyboard.nextLine()).split(" ");
		int[][] donations = new int[arryString.length][5];
		for (int i = 0; i < arryString.length; i++) {
			donations[i][0] = Integer.parseInt(arryString[i]);
		}

		// If the length of the donations array is 1 then just print out the
		// value of the donation as the max amount, otherwise perform the
		// dynamic programming
		if (donations.length == 1) {
			System.out.println("The biggest possible donation is: $" +donations[0][0]);
		} else {
			for (int i = 0; i < donations.length; i++) {
				/*
				 * This is the main loop of the dynamic programming part of
				 * this. Each time it iterates it solves 2 subproblems, one of
				 * which is comprised of the donations in the array up to i, and
				 * the other comprised of the donations up to i but not
				 * including 0 (Which allows us to compare the maximum sums with
				 * the first donation and not the last, or with the last
				 * donation but not the first). For each subproblem, it finds 2
				 * possible maximum solutions, which are stored separately for
				 * each subproblem in the array. One of these maximums is using
				 * the current donation at i, and one of these is not using it.
				 * 
				 * donations[i][0] stores the original donation offering at i;
				 * donations[i][1] stores the maximums including the donation at
				 * i for the subproblems that analyze the first donation;
				 * donations[i][2] stores the maximum NOT including the donation
				 * at i for the subproblems that analyze the first donation;
				 * donations[i][3] stores the maximums including the donation at
				 * i for the subproblems that do NOT analyze the first donation;
				 * donations[i][4] stores the maximums NOT including the
				 * donation at i for the subproblems that do NOT analyze the
				 * first donation;
				 */
				if (i == 0) {
					/*
					 * If this is the first subproblem then we make
					 * donations[i][0] equal to the donation offered at i,
					 * because that would be the maximum possible sum using that
					 * house. We also set donations[i][2] to 0 because that
					 * would be the maximum possible sum without using the
					 * donation offered at i
					 */
					donations[i][1] = donations[i][0];
					donations[i][2] = 0;
				} else {// Any iteration after the first comes here
					/*
					 * Here to find the maximum sum for the subproblem using the
					 * donation offered at i, we take the maximum sum without
					 * using the previous donation (the previous subproblem) and
					 * add to that the donation offered at i. Then, to find the
					 * maximum sum without using the donation at i, we just take
					 * the bigger of the two solutions to the previous
					 * subproblem (with or without using the donation at i-1).
					 * 
					 * These 2 solutions are saved for each of the 2 subproblems
					 * (the ones that do and don't analyze i=0), filling the 4
					 * indexes available to be filled for each i.
					 * 
					 */
					donations[i][1] = donations[i - 1][2] + donations[i][0];
					donations[i][2] = java.lang.Math.max(donations[i - 1][1], donations[i - 1][2]);
					donations[i][3] = donations[i - 1][4] + donations[i][0];
					donations[i][4] = java.lang.Math.max(donations[i - 1][3], donations[i - 1][4]);
				}
			}
			/*
			 * Here we choose which of the maximum sums will give us the maximum
			 * possible donation value. The possible max sums include:
			 * 
			 * The sub problems that use the first donation but not the last:
			 * donations[length - 2][1] which would use the last value in the
			 * subproblem and donations[length - 2][2], which would NOT use the
			 * last value in the subproblem
			 * 
			 * The sub problems that use the last donation but not the first:
			 * donations[length - 1][3] which would use the last value in the
			 * subproblem and donations[length - 1][4], which would NOT use the
			 * last value in the subproblem
			 */
			int length = donations.length;
			int biggestAmount = java.lang.Math.max(
					java.lang.Math.max(donations[length - 2][1], donations[length - 2][2]),
					java.lang.Math.max(donations[length - 1][3], donations[length - 1][4]));

			// The maximum sum out of the 4 above mentioned options is then
			// printed as the final maximum donation sum answer
			System.out.println("The biggest possible donation is: $" + biggestAmount);
		}
	}
}
