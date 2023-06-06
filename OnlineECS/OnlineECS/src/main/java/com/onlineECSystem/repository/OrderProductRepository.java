package com.onlineECSystem.repository;

import com.onlineECSystem.entity.Order;
import com.onlineECSystem.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository  extends JpaRepository<OrderProduct, Long> {
}
