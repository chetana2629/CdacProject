package com.nakshtra.interior.customexcpetion;

public class ProjectNotFoundException extends RuntimeException{

	public ProjectNotFoundException (String msg) {
		super(msg);
	}
}
