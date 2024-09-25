package com.assignment.inventory.controller;


import com.assignment.inventory.entity.Batch;
import com.assignment.inventory.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("batches")
@Slf4j
public class BatchController {

    @Autowired
    private BatchService batchService;
    @PostMapping("add")
    public ResponseEntity<String> addBatch(@RequestBody Batch batch){
        log.info("addBatch controller method started");
        String result = batchService.addBatch(batch);
        log.info("addBatch controller method completed");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("associateBatchAndProduct")
    public ResponseEntity<String> associateBatchAndProduct(@RequestParam("batchId") Integer batchId,
                                           @RequestParam("productId") Integer productId) throws Exception{

        log.info("associateBatchAndProduct controller method started");
        String result = batchService.associateProductAndBatch(batchId, productId);
        log.info("associateBatchAndProduct controller method completed");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("latestNegativeOrZeroQuantity")
    public ResponseEntity<Batch> getLatestNegativeOrZeroQuantityBatch() {
        log.info("getLatestNegativeOrZeroQuantityBatch controller method started");
        Batch latestBatch = batchService.getLatestNegativeOrZeroQuantityBatchForProduct();
        if (latestBatch != null) {
            log.info("getLatestNegativeOrZeroQuantityBatch controller method completed");
            return ResponseEntity.ok(latestBatch);
        } else {
            log.error("Error in getLatestNegativeOrZeroQuantityBatch controller method");
            return ResponseEntity.notFound().build();
        }
    }
}
