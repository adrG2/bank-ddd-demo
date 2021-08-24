# bank-ddd-demo

## Requirements

* Java 11 
* Maven

## Features

### Register a user

* Path: `/users`
* Method: `POST`
* Body:

```
{
  "email": "xxx@xxx.com",
  "password": "xxxx"
}
```

### Create a Wallet

* Path: `/wallet`
* Method: `POST`
* Body:

``` 
{
    "userId": "xxx"
}
```

### Visualize a Wallet

* Path: `/wallet`
* Method: `GET`
* PathParameters:
  * id

### Deposit

* Path: `/wallet/deposit`
* Method: `POST`
* Body:

``` 
{
    "walletId": "xxxxx",
    "quantity": "xxxxx",
    "currency": "",
    "method": "",
    "source": "",
    "concept": ""
}
```

### Transfer

* Path: `/wallet/transfer`
* Method: `POST`
* Body:

``` 
{
    "walletId": "",
    "destination": "",
    "quantity": "",
    "currency": "",
    "concept": ""
}
```