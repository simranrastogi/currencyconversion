package com.accounttranfer.controller;

import com.accounttranfer.model.AccountTransferModel;
import com.accounttranfer.model.FundingModel;
import com.accounttranfer.service.AccountTransferService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resilience")
public class ResilienceController {

    @Autowired
    private AccountTransferService service;

    private static final Logger logger = LogManager.getLogger(ResilienceController.class);


    @PostMapping("/currency")
    @Retry(name = "myRetry")
  // @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackMethod")
   // @Retry(name = "myRetry", fallbackMethod = "fallback")
    public FundingModel create(@RequestBody AccountTransferModel accountTransferModel) {
        logger.info(" inside controller crete method :: "+accountTransferModel);
        return service.postDataToOtherService(accountTransferModel);
    }

    @GetMapping("/getdata")
    @RateLimiter(name = "myRateLimiter")
    public String getData() {
        return "Here’s your data!";
    }



    public FundingModel fallbackMethod(AccountTransferModel accountTransferModel , Throwable t) {
        logger.info(" inside circuit breaker contro fallback method :: "+accountTransferModel);
     //  logger.info(t.getMessage());
        return new FundingModel();
    }


    public FundingModel fallback(AccountTransferModel accountTransferModel , Throwable t) {
        //logger.info(" inside contro retry fallback method :: "+accountTransferModel);
       //logger.info(t.getMessage());
        return new FundingModel();
    }





}

