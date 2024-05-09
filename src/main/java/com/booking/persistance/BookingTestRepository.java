package com.booking.persistance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.domain.model.BookingModel;
import com.booking.domain.model.FilterModel;
import com.booking.persistance.interfaces.Booking;

public class BookingTestRepository implements Booking {

	private static List<BookingModel> bookingList = new ArrayList<BookingModel>();

	public List<BookingModel> getBookingList() {
		System.out.println("getBookingList from BookingTestRepository");
		return bookingList;
	}

	public List<BookingModel> getBookingListByDate(FilterModel filter) {
		System.out.println("getBookingListByDay from BookingTestRepository");
		LocalDate matchDate = filter.getDate();

		List<BookingModel> filteredBookingList = bookingList.stream()
				.filter(x -> x.getDateTime().toLocalDate().isEqual(matchDate)).collect(Collectors.toList());

		return filteredBookingList;

	}

	public BookingModel addBooking(BookingModel booking) {
		System.out.println("addBooking from BookingTestRepository");
		booking.setBookingId(bookingList.size() + 1);
		bookingList.add(booking);

		return booking;

	}

}
