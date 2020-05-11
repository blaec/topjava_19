##MealRestController

**Get meal**

`curl --location --request GET 'http://localhost:8080/topjava/rest/admin/meals/100002'`

**Delete meal**

`curl --location --request DELETE 'http://localhost:8080/topjava/rest/admin/meals/100003'`

**Get all meals**

`curl --location --request GET 'http://localhost:8080/topjava/rest/admin/meals'`

**Get meals filtered**

`curl --location --request GET 'http://localhost:8080/topjava/rest/admin/meals/filter?startDate=2020-01-30&endDate=2020-01-31&startTime=08:00&endTime=11:00'`

**Update meal**

`curl --location --request PUT 'http://localhost:8080/topjava/rest/admin/meals/100007' \
 --header 'Content-Type: application/json' \
 --data-raw '{
 	"dateTime": "2020-01-31T13:00:00",
 	"description": "Updated supper",
 	"calories": 555
 }'`
 
 **Create meal**
 
 `curl --location --request POST 'http://localhost:8080/topjava/rest/admin/meals' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  	"dateTime": "2020-05-11T22:22:22",
  	"description": "CreatedSupper",
  	"calories": 999
  }'`