package com.nakshtra.interior.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nakshtra.interior.entity.Quotation;

public interface QuotationRepo extends JpaRepository<Quotation, Integer> {

	Quotation findByName(String name);
}
