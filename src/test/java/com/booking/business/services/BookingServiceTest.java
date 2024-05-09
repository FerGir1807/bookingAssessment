package com.booking.business.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.booking.common.constant.MessageConstant;
import com.booking.domain.model.BookingModel;
import com.booking.domain.model.FilterModel;
import com.booking.persistance.BookingLocalRepository;
import com.booking.persistance.interfaces.Booking;

public class BookingServiceTest {

	private Booking bookingRepository;

	@Before
	public void setUp() throws Exception {
		bookingRepository = new BookingLocalRepository();
	}

	@After
	public void clear() {
		bookingRepository.getBookingList().clear();
	}

	@Test
	public void addFirstBookingCorrectly() {

		try {
			BookingModel booking = new BookingModel();

			booking.setEmail("f_gir_07@hotmail.com");
			booking.setCustomerName("Fernando");
			booking.setTableSize(2);
			booking.setDateTime(LocalDateTime.of(2024, 05, 07, 10, 0));

			BookingModel response = bookingRepository.addBooking(booking);

			assertNotNull(response);
			assertEquals(1, bookingRepository.getBookingList().size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addBookingBetweenOtherBookingsCorrectly() {

		try {
			BookingModel booking1 = new BookingModel();

			booking1.setEmail("f_gir_07@hotmail.com");
			booking1.setCustomerName("Fernando");
			booking1.setTableSize(2);
			booking1.setDateTime(LocalDateTime.of(2024, 05, 07, 10, 0));

			BookingModel booking2 = new BookingModel();

			booking2.setEmail("f_gir_07@hotmail.comm");
			booking2.setCustomerName("Roberto");
			booking2.setTableSize(3);
			booking2.setDateTime(LocalDateTime.of(2024, 05, 07, 14, 0));

			BookingModel booking3 = new BookingModel();

			booking3.setEmail("f_gir_07@hotmail.commm");
			booking3.setCustomerName("Roberto");
			booking3.setTableSize(3);
			booking3.setDateTime(LocalDateTime.of(2024, 05, 07, 12, 0));

			bookingRepository.addBooking(booking1);
			bookingRepository.addBooking(booking2);

			BookingModel bookingToTest = bookingRepository.addBooking(booking3);

			assertNotNull(bookingToTest);
			assertEquals(3, bookingRepository.getBookingList().size());

		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Test
	public void addBookingDuplicatedEmail() {

		BookingModel bookingToTest = null;

		try {
			BookingModel booking1 = new BookingModel();

			booking1.setEmail("f_gir_07@hotmail.com");
			booking1.setCustomerName("Fernando");
			booking1.setTableSize(2);
			booking1.setDateTime(LocalDateTime.of(2024, 05, 07, 10, 0));

			BookingModel booking2 = new BookingModel();

			booking2.setEmail("f_gir_07@hotmail.com");
			booking2.setCustomerName("Roberto");
			booking2.setTableSize(3);
			booking2.setDateTime(LocalDateTime.of(2024, 05, 07, 14, 0));

			bookingRepository.addBooking(booking1);
			bookingToTest = bookingRepository.addBooking(booking2);
			assertFalse(true);

		} catch (Exception e) {
			assertEquals(e.getMessage(), MessageConstant.RESPONSE_DUPLICATED_MAIL_EXCEPTION);
			assertNull(bookingToTest);
			assertEquals(1, bookingRepository.getBookingList().size());
		}
	}

	@Test
	public void addBookingIncorrectDate() {

		BookingModel bookingToTest = null;

		try {
			BookingModel booking1 = new BookingModel();

			booking1.setEmail("f_gir_07@hotmail.com");
			booking1.setCustomerName("Fernando");
			booking1.setTableSize(2);
			booking1.setDateTime(LocalDateTime.of(2024, 05, 07, 10, 0));

			BookingModel booking2 = new BookingModel();

			booking2.setEmail("f_gir_07@hotmail.comm");
			booking2.setCustomerName("Roberto");
			booking2.setTableSize(3);
			booking2.setDateTime(LocalDateTime.of(2024, 05, 07, 11, 0));

			bookingRepository.addBooking(booking1);
			bookingToTest = bookingRepository.addBooking(booking2);
			assertFalse(true);

		} catch (Exception e) {
			assertEquals(e.getMessage(), MessageConstant.RESPONSE_INCORRECT_DATE_EXCEPTION);
			assertNull(bookingToTest);
			assertEquals(1, bookingRepository.getBookingList().size());
		}
	}

	@Test
	public void getAllBookingsByDateCorreclty() {

		try {
			BookingModel booking1 = new BookingModel();

			booking1.setEmail("f_gir_07@hotmail.com");
			booking1.setCustomerName("Fernando");
			booking1.setTableSize(2);
			booking1.setDateTime(LocalDateTime.of(2024, 05, 07, 10, 0));

			BookingModel booking2 = new BookingModel();

			booking2.setEmail("f_gir_07@hotmail.comm");
			booking2.setCustomerName("Roberto");
			booking2.setTableSize(3);
			booking2.setDateTime(LocalDateTime.of(2024, 05, 8, 10, 0));

			bookingRepository.addBooking(booking1);
			bookingRepository.addBooking(booking2);

			FilterModel filter = new FilterModel();
			filter.setDate(LocalDate.of(2024, 05, 8));

			List<BookingModel> filteredBookingList = bookingRepository.getBookingListByDate(filter);

			assertEquals(2, bookingRepository.getBookingList().size());
			assertEquals(1, filteredBookingList.size());

		} catch (Exception e) {
			assertFalse(true);
		}
	}
}
