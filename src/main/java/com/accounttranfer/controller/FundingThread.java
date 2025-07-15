package com.accounttranfer.controller;

import com.accounttranfer.model.AccountTransferModel;
import com.accounttranfer.model.FundingModel;
import com.accounttranfer.service.AccountTransferService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FundingThread extends Thread {
    @Autowired
    AccountTransferService service;


    private AccountTransferModel accountTransferModel;

    @Autowired
    public FundingThread(AccountTransferModel accountTransferModel){
        this.accountTransferModel = accountTransferModel;
    }
    @Override
    public void run() {
        FundingModel model = service.postDataToOtherService(accountTransferModel);
        super.run();
    }
}
