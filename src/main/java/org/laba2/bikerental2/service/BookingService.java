package org.laba2.bikerental2.service;

import org.laba2.bikerental2.dto.BookingDTO;
import org.laba2.bikerental2.entity.Bicycle;
import org.laba2.bikerental2.entity.Booking;
import org.laba2.bikerental2.entity.User;
import org.laba2.bikerental2.repository.BicycleRepository;
import org.laba2.bikerental2.repository.BookingRepository;
import org.laba2.bikerental2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BicycleRepository bicycleRepository;

    // CREATE: Створення нового бронювання
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Bicycle bicycle = bicycleRepository.findById(bookingDTO.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Bicycle not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBicycle(bicycle);
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking = bookingRepository.save(booking);

        bookingDTO.setId(booking.getId());
        return bookingDTO;
    }

    // READ: Отримання списку всіх бронювань
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // READ: Отримання бронювання за ID
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return convertToDTO(booking);
    }

    // UPDATE: Оновлення існуючого бронювання
//    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
//        Booking booking = bookingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//
//        User user = userRepository.findById(bookingDTO.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Bicycle bicycle = bicycleRepository.findById(bookingDTO.getBicycleId())
//                .orElseThrow(() -> new RuntimeException("Bicycle not found"));
//
//        booking.setUser(user);
//        booking.setBicycle(bicycle);
//        booking.setStartTime(bookingDTO.getStartTime());
//        booking.setEndTime(bookingDTO.getEndTime());
//
//        booking = bookingRepository.save(booking);
//        return convertToDTO(booking);
//    }

    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bicycle bicycle = bicycleRepository.findById(bookingDTO.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Bicycle not found"));

        booking.setUser(user);
        booking.setBicycle(bicycle);
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());

        bookingRepository.save(booking);

        // Map the updated entity back to DTO
        bookingDTO.setId(booking.getId());
        return bookingDTO;
    }


    // DELETE: Видалення бронювання за ID
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
    }

    // Метод для конвертації Booking у BookingDTO
    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setBicycleId(booking.getBicycle().getId());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        return dto;
    }
}
