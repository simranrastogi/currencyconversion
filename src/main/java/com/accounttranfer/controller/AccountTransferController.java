package com.accounttranfer.controller;
import com.accounttranfer.model.AccountTransferModel;
import com.accounttranfer.model.FundingModel;
import com.accounttranfer.service.AccountTransferService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api")
public class AccountTransferController {

    @Autowired
    private AccountTransferService service;

    @Autowired
    private  AccountTransferModel model;

//    @Autowired
//    private CompletableFutureExample completableFutureExample;
//
    //@Autowired
   // FundingThread fundingThread;

    @Autowired
    FundingThreadRunnable fundingThreadRunnable;

    private static final Logger logger = LogManager.getLogger(AccountTransferController.class);

    @PostMapping("/currencys")
    public FundingModel create1(@RequestBody AccountTransferModel accountTransferModel) {
        logger.info(" inside controller crete method :: "+accountTransferModel);

        FundingModel model1 = new FundingModel();

        return model1;

    }

    @PostMapping("/currency")
    public FundingModel create(@RequestBody AccountTransferModel accountTransferModel) {
        logger.info(" inside controller crete method :: "+accountTransferModel);
        FundingModel model = service.postDataToOtherService(accountTransferModel);
        Long txnId = model.getId();
        System.out.println("txn id created :: " +txnId);
        //TODO VISADIRECT API TO BE CALLED
        //TODO SANCTION API TO BE CALLED
       FundingModel fundingModel = service.postDataSanctionService(model);
       if(fundingModel.getSanctionStatus().equals("HIT")){
            logger.info("set status complete as inside HIT case");
           fundingModel.setTxnStatus("PENDING");
        }
        else {
           fundingModel.setTxnStatus("COMPLETE");
           logger.info("set status complete as inside NOHIT case");
       }
        List<FundingModel> fundingList = List.of(fundingModel);
        if (fundingList !=  null  && fundingList.size() > 0){
            service.saveAllFundings(fundingList);
        }

        return fundingModel;

    }

//    @PostMapping("/currency")
//    public FundingModel create(@RequestBody AccountTransferModel accountTransferModel) throws ExecutionException, InterruptedException {
//        logger.info(" inside controller crete method :: "+accountTransferModel);
//
//        model.setAmount(accountTransferModel.getAmount());
//        model.setId(accountTransferModel.getId());
//        model.setReceiveraccountnum(accountTransferModel.getReceiveraccountnum());
//        model.setReceiverbankname(accountTransferModel.getReceiverbankname());
//        model.setSenderbankname(accountTransferModel.getSenderbankname());
//        model.setSenderaccountnum(accountTransferModel.getSenderaccountnum());
//        model.setSendername(accountTransferModel.getSendername());
//        //completableFutureExample.testCompletable(model);
//        CompletableFuture<FundingModel> future=  CompletableFuture.supplyAsync(()->service.postDataToOtherService(accountTransferModel));
//               //Thread t = new Thread(fundingThreadRunnable);
//
//
//       // t.run();
//        //t.start();
//        // fundingThread.start();
//        FundingModel model1 = new FundingModel();
//        model1.setStatus("IN-PR0GRESS");
//        return model1;
//
//    }


//    @Scheduled(cron = "0 */2 * * * ?")
    public void processInProgressTnx() throws URISyntaxException {
        logger.info("inside scheduler method");
        List<FundingModel> list = new ArrayList<>();

        for(FundingModel model: service.getAllInProgressTxn()){
            System.out.println(model.toString());
            model.setTxnStatus("COMPLETE");
            list.add(model);
        }
        if(list != null && list.size() > 0){
            logger.info("inside scheduler method check list not empty {} ",list.size());
            service.saveAllFundings(list);
        }
       else {
            logger.info("inside scheduler list is empty {} ",list.size());
        }
    }

    /**
     * in previous call already we hit the sanction and it gave first level response
     * sanction api will hit this api and will update HIT/NOHIT
     */
    @PostMapping("/updateTxnStatus")
    public FundingModel update( @RequestParam Long id,String sanctionStatus){
        FundingModel fundingModel = service.getDataFromOtherService(id);
        FundingModel fundObj = null;
        if(fundingModel != null){
            logger.info("inside scheduler list is empty {} ",fundingModel.toString());
            String txnStatus = fundingModel.getTxnStatus();
            if(txnStatus != null && txnStatus.length() != 0){
              fundObj = service.updateTxnStatusService(id,sanctionStatus,txnStatus);
                System.out.println(fundObj);
             return fundObj;
            }
        }
        return fundObj;
    }
}

