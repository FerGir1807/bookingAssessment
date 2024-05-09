package com.booking.persistance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.common.constant.MessageConstant;
import com.booking.domain.model.BookingModel;
import com.booking.domain.model.FilterModel;
import com.booking.persistance.interfaces.Booking;

public class BookingLocalRepository implements Booking {

	private static List<BookingModel> bookingList = new ArrayList<BookingModel>();

	public List<BookingModel> getBookingList() {
		System.out.println("Executing method getBookingList");
		return bookingList;
	}

	public List<BookingModel> getBookingListByDate(FilterModel filter) {
		System.out.println("Executing method getBookingListByDate");
		LocalDate matchDate = filter.getDate();

		List<BookingModel> filteredBookingList = bookingList.stream()
				.filter(x -> x.getDateTime().toLocalDate().isEqual(matchDate)).collect(Collectors.toList());

		filteredBookingList.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));

		return filteredBookingList;

	}

	public BookingModel addBooking(BookingModel booking) throws Exception {

		System.out.println("Executing method addBooking");

		LocalDate date = booking.getDateTime().toLocalDate();
		LocalTime timeToAdd = booking.getDateTime().toLocalTime();

		List<BookingModel> bookingListByDate = bookingList.stream()
				.filter(x -> x.getDateTime().toLocalDate().equals(date)).collect(Collectors.toList());

		bookingListByDate.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));

		if (bookingListByDate.stream().anyMatch(x -> x.getEmail().equals(booking.getEmail()))) {
			throw new Exception(MessageConstant.RESPONSE_DUPLICATED_MAIL_EXCEPTION);
		} else if (!canBookByTime(bookingListByDate, timeToAdd)) {
			throw new Exception(MessageConstant.RESPONSE_INCORRECT_DATE_EXCEPTION);
		} else {
			booking.setBookingId(bookingList.size() + 1);
			bookingList.add(booking);

			return booking;
		}

	}

	private boolean canBookByTime(List<BookingModel> bookingList, LocalTime timeToAdd) {

		LocalTime currentTime;
		LocalTime nextTime;
		boolean response = false;

		if (bookingList.isEmpty()) {
			response = true;
		} else {
			for (int i = 0; i < bookingList.size(); i++) {

				currentTime = bookingList.get(i).getDateTime().toLocalTime().plusHours(2);

				if (timeToAdd.isAfter(currentTime) || timeToAdd.equals(currentTime)) {
					if (i == bookingList.size() - 1) {
						nextTime = currentTime;

						if (timeToAdd.isAfter(nextTime) || timeToAdd.equals(nextTime)) {
							response = true;
							break;
						}

					} else {
						nextTime = bookingList.get(i + 1).getDateTime().toLocalTime().minusHours(2);

						if (timeToAdd.isBefore(nextTime) || timeToAdd.equals(nextTime)) {
							response = true;
							break;
						}

					}
				} else {
					break;
				}
			}
		}

		return response;
	}

}
