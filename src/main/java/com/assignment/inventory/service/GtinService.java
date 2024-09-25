package com.assignment.inventory.service;

import com.assignment.inventory.entity.Batch;
import com.assignment.inventory.entity.Gtin;
import com.assignment.inventory.entity.Product;
import com.assignment.inventory.repository.BatchRepository;
import com.assignment.inventory.repository.GtinRepository;
import com.assignment.inventory.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GtinService {

    @Autowired
    private GtinRepository gtinRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchRepository batchRepository;


    public String addGtin(Gtin gtin){
        log.info("addGtin service method started");

        gtinRepository.save(gtin);

        log.info("addGtin service method completed");

        return "Gtin has been saved to DB";
    }

    public String associateProductAndGtin(Integer id, Integer productId) throws Exception{

        log.info("associateProductAndGtin service method started");

        //Get the gtin from the id
        Optional<Gtin> gtinOptional = gtinRepository.findById(id);

        if(gtinOptional.isEmpty()){
            //Throw an exception that the gtin is not found
            throw new Exception("id entered is incorrect");
        }

        Gtin gtin =gtinOptional.get();

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            //throws an exception saying productID entered is incorrect
            throw new Exception("ProductId entered is incorrect");
        }

        Product product = optionalProduct.get();


        //associate Product and Gtin Entity
        gtin.setProduct(product);

        gtinRepository.save(gtin);
        productRepository.save(product);

        log.info("associateProductAndGtin service method completed");

        return "Gtin and Product have been associated";
    }

    public Gtin getGtinDetails(String gtin){
        log.info("getGtinDetails service method started");

        Gtin gtinDetails = gtinRepository.findByGtin(gtin);

        log.info("getGtinDetails service method completed");

        return gtinDetails;
    }

    public List<String> getGtinsWithPositiveBatchQuantities() {

        log.info("getGtinsWithPositiveBatchQuantities service method completed");

        return gtinRepository.findGtinsWithPositiveBatchQuantities();
    }
}
