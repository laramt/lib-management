package com.project.librarysystem.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Table(name = "HOLD_TB")
public class Hold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn( name = "book_copy_id")
    private BookCopy bookCopy;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkout;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkIn;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;

    public Hold() {
    }

    public Hold(Long id, BookCopy bookCopy, Patron patron, LocalDate checkout, LocalDate checkIn, LocalDate dueDate, BigDecimal lateFee, boolean returned) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.patron = patron;
        this.checkout = checkout;
        this.checkIn = checkIn;
        this.dueDate = dueDate;
        this.lateFee = lateFee;
        this.returned = returned;
    }

    @PrePersist
    protected void onCreate() {
        checkout = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hold hold)) return false;

        return getId() != null ? getId().equals(hold.getId()) : hold.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
