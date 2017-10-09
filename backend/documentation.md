**[url]** = http://37.139.13.250:8080

## Table of Contents 



**JSON Web Token**
- [`POST` Login \[Under Development\]](#login)

**Employee**

<!-- Added but check documentation -->
- [`Get` All Employees ](#getAllEmployees) 

<!--Not added-->
- [`POST` Add Employee](#addEmployee)

- [`POST` Update Employee](#updateEmployee)

- [`DELETE` Employee](#deleteEmployee)

**Food**

- [`GET` All Food](#getAllFood)

- [`POST` Add Food](#addFood)

**FoodType**

- [`GET` FoodType](#getFoodType)


**Food Order**

- [`GET` All Food Orders](#getFoodOrder)

- [`POST` Add Multiple Food Orders](#addMultipleFoodOrders)

**Measurement**

- [`GET` All Measurements](#getAllMeasurements)

- [`POST` Add Measurement](#addMeasurement)

**Ingredient**

- [`GET` Get All Ingredients](#getAllIngredients)

- [`POST` Add Ingredient](#addIngredient)

**Inventory**

- [`POST` Add Inventory Item](#addInventoryItem)

- [`GET` Get Inventory](#getInventoryItems)

- [`POST` Update Inventory Item](#updateInventoryItems)

**Dining Table**

- [`GET` All Dining Tables](#getDiningTables)

**Opening Hours**

- [`GET` All Opening Hours](#getOpeningHours)



<!-- 
=======================================================
    JSON WEB TOKEN
=======================================================
-->



<a name="login"/>

**Login**
----
  Returns a JSON Web Token used for authentication.

* **URL**

  [url]/api/login

* **Method:**

  `POST`

* **Data Params**
  * **Content:** `{"username": "username123", "password": "pass123"}`
  
* **Success Response:**
  
  * **Code:** 200 <br />

  * **Header:** `Authorization: Bearer zfkz0135999z9c9z99cz99c...`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/login",
      dataType: "json",
      type : "POST",
      data : {"username": "username123", "password": "pass123"},
      headers: { "Authorization": "Bearer zfkz0135999z9c9z99cz99c..." },
      success : function(r) {
        console.log(r);
      }
    });
  ```




<!-- 
=======================================================
    EMPLOYEE
=======================================================
-->

<a name="getAllEmployees"/>

**Get All Employees 😃**
----
  Returns json data about all employees.

* **URL**

  [url]/api/employee

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

<a name="addEmployee"/>

**Add Employee**
----

<a name="updateEmployee"/>

**Update Employee**
----


<a name="deleteEmployee"/>

**Delete Employee**
----

<!-- 
=======================================================
    Food
=======================================================
-->

<a name="getAllFood"/>

**Get All Food**
----

<a name="addFood"/>

**Get All FoodOrder**
----


<!-- 
=======================================================
    FoodType
=======================================================
-->

<a name="getFoodType"/>

**Get All FoodOrder**
----


<!-- 
=======================================================
    Food Order
=======================================================
-->


<a name="getFoodOrder"/>

**Get All FoodOrder**
----
  Returns json data about all food orders.

* **URL**

  [url]/api/foodorder

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



<a name="addMultipleFoodOrders"/>

**Add multiple food orders**
----

<!-- 
=======================================================
    Measurement
=======================================================
-->



<a name="getAllMeasurements"/>

**Get All Measurements**
----
  Returns json data about all measurements.

* **URL**

  [url]/api/measurement/getall

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
<a name="addMeasurement"/>

**Add Measurement**
----
  Add a measurement.

* **URL**

  [url]/api/measurements/add

* **Method:**

  `POST`

* **Data Params**
  * **Content:** `{"name": "kilogram", "prefix": "kg"}`

* **Success Response:**

  * **Code:** 200 <br />

* **Sample Call:**

  ```javascript
    $.ajax({
        url: "http://37.139.13.250:8080/api/measurement/add",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify({"name": "kilogram", "prefix": "kg"}),
        type : "POST",
        success : function(r) {
            console.log(r);
        }
    });
  ```



<!--=======================================================
Ingrdients
=======================================================-->

<a name="getAllIngredients"/>

**Get All All Ingredients**
----

<a name="addIngredient"/>

**Get Add Ingredient**
----




<!-- 
=======================================================
    Inventory
=======================================================
-->
<a name="addInventoryItem"/>

**Add inventory items**
----

<a name="getInventoryItems"/>

**Get Inventory Item**
----

<a name="updateInventoryItems"/>

**Update Inventory Item**
----



<!-- 
=======================================================
    DiningTable
=======================================================
-->

<a name="getDiningTables"/>

**Get Dining Table**
----


<!-- 
=======================================================
    Opening Hours
=======================================================
-->

<a name="getOpeningHours"/>

**Get Opening Hours**
----