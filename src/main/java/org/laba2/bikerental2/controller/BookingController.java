package org.laba2.bikerental2.controller;

import org.laba2.bikerental2.dto.BookingDTO;
import org.laba2.bikerental2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // GET: Отримати всі бронювання
    @GetMapping
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // GET: Отримати бронювання за ID
    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // POST: Створити нове бронювання
    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    // PUT: Оновити існуюче бронювання
    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id, bookingDTO);
    }


    // DELETE: Видалити бронювання
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
