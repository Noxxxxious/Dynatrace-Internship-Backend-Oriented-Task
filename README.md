# Description
This is an implementation of backend oriented task for Dynatrace internship application. My goal was to implement a simple runnable local server destined to query data from the Narodowy Bank Polski's public APIs and return relevant information from them.
# Installation
In the project directory run following commands:
- Create docker image
```
docker build -t backend_image .
```
- Run docker container
```
docker run --name backend -p 8080:8080 backend_image
```
# Operations
Requests to the server can be made with curl tool or any HTTP client of your choice.
## Examples
- Average euro exchange rate of Euro on April 5, 2023.
```
curl http://localhost:8080/api/exchanges/EUR/2023-04-05
```
Expected response:
```
{"exchangeRate":4.6803}
```
- Maximum and minimum average exchange rates of Pound sterling from last 10 records.
```
curl http://localhost:8080/api/maxminrates/GBP/10
```
Expected response:
```
{"maxRate":5.3369,"minRate":5.2086}
```
- Major difference between the buy and ask rate of Yen from last 50 records.
```
curl http://localhost:8080/api/buyaskdiff/JPY/50
```
Expected response:
```
{"majorDiff":6.540000000000018E-4}
```
