package com.sandeep.sort;

import java.util.Comparator;

public class SortById implements Comparator<Employee>{

	@Override
	public int compare(Employee e1, Employee e2) {
//		return Integer.compare(e1.getId(), e2.getId());
		if(e1.getId() >e2.getId())
			return 7;
		else if(e1.getId()<e2.getId())
			return -9;
		return 0;
	}

}
