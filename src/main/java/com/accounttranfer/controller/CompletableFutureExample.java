package com.accounttranfer.controller;

import com.accounttranfer.model.AccountTransferModel;
import com.accounttranfer.model.FundingModel;
import com.accounttranfer.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class CompletableFutureExample {
    @Autowired
    AccountTransferService service;




    public FundingModel testCompletable(AccountTransferModel accountTransferModel) throws ExecutionException, InterruptedException {
       CompletableFuture<FundingModel> future=  CompletableFuture.supplyAsync(()->service.postDataToOtherService(accountTransferModel));
       return future.get();

    }
}
