package com.ty;

import java.util.Objects;

public class Student implements Cloneable {

	String name;
	int id;

	@Override
	public String toString() {
		return name;
	}

	public boolean equals(Object o) {
		Student s = (Student) o;
		if (s.name == this.name)
			return true;
		else
			return false;
	}

	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	protected void finalize() {
		// Finalize method overridden to print a message when the object is garbage
		// collected
		System.out.println("Object is garbage collected!");
	}

	public static void main(String[] args) {
		Student s = new Student();
		s.id = 4;
		s.name = "sandy";
		Student s2 = new Student();
		s2.id = 4;
		s2.name = "sandy";
		System.out.println(s.getClass());
		System.out.println(s == s2);
		System.out.println(s.equals(s2));
		System.out.println(s.hashCode());
		System.out.println(s2.hashCode());
		Student s6 = new Student();
		s6 = null;
		System.gc();
	}
}
