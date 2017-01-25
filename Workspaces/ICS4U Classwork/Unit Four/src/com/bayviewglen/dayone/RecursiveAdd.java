package com.bayviewglen.dayone;

public class RecursiveAdd {
static int[] solutions;
	public static void main(String[] args) {
		System.out.println(solve(5));

	}


	private static int solve(int n){
		int[] solutions = new int[n+1];
		int i;
		solutions[1]=0;
		for (i =2;i<=n;i++){
			solutions[i] = 1+ solutions[i-1];
			if (i%2 == 0){
				solutions[i] = Math.min(solutions[i], 1+solutions[i/2]);
			}
			if (i%3 == 0){
				solutions[i] = Math.min(solutions[i], 1+solutions[i/3]);
			}
		}
		return solutions[n];
	}
}
