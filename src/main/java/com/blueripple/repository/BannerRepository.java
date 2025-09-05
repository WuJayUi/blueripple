package com.blueripple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blueripple.entity.BannerEntity;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity, Long>{
	
    List<BannerEntity> findByIsActiveTrueOrderByPriorityAsc();
    
    List<BannerEntity> findByIsActiveFalseOrderByPriorityAsc();

}
