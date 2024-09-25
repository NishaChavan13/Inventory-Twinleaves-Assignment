package com.assignment.inventory.repository;

import com.assignment.inventory.entity.Gtin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GtinRepository extends JpaRepository<Gtin, Integer> {

    Gtin findByGtin(String gtin);

    @Query("SELECT DISTINCT g.gtin FROM Gtin g " +
            "JOIN g.product p " +
            "JOIN Batch b ON p.productId = b.product.productId " +
            "WHERE b.availableQuantity > 0")
    List<String> findGtinsWithPositiveBatchQuantities();
}
