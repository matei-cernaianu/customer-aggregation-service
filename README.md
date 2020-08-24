# Customer Aggregation Service

Running this service requires following dependecies to be installed on your machine:
* java 1.8 
* docker 

##### How to run 

  Scripts are configured to use built in maven wrapper.

  * ###### **Windows** 
    * Using **CMD**

      ``` 
      run.bat
      ```
  * ###### **Linux / MacOS**
    * bash

      ``` 
      ./run.sh
      ```
    
##### How to use

  Service exposes two endpoints: 

  * Accounts
    * Request 
      ```
      curl --location --request GET 'localhost:8085/v1/accounts' \
      --header 'username: popa' \
      --header 'Content-Type: application/json' \
      --header 'Accept: application/json'
      ```
    * Response
      ```
      [
      {
          "id": "a97c24c5-8b65-45f8-8a4a-06f576c3e1e7",
          "update": "2020-05-26T09:32:47.706Z",
          "name": "Account-popa",
          "product": "Gold account.",
          "status": "ENABLED",
          "type": "CREDIT_CARD",
          "balance": 8652.059672785963
      }
      ]
      ```
  * Transactions
    * Request :page_with_curl: (this is a paginated request, pay attention to **page** and **pageSize** params)

      ```
      curl --location --request GET 'localhost:8085/v1/transactions?page=0&pageSize=2' \
      --header 'username: popa' \
      --header 'Content-Type: application/json' \
      --header 'Accept: application/json'
      ```

    * Example Response
      ```
      [
      {
          "id": "83feaaab-33a4-4313-913a-a09e2fc84242",
          "accountId": "a97c24c5-8b65-45f8-8a4a-06f576c3e1e7",
          "exchangeRate": {
              "currencyFrom": "EUR",
              "currencyTo": "USD",
              "rate": 1.1
          },
          "originalAmount": {
              "amount": 17.640369570917173,
              "currency": "USD"
          },
          "creditor": {
              "maskedPan": "XXXXXXXXXX12326",
              "name": "Creditor 12326"
          },
          "debtor": {
              "maskedPan": "XXXXXXXXXX98726",
              "name": "DebtorName 98726"
          },
          "status": "BOOKED",
          "currency": "EUR",
          "amount": 16.037,
          "update": "2020-05-26T09:32:54.142Z",
          "description": "Mc Donalds Amsterdam transaction - 26"
      },
      {
          "id": "3beccb56-9d23-4866-99c9-30ca638c2c60",
          "accountId": "a97c24c5-8b65-45f8-8a4a-06f576c3e1e7",
          "exchangeRate": {
              "currencyFrom": "EUR",
              "currencyTo": "USD",
              "rate": 1.1
          },
          "originalAmount": {
              "amount": 17.669499711620574,
              "currency": "USD"
          },
          "creditor": {
              "maskedPan": "XXXXXXXXXX12325",
              "name": "Creditor 12325"
          },
          "debtor": {
              "maskedPan": "XXXXXXXXXX98725",
              "name": "DebtorName 98725"
          },
          "status": "BOOKED",
          "currency": "EUR",
          "amount": 16.063,
          "update": "2020-05-26T09:32:54.141Z",
          "description": "Mc Donalds Amsterdam transaction - 25"
      }
      ]
      ```
##### How to access in memory DB via Web Console

  * This service uses embedded in memory H2DB, in order to access it you should access ``` localhost:8085/h2-console ``` with username **sa** and password should be left empty.
