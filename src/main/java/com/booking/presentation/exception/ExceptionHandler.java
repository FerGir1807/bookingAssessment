package com.booking.presentation.exception;

import com.booking.common.util.ResponseUtil;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		System.out.println("Generating exception response: ");
		return ResponseUtil.generateResponse(null, exception.getMessage(), Status.BAD_REQUEST);
	}

}