**Get All Employees**
----
  Returns json data about all employees.

* **URL**

  /api/employee

* **Method:**

  `GET`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{ "id" : 12, "firstName" : "Sven", "lastName" : "Svensson", "positionId" : 0 },{ "id" : 13, "firstName" : "Erik", "lastName" : "Eriksson", "positionId" : 1 }]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/employee",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

**Get All FoodOrder**
----
  Returns json data about all food orders.

* **URL**

  /api/foodorder

* **Method:**

  `GET`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{"created":"2017-10-03T14:04:20Z[UTC]","delivered":"2017-10-03T14:05:20Z[UTC]","diningTableOrderId":0,"foodId":0,"id":0,"modification":"extra ice","orderStatusId":2,"ready":"2017-10-03T14:03:20Z[UTC]"},{"created":"2017-10-03T14:04:20Z[UTC]","delivered":"2017-10-03T14:05:20Z[UTC]","diningTableOrderId":0,"foodId":1,"id":1,"modification":"extra meat","orderStatusId":2,"ready":"2017-10-03T14:03:20Z[UTC]"}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/foodorder",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```


**Get All Measurements**
----
  Returns json data about all measurements.

* **URL**

  /api/measurement/getall

* **Method:**

  `GET`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/measurement/getall",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

**Add Measurement**
----
  Add a measurement.

* **URL**

  /api/measurements/add

* **Method:**

  `POST`

* **Data Params**
  * **Content:** `{"name": "kilogram", "prefix": "kg"}`

* **Success Response:**

  * **Code:** 200 <br />

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/measurements/add",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```
