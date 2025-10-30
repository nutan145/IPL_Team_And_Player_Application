package com.nt.advice;

public class TeamNotFoundException extends Exception{
	
	public TeamNotFoundException(String msg) {
		super(msg);
	}
	
	public TeamNotFoundException() {
		super();
	}

}
