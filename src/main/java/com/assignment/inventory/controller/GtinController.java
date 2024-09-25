package com.assignment.inventory.controller;

import com.assignment.inventory.entity.Gtin;
import com.assignment.inventory.service.GtinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gtin")
@Slf4j
public class GtinController {

    @Autowired
    private GtinService gtinService;

    @PostMapping("add")
    public ResponseEntity<String> addGtin(@RequestBody Gtin gtin){

        log.info("addGtin controller method started");

        String result = gtinService.addGtin(gtin);

        log.info("addGtin controller method completed");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("associateGtinAndProduct")
    public ResponseEntity<String> associateGtinAndProduct(@RequestParam("id") Integer id,
                                          @RequestParam("productId") Integer productId) throws Exception{

        log.info("associateGtinAndProduct controller method started");

        String result = gtinService.associateProductAndGtin(id, productId);

        log.info("associateGtinAndProduct controller method completed");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getGtin")
    public Gtin getGtin(@RequestParam("gtin") String gtin){

        log.info("getGtin controller method started");

        Gtin gtinDetails = gtinService.getGtinDetails(gtin);

        log.info("getGtin controller method completed");

        return gtinDetails;
    }

    @GetMapping("getGtinsWithPositiveBatchQuantities")
    public ResponseEntity<List<String>> getGtinsWithPositiveBatchQuantities(){

        log.info("getGtinsWithPositiveBatchQuantities controller method started");

        List<String> listOfGtinPos = gtinService.getGtinsWithPositiveBatchQuantities();

        log.info("getGtinsWithPositiveBatchQuantities controller method completed");

        return ResponseEntity.ok(listOfGtinPos);
    }
}
