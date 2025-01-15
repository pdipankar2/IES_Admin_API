package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcs.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	@Query("update UserEntity set accStatus=:status where userId=:userId")
	public Integer updateAccStatus(Integer userId,String status);
	
	public UserEntity findByEmail(String email);
	
	
	public UserEntity findByEmailAndPwd(String email,String pwd);
	
	
}