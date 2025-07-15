package com.accounttranfer.service;
import com.accounttranfer.controller.AccountTransferController;
import com.accounttranfer.model.AccountTransferModel;
import com.accounttranfer.model.FundingModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTransferService {

@Autowired
RestTemplate restTemplate;
//    private final String BASE_URL = "http://localhost:8082/api/fundings";
//    private final String BASE_URL2 = "http://localhost:8082/api/fundings/updateFund";
//    private final String SANCTIONSERIVCE_URL = "http://localhost:8082/api/sanction";
//    private final String ALLFUNDING_URL = "http://localhost:8082/api/fundings/getAllFund";

    private final String BASE_URL = "https://funding.azurewebsites.net/api/fundings";
  private final String BASE_URL2 = "https://funding.azurewebsites.net/api/fundings/updateFund";
    private final String SANCTIONSERIVCE_URL = "https://funding.azurewebsites.net/api/sanction";
    private final String ALLFUNDING_URL = "https://funding.azurewebsites.net/api/fundings/getAllFund";

    private static final Logger logger = LogManager.getLogger(AccountTransferService.class);

    public FundingModel getDataFromOtherService(Long id) {

        String finalUrl = BASE_URL + "/" + id ; // http://localhost:8082/api/fundings/123
        logger.info("inside get funding model using rest template By id : "+ id);
        return restTemplate.getForObject(finalUrl, FundingModel.class);
    }

    public FundingModel postDataToOtherService(AccountTransferModel data) {
        logger.info("inside post data from funding service using rest template : "+ data);

        return restTemplate.postForObject(BASE_URL, data, FundingModel.class);
    }

    public FundingModel postDataSanctionService(FundingModel data) {
        logger.info("inside post data from sanction service using rest template : "+ data);
        return restTemplate.postForObject(SANCTIONSERIVCE_URL, data, FundingModel.class);
    }


    public void saveAllFundings(List<FundingModel> fundings) {
        final String BULK_SAVE_URL = "https://funding.azurewebsites.net/api/fundings/saveAllFunds";
        logger.info("inside account service save all funds method:: ");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<FundingModel>> entity = new HttpEntity<>(fundings, headers);

        ResponseEntity<List<FundingModel>> response = restTemplate.exchange(
                BULK_SAVE_URL,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<FundingModel>>() {}
        );

        List<FundingModel> savedList = response.getBody();
        if(savedList != null && savedList.size() > 0){
            logger.info("inside account service save all funds method for funding model list :: "+savedList);
            savedList.forEach(f -> System.out.println("Saved: " + f));
        }

    }

    public FundingModel updateTxnStatusService(Long id, String sanctionStatus, String txnStatus) {
        FundingModel data = new FundingModel();
        data.setId(id);
        logger.info("Sanction status value:: "+sanctionStatus);
        if(sanctionStatus.equals("NOHIT") && txnStatus.equals("PENDING")){
            logger.info("inside post data from update funding service using rest template : "+ data);
            data.setTxnStatus("INPROGRESS");
            logger.info("set status complete as inside NOHIT case");
        }


        return restTemplate.postForObject(BASE_URL2, data, FundingModel.class);
    }



    public List<FundingModel> getAllInProgressTxn() throws URISyntaxException {

        final String ALLFUNDING_URL = "https://funding.azurewebsites.net/api/fundings/getAllFundInProgress";

        URI uri = new URI(ALLFUNDING_URL);

        HttpEntity<Void> entity = new HttpEntity<>(null); // or use new HttpEntity<>(null, headers) if needed

        ParameterizedTypeReference<List<FundingModel>> responseType =
                new ParameterizedTypeReference<List<FundingModel>>() {
                };

        ResponseEntity<List<FundingModel>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                responseType
        );

        List<FundingModel> fundingList = response.getBody();
        if(fundingList != null && fundingList.size() > 0 ){
            logger.info("funding list has values size :{} ", fundingList.size());
        }

        return fundingList;
    }

    public void deleteDataFromOtherService(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }

//        ArrayList<AccountTransferModel> arrList = new ArrayList<>();
//        public AccountTransferModel amtTransfer(AccountTransferModel actDetail) {
//           Long transactionId = 10234567L;
//           actDetail.setId(transactionId);
//            arrList.add(actDetail);
//
//            return actDetail;
//
//        }
//






    }

