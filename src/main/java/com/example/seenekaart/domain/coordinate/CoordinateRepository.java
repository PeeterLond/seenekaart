package com.example.seenekaart.domain.coordinate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {


}