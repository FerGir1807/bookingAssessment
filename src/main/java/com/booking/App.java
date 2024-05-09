package com.booking;

import com.booking.presentation.controller.BookingController;
import com.booking.presentation.exception.ExceptionHandler;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;

public class App {
	public static void main(String[] args) {

		JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
		MuServer server = MuServerBuilder.httpServer()
				.addHandler(RestHandlerBuilder.restHandler(new BookingController()).addCustomWriter(jacksonJsonProvider)
						.addCustomReader(jacksonJsonProvider)
						.addExceptionMapper(Exception.class, new ExceptionHandler()))

				.withHttpPort(53255).start();
		System.out.println("Started server at " + server.uri());
	}
}
