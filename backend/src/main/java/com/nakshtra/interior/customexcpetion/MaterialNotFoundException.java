package com.nakshtra.interior.customexcpetion;

public class MaterialNotFoundException extends RuntimeException{

	public MaterialNotFoundException(String msg) {
		super(msg);
	}

}
