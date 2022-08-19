package com.my.exception;

public class AddException extends Exception {

	public AddException() {
		super();
	}

	public AddException(String message) {     //->저장소가 꽉찼습니다.~~~   
		super(message);                       //-> extends Exception
	}
}