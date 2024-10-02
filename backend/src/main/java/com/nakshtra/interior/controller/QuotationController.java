package com.nakshtra.interior.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakshtra.interior.customexcpetion.QuotationNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.Quotation;
import com.nakshtra.interior.service.QuotationService;



@RestController
@RequestMapping("/quotation")
public class QuotationController {

		@Autowired
		private QuotationService quotationService ;

		@PostMapping("/add")
		public ResponseEntity<?> registerdQuotation(@RequestBody Quotation quotation) {
			Quotation createdQuotation = quotationService.create(quotation);
			return new ResponseEntity<>(createdQuotation, HttpStatus.CREATED);
		}

		@GetMapping("/all")
		public ResponseEntity<?> getAllQuotationser() {
			try {
				return new ResponseEntity<>(quotationService.fetchAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>
				(new ErrorResponse("Quotation Fetching is failed", e.getMessage()),
						HttpStatus.BAD_REQUEST);
			}

		}
		@GetMapping("/getQuotation/{QuotationId}")
		public ResponseEntity<?> getQuotationById(@PathVariable("QuotationId") Integer id) {
			try {
				return  ResponseEntity.ok(quotationService.fetchById(id));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce=
			    new ErrorResponse("Quotation Fetching is failed", e.getMessage());
				return new ResponseEntity<>
				(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}

		@PutMapping("/update/{QuotationId}")
		public ResponseEntity<?> updateQuotationById(@PathVariable("QuotationId") Integer id,
				@RequestBody Quotation updateQuotation) {
			try {
				Quotation existingQuotation=quotationService.fetchById(id);
				
				return  ResponseEntity.ok(quotationService.update(updateQuotation, existingQuotation));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce= new ErrorResponse("Quotation updation is failed", e.getMessage());
				return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}
		@DeleteMapping("/delete/{QuotationId}")
		public ResponseEntity<?>deleteQuotationById(@PathVariable ("QuotationId") Integer id)
		{ try {
			Quotation existingQuotation=quotationService.fetchById(id);
			return ResponseEntity.ok(quotationService.delete(existingQuotation));
					
		} catch (Exception e) {

			ErrorResponse errorResponce= new ErrorResponse("Quotation deletion is failead", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			
		}
			
		}
		@GetMapping("/getbyName/{name}")
		public ResponseEntity<?> getQuotationByQuotationName(@PathVariable String name){
			try {
				ResponseEntity res = null;
				Quotation foundQuotation = quotationService.findByName(name);
				if(foundQuotation != null) {
					return res.ok(foundQuotation);
				
				}else {
					throw new QuotationNotFoundException("Invalid Quotationname..");
				}
			
			} catch (Exception e) {
				return new ResponseEntity<>(new ErrorResponse("fetching Quotation by Quotationname failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
			}
			
			
		}
		
}
