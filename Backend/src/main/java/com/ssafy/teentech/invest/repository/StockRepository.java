package com.ssafy.teentech.invest.repository;

import com.ssafy.teentech.invest.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<List<Stock>> findAllByCompanyName(String companyName);
    Optional<Stock> findByCompanyNameAndDate(String companyName, LocalDate date);
}
