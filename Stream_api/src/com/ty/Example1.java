package com.ty;

import java.util.Arrays;

public class Example1 {

	public static void main(String[] args) {
		int[] n= {2,4,1,5};
		System.out.println();
	
		Arrays.stream(n).forEach(a->System.out.println(a));
		//or
		
		
		String[] s= {"sandeep","chavan","rathod"};
		Arrays.stream(s).forEach(System.out::println);
	}
}
