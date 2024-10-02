package com.nakshtra.interior.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nakshtra.interior.entity.Design;

public interface DesignRepo extends JpaRepository<Design, Integer> {
   

    
    Design findByName(String name);
}
