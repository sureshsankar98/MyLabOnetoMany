package com.ibm.LabOneToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.LabOneToMany.model.Mobile;

public interface MobileDao extends JpaRepository<Mobile, Long> {

}
