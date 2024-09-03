package com.sandeep.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		List<Employee> list=new ArrayList<Employee>();
		list.add(new Employee(3,"xyz"));
		list.add(new Employee(1,"abc"));
		list.add(new Employee(10,"sss"));
		list.add(new Employee(2,"ddd"));
		Collections.sort(list,new SortByName());
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
}
