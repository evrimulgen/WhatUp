package com.whatsup.repositories;

import com.whatsup.models.Coupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mosesfranco on 7/6/17
 * Codeup
 * Pinnacles
 */
@Repository
public interface CouponsRepository extends CrudRepository<Coupon, Long> {
	List<Coupon> findAll();
}
