package com.sejong.rental.repository;

import com.sejong.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {
    List<Rental> findAllByStudentNoAndPassword(String studentNo, String password);
}
