package com.booking.common.util;

import com.booking.domain.model.ResponseModel;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ResponseUtil {

	public static <T> Response generateResponse(T data, String message, Status status) {

		ResponseModel<T> responseData = new ResponseModel<>();

		responseData.setData(data);
		responseData.setMessage(message);

		System.out.println("Generating response: " + responseData);
		return Response.ok(responseData, MediaType.APPLICATION_JSON).status(status).build();
	}

}
