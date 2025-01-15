package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.entity.PlanEntity;

@Repository
public interface PlanRepo extends JpaRepository<PlanEntity,Integer> {

}
