package com.booking.domain.model;

public class ResponseModel<T> {

	private String message;
	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GlobalResponse [message=" + message + ", data=" + data + "]";
	}
}
