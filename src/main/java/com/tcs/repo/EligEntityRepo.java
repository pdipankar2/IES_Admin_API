package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.entity.EligEntity;

@Repository
public interface EligEntityRepo extends JpaRepository<EligEntity, Integer> {

}
