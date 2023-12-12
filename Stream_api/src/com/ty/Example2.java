package com.ty;

import java.util.Arrays;

public class Example2 {

	public static void main(String[] args) {
		String[] names= {"ravana","rama","seeta","laxmana","maruthi"};
		
		Arrays.stream(names).filter(s->s.length()>=5).forEach(a->System.out.println(a));
		//0r
		Arrays.stream(names).filter(s->s.length()>=5).forEach(System.out::println);
	}
}
