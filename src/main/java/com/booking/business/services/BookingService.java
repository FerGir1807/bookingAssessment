package com.booking.business.services;

import java.util.List;

import com.booking.domain.model.BookingModel;
import com.booking.domain.model.FilterModel;
import com.booking.persistance.BookingLocalRepository;
import com.booking.persistance.interfaces.Booking;

public class BookingService {

	public Booking bookingRepository = new BookingLocalRepository();
	// public BookingInterface bookingRepository = new BookingTestRepository();

	/**
	 * Method that add a booking to the repository
	 * 
	 * @param BookingModel
	 * @return BookingModel
	 * @throws Exception
	 */
	public BookingModel addBooking(BookingModel booking) throws Exception {
		return bookingRepository.addBooking(booking);
	}

	/**
	 * Method that retrieves all Bookings
	 * 
	 * @return List<BookingModel>
	 */
	public List<BookingModel> getAllBookings() {
		return bookingRepository.getBookingList();
	}

	/**
	 * Method that retrieves all bookings filtered by date
	 * 
	 * @param filter
	 * @return List<BookingModel>
	 */
	public List<BookingModel> getAllBookingsByDate(FilterModel filter) {
		return bookingRepository.getBookingListByDate(filter);
	}
}
