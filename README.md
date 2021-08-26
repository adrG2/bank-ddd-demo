# bank-ddd-demo

## Requirements

* Java 11
* Maven

## Endpoints

* customers.http
* wallets.http
* transfers.http

## Order

1. Create Customer
2. Get a customer to check that it has been created
3. Create a wallet for the customer
4. Get a wallet to check that it has been created
5. Put a credit(deposit)
6. Put a debit(withdraw). Must be negative.
7. Get a wallet with transfers

Disclaimer: The uuid of the http files must be changed when the resources are created.
