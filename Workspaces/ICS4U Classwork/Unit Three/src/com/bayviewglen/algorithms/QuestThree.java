package com.bayviewglen.algorithms;

import java.util.Scanner;

public class QuestThree {
	static Scanner keyboard = new Scanner(System.in);	 // test

	public static void main(String[] args) {
		/*
		 * There is a 2D array that holds the height sequence on one row, the
		 * bloom sequence on one row, and the wilt sequence on the last row
		 * 
		 * In the following section of code the user enters the height, bloom,
		 * and wilt sequences which are then stored in a properly sized 2D array
		 * using for loops
		 */
		System.out.println("Please enter a height sequence with spaces in between the numbers.");
		String[] arryString = (keyboard.nextLine()).split(" ");
		int[][] flowers = new int[3][arryString.length];
		for (int i = 0; i < arryString.length; i++) {
			flowers[0][i] = Integer.parseInt(arryString[i]);
		}
		System.out.println("Please enter a bloom sequence with spaces in between the numbers.");
		arryString = (keyboard.nextLine()).split(" ");
		for (int i = 0; i < arryString.length; i++) {
			flowers[1][i] = Integer.parseInt(arryString[i]);
		}
		System.out.println("Please enter a wilt sequence with spaces in between the numbers.");
		arryString = (keyboard.nextLine()).split(" ");
		for (int i = 0; i < arryString.length; i++) {
			flowers[2][i] = Integer.parseInt(arryString[i]);
		}

		/*
		 * Essentially how this program works is it first sorts the flowers by
		 * their height, from tallest to shortest. Then, comes the dynamic part.
		 * A 2D array is created, which has length and width of the number of
		 * flowers. This extra array is called indexesArray, and it holds the
		 * solutions to each subproblem, as an order of flowers stored on one
		 * row of the array. Each subproblem is the flowers from the first index
		 * in the sorted flower array, to an index which increases by one in
		 * each iteration. For example, subproblem 1 would be the tallest
		 * flower, and subproblem 2 would be the tallest and second tallest, and
		 * so on.
		 * 
		 * To find the solutions in indexesArray, the program looks at the
		 * previous subproblem solution, and inserts the new extra flower in at
		 * the appropriate place, bringing the rest of the previous row down
		 * into the current subproblem's solution row. The appropriate place
		 * would be in front of the first flower it meets that it has an
		 * overlapping bloom/wilt time period with, to make sure that the new
		 * flower has access to sunlight (It is important to remember that
		 * timing is the only constraint because the flowers are already sorted
		 * by height. Therefore, the flower currently being added to the
		 * solution is shorter than all the other flowers already there, so no
		 * matter where it is put it will not block sunlight from any of the
		 * flowers already in the solution).
		 */

		/*
		 * Insertion sort is used here to sort the flowers array by height from
		 * tallest to shortest. When a flower is moved, all its indexes are
		 * moved to the new row, including height, bloom, and wilt
		 */
		for (int i = 1; i < flowers[0].length; i++) {
			if (flowers[0][i] > flowers[0][i - 1]) {
				int j = i;
				while (j > 0 && flowers[0][j] > flowers[0][j - 1]) {
					int tempHeight = flowers[0][j];
					int tempBloom = flowers[1][j];
					int tempWilt = flowers[2][j];
					flowers[0][j] = flowers[0][j - 1];
					flowers[1][j] = flowers[1][j - 1];
					flowers[2][j] = flowers[2][j - 1];
					flowers[0][j - 1] = tempHeight;
					flowers[1][j - 1] = tempBloom;
					flowers[2][j - 1] = tempWilt;
					j--;
				}
			}
		}

		/*
		 * This is the array that will be used to do the "dynamic" part of the
		 * programming, it will store the order of indexes of the sorted flower
		 * array that make up a valid solution to each subproblem, each
		 * subproblem being on a different row of the array
		 * 
		 * **Note that it stores indexes of the array flowers, each index
		 * representing one flower
		 */
		int[][] indexesArray = new int[flowers[0].length][flowers[0].length];

		// Set the first row and column of indexesArray to 0, the first index of
		// the flowers array
		indexesArray[0][0] = 0;

		/*
		 * Below is the for loop that performs the dynamic algorithm. Each
		 * iteration through it is one subproblem, each subproblem being the
		 * indexes of flowers array from 0 through i.
		 */
		for (int i = 1; i < flowers[0].length; i++) {
			// The variable j counts the index of the row of indexesArray
			int j = 0;
			for (j = 0; j <= i; j++) {
				/*
				 * This loop creates the solution of the subproblem, going
				 * through and using the flowers from the previous subproblem at
				 * i-1 until the new flower for the current subproblem is
				 * inserted into an appropriate spot based on bloom dates
				 */
				if (j == i) {
					/*
					 * If we have reached the last index of the subproblem, when
					 * i=j, then it means that the new flower can go after all
					 * the other flowers in the subproblem, so we insert it into
					 * the solution at indexesArray[i][j]. Then we break from
					 * the loop. This case is checked first to ensure that we
					 * don't try to compare bloom dates of a non-existent flower
					 * at indexesArray[i-1][j]
					 */
					indexesArray[i][j] = i;
					break;
				}

				// This variable holds the flowers array index of the flower at
				// the previous subproblem at this same j
				int checkOne = indexesArray[i - 1][j];

				if (flowers[1][checkOne] > flowers[2][i] || flowers[1][i] > flowers[2][checkOne]) {
					/*
					 * if the new flower for this subproblem does NOT have an
					 * overlapping bloom time-frame with the flower at this j
					 * for the previous subproblem, then the new flower can go
					 * behind it in the garden. Therefore, we can add that
					 * flower that existed in the previous subproblem at j to
					 * indexesArray[i][j], making it a part of the new solution.
					 * We have not yet inserted the new flower into the solution
					 */
					indexesArray[i][j] = indexesArray[i - 1][j];
				} else {
					/*
					 * if the new flower for this subproblem does have an
					 * overlapping bloom time-frame with the flower at this j
					 * for the previous subproblem, then the new flower must go
					 * in front of it in the garden to get sunlight. Therefore,
					 * we make the new flower a part of this subproblem's
					 * solution by setting indexesArray[i][j] = i, because i can
					 * be considered to be the index of array flowers that holds
					 * the new flower.
					 * 
					 * Afterwards, we break from the loop and the program moves
					 * on to finish the subproblem by moving the remaining
					 * flowers in the previous subproblem down to this
					 * subproblem's row
					 */
					indexesArray[i][j] = i;
					break;
				}
			}
			for (int k = j + 1; k <= i; k++) {
				/*
				 * This loop finishes solving the current subproblem, now
				 * containing the new flower at the right spot, by moving the
				 * remaining flowers in the previous subproblem down to this
				 * subproblem's row
				 */
				indexesArray[i][k] = indexesArray[i - 1][k - 1];
			}
		}

		/*
		 * The final answer is the flowers listed in order in the last row of
		 * indexesArray
		 * 
		 * The heights of these flowers are printed in the proper garden order,
		 * using the indexes of array flowers listed in the last row of array
		 * indexesArray
		 */
		System.out.println();
		System.out.println("The required order for the garden is: ");
		for (int i : indexesArray[indexesArray.length - 1]) {
			System.out.print(flowers[0][i] + " ");
		}
	}
}
