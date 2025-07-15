package com.accounttranfer.controller;

import com.accounttranfer.model.AccountTransferModel;
import com.accounttranfer.model.FundingModel;
import com.accounttranfer.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FundingThreadRunnable implements Runnable {
    @Autowired
    AccountTransferService service;


    AccountTransferModel accountTransferModel;

   @Autowired
   public FundingThreadRunnable(AccountTransferModel accountTransferModel){
       this.accountTransferModel = accountTransferModel;
   }


    @Override
    public void run() {
        FundingModel model = service.postDataToOtherService(accountTransferModel);
    }
}
