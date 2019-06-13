# side-project
This is a java side-project used `7 hours` to completely code this program. <br />
Used strategy pattern

>## GUIDE TO USE 
>1. Import this project into any IDE
>2. Take a look at the smaple_input.json file
>3. This program takes in JSON input ONLY

## SUPPORTED COMMANDS & AN EXAMPLE *(CAP sensitive)*
###customer
```json
{ "command": "customer", "name": "UxU", "password": "12345" }
```
###login
```json
{ "command": "login", "name": "UxU", "password": "12345" }
```
###logout
```json
{ "command": "logout", "name": "Wick Wick"}
```
###restaurant
```json
{ "command": "restaurant", "name": "Klang Bak Kut Teh", "cuisine": "chinese", "opening": "06:00:00", "closing": "14:00:00", "menu": "Bak Kut Teh", "price": "10.80"}
## to add more menu in the restaurant just copy the command and change the menu and price field
{ "command": "restaurant", "name": "Klang Bak Kut Teh", "cuisine": "chinese", "opening": "06:00:00", "closing": "14:00:00", "menu": "Chinese Tea", "price": "1.80"}
```
###order
```json
{ "command": "order", "order": "1", "customer": "UxU", "restaurant": "Klang Bak Kut Teh", "cuisine": "chinese", "menu": "Bak Kut Teh", "time": "13:00:00"}
```
###cancel
```json
{ "command": "cancel", "order": "2"}
```
###change
```json
{ "command": "change", "order": "1", "customer": "UxU", "restaurant": "Klang Bak Kut Teh", "cuisine": "chinese", "menu": "Chinese Tea" , "time": "13:30:00"}
```
###pay
```json
{ "command": "pay", "order": "1", "payment": "cash", "amount": "50"}
```
```json
## to display restaurants
{ "command": "display", "thing": "restaurants"}
## to display orders
{ "command": "display", "thing": "orders"}
```

##IF YOU CTRL + A AND CTRL + C IN SAMPLE_INPUT.JSON THIS IS THE OUTPUT YOU WILL GET
###Dont mind the padding/ spacing 
```json
{
 "SYSTEM_USER": "customer UxU added",
 "status": "Success"
}
{
 "SYSTEM_LOGIN": "UxU",
 "status": "Success"
}
{
 "SYSTEM_RESTAURANT": "Klang Bak Kut Teh (chinese) added",
 "status": "Success"
}
{
 "SYSTEM_MENU": "menu Chinese Tea 1.8 added to Klang Bak Kut Teh (chinese)",
 "status": "Success"
}
{
 "SYSTEM_ORDER": "order at Klang Bak Kut Teh (chinese)",
 "status": "Success",
 "order": 1,
 "customer": "UxU"
}
{
 "ERROR_404": "order doesn't exist",
 "status": "Rejected"
}
{
 "SYSTEM_ORDER": "order at Klang Bak Kut Teh (chinese)",
 "status": "Success",
 "order": 1,
 "customer": "UxU"
}
{
 "SYSTEM_DELIVERY": "order [1] is delivering or delivered",
 "status": "Success"
}
{
 "SYSTEM_PAYMENT": "balance $48.2",
 "status": "Success"
}
{"restaurants": ["Klang Bak Kut Teh (chinese)"]}
{"orders": ["paid [1] ordered by UxU"]}
{
 "SYSTEM_LOGOUT": "UxU logging out",
 "status": "Success"
}
```