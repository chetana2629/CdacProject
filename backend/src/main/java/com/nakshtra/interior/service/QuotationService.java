package com.nakshtra.interior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.customexcpetion.QuotationNotFoundException;
import com.nakshtra.interior.dao.QuotationRepo;
import com.nakshtra.interior.entity.Quotation;
@Service
public class QuotationService implements CurdService<Quotation, Integer> {

	@Autowired
	private QuotationRepo quotationRepo;
	@Override
	public Quotation create(Quotation quotation) {
		
		return quotationRepo.save(quotation);
	}

	@Override
	public List<Quotation> fetchAll() {
	
		return quotationRepo.findAll();
	}

	@Override
	public Quotation fetchById(Integer k) {
	
		return quotationRepo.findById(k).orElseThrow(()-> new QuotationNotFoundException("Invalid Id"));
	}

	@Override
	public Quotation update(Quotation updateQuotation, Quotation exixtingQuotaion) {
		exixtingQuotaion.setAmount(updateQuotation.getAmount());
		exixtingQuotaion.setDescription(updateQuotation.getDescription());
		exixtingQuotaion.setDesign(updateQuotation.getDesign());
		return quotationRepo.save(exixtingQuotaion);
	}

	@Override
	public String delete(Quotation quotation) {
		quotationRepo.delete(quotation);
		return quotation.getId()+ " Deleted";
	}

	public Quotation findByName(String name) {
		
		return quotationRepo.findByName(name);
	}
}
