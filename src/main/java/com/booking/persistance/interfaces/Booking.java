package com.booking.persistance.interfaces;

import java.util.List;

import com.booking.domain.model.BookingModel;
import com.booking.domain.model.FilterModel;

public interface Booking {

	public List<BookingModel> getBookingList();

	public List<BookingModel> getBookingListByDate(FilterModel filter);

	public BookingModel addBooking(BookingModel booking) throws Exception;
}
