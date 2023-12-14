package com.ty;

import java.util.Arrays;

public class Example3 {
	public static void main(String[] args) {
		String[] str={"tyuikjh","fyuje","fgh","kjhgd","rds"};
		Arrays.stream(str).map(s->s.toUpperCase()).forEach(System.out::println);
		String[] s=Arrays.stream(str).map(u->u.toUpperCase()).toArray(String[]::new);
		Arrays.stream(s).forEach(System.out::println);
	}
}
