package com.booking.presentation.controller;

import com.booking.business.services.BookingService;
import com.booking.common.constant.MessageConstant;
import com.booking.common.util.ResponseUtil;
import com.booking.domain.model.BookingModel;
import com.booking.domain.model.FilterModel;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/booking")

public class BookingController {

	private BookingService bookingService = new BookingService();

	public BookingController() {
	}

	@GET
	@Produces("application/json")
	public Response getAllBookings() {
		System.out.println("Executing getAllBookings service");
		return ResponseUtil.generateResponse(bookingService.getAllBookings(), MessageConstant.RESPONSE_OK, Status.OK);
	}

	@POST
	@Path("/filter")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAllBookingsByDate(FilterModel filter) {
		System.out.println("Executing getAllBookingsByDate service");
		return ResponseUtil.generateResponse(bookingService.getAllBookingsByDate(filter), MessageConstant.RESPONSE_OK,
				Status.OK);
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addBooking(BookingModel booking) throws Exception {
		System.out.println("Executing addBooking service");
		return ResponseUtil.generateResponse(bookingService.addBooking(booking), MessageConstant.RESPONSE_OK,
				Status.CREATED);
	}
}
