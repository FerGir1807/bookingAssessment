# Booking Application

Application for booking at a restaurant


## Features

- Register a booking 
- Get all bookings
- Get all booking by date


## API Reference

#### Register a booking

```http
  POST /booking
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `customerName` | `String` | CustomerName |
| `email` | `String` | Customer email |
| `tableSize` | `Integer` | Table size |
| `dateTime` | `dateTime` | Booking date |

#### Get all Bookings

```http
  GET /booking
```

#### Get all Bookings by date

```http
  GET /booking
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `date`      | `dateTime` | Date to consult |


## Improvements

Registering a booking:
- Consider more available tables to register more customers.
- Functionality to modify a booking.
- Add a booking status to validate availability in case of inactive bookings.

General:
- Entity validation for requests.
- Handle more type of exceptions.
