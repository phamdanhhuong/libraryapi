package com.library.libraryapi.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  reservationId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Column(name = "reservationDate", nullable = false)
    private LocalDateTime reservationDate = LocalDateTime.now();

    @Column(name = "expirationDate", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "status", nullable = false)
    private String status  = "ACTIVE";

    // Getters and Setters

    public Integer  getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer  reservationId) {
        this.reservationId = reservationId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Constructors
    public Reservation() {}

    public Reservation(Integer  reservationId, Users user, Book book, LocalDateTime reservationDate, LocalDateTime expirationDate, String status) {
        this.reservationId = reservationId;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
        this.status = status;
    }
}
