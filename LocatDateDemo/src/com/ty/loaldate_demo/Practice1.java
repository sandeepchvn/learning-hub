package com.ty.loaldate_demo;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Practice1 {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		System.out.println(date);// 2023-12-14

		LocalDate date2 = LocalDate.of(2024, 12, 28);
		System.out.println(date2);// 2024-12-28

		Month month = date.getMonth();
		System.out.println(month);// DECEMBER
		System.out.println(month.getValue());// 12

		int year = date.getYear();
		System.out.println(year);//2023
		
		int i=date.getDayOfMonth();
		System.out.println(i);//14
		
		//ou can format a LocalDate instance into a string and parse a string 
		String sDate=date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		System.out.println(sDate);
		String sDate2=date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(sDate2);
		
		//string into a LocalDate
		LocalDate parsedDate = LocalDate.parse("2040-08-28");
		System.out.println(parsedDate);
	}
}
