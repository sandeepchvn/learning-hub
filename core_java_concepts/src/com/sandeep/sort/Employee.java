package com.sandeep.sort;

public class Employee implements Comparable<Employee>{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(Employee o) {
		return Integer.compare(this.id, o.id);
		//or
//		if(this.id>o.id)
//			return 3;
//		else if(this.id<o.id)
//			return -5;
//		return 0;
	}
	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
}
