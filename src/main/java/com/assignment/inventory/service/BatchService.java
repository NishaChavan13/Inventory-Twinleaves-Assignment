package com.assignment.inventory.service;

import com.assignment.inventory.entity.Batch;
import com.assignment.inventory.entity.Product;
import com.assignment.inventory.repository.BatchRepository;
import com.assignment.inventory.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BatchService {
    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addBatch(Batch batch){
        log.info("addBatch service method started");

        batchRepository.save(batch);

        log.info("addBatch service method completed");
        return "Batch is added to the DB";
    }

    public String associateProductAndBatch(Integer batchId, Integer productId) throws Exception{

        log.info("associateProductAndBatch service method started");

        //Get the batch from the batchId
        Optional<Batch> batchOptional = batchRepository.findById(batchId);

        if(batchOptional.isEmpty()){
            //Throw an exception that the batch is not found
            throw new Exception("BookId entered is incorrect");
        }

        Batch batch =batchOptional.get();



        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){

            throw new Exception("AuthorId entered is incorrect");
        }

        Product product = optionalProduct.get();


        //associate batch and product Entity
        batch.setProduct(product);

        batchRepository.save(batch);
        productRepository.save(product);

        log.info("associateProductAndBatch service method completed");

        return "Batch and Product have been associated";
    }

    public Batch getLatestNegativeOrZeroQuantityBatchForProduct() {

        log.info("getLatestNegativeOrZeroQuantityBatchForProduct service method started");

        List<Batch> batches = batchRepository.findLatestNegativeOrZeroQuantityBatchByProduct();

        log.info("getLatestNegativeOrZeroQuantityBatchForProduct service method completed");

        // Return the first (most recent) batch if available
        return batches.isEmpty() ? null : batches.get(0);
    }
}
