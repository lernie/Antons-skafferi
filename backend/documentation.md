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
    **Content:** `[{"id":1,"firstName":"123123","lastName":"Svensson","positionId":0,"userName":"ss1231ffff123","password":"password","email":"mmmf123@mail.com","startDate":"2017-10-05"}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/employee",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

<a name="addEmployee"/>

**Add Employee**
----
 Adds an employee 

* **URL**

  [url]/api/employee

* **Method:**

  `POST`

* **Data Params**

    **Content:** `{"firstName":"123123","lastName":"Svensson","positionId":0,"userName":"ss1231ffff123","password":"password","email":"mmmf123@mail.com"}`

* **Success Response:**

  * **Code:** 200 <br />


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/employee",
      dataType: "json",
      method : "POST",
      data: JSON.stringify({"firstName":"123123","lastName":"Svensson","positionId":0,"userName":"ss1231ffff123","password":"password","email":"mmmf123@mail.com"}),
      success : function(r) {
        console.log(r);
      }
    });
  ```


<a name="updateEmployee"/>

**Update Employee**
----
 Updates an employee 

* **URL**

  [url]/api/employee/{id}

* **Method:**

  `POST`

* **Data Params**

    **Content:** `{ "firstName" : "Sven", "lastName" : "Svensson", "positionId" : 0 },{ "id" : 13, "firstName" : "Erik", "lastName" : "Eriksson", "positionId" : 1 }`

* **Success Response:**

  * **Code:** 200 <br />


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/employee/{id}",
      dataType: "json",
      method : "POST",
      data: JSON.stringify({"firstName":"123123","lastName":"Svensson","positionId":0,"userName":"ss1231ffff123","password":"password","email":"mmmf123@mail.com"}),
      success : function(r) {
        console.log(r);
      }
    });
  ```


<a name="deleteEmployee"/>

**Delete Employee**
----
 Deletes an employee 

* **URL**

  [url]/api/employee/{id}

* **Method:**

  `DELETE`

* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/employee/{id}",
      method : "DELETE",
      success : function(r) {
        console.log(r);
      }
    });
  ```
<!-- 
=======================================================
    Food
=======================================================
-->

<a name="getAllFood"/>

**Get All Food**
----
 Returns a list of all food.

* **URL**

  [url]/api/food

* **Method:**

  `GET`

* **Data Params**

    None


* **Success Response:**

  * **Code:** 200 <br />

    **Content:** `[{"id":0,"name":"Gryta","foodTypeId":1,"timeToCook":30,"price":85},{"id":1,"name":"tjo","foodTypeId":2,"timeToCook":30,"price":85},{"id":2,"name":"tjenmare","foodTypeId":3,"timeToCook":30,"price":85},{"id":3,"name":"mmm","foodTypeId":1,"timeToCook":30,"price":85},{"id":4,"name":"fanta mezzo","foodTypeId":0,"timeToCook":30,"price":85}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/food",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

<a name="addFood"/>

**Add food**
----
 Add food .

* **URL**

  [url]/api/food

* **Method:**

  `POST`

* **Data Params**

    **Content:** `{"id":0,"name":"Gryta","foodTypeId":1,"timeToCook":30,"price":85}`


* **Success Response:**

  * **Code:** 200 <br />


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/food",
      method : "POST",
      data : JSON.stringify({"id":0,"name":"Gryta","foodTypeId":1,"timeToCook":30,"price":85}),
      success : function(r) {
        console.log(r);
      }
    });
  ```


<!-- 
=======================================================
    FoodType
=======================================================
-->

<a name="getFoodType"/>

**Get All FoodOrder**
----
 Return a list of food type .

* **URL**

  [url]/api/foodtype

* **Method:**

  `GET`

* **Data Params**

    None


* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{"id":0,"name":"starter"},{"id":1,"name":"main course"},{"id":2,"name":"dessert"},{"id":3,"name":"drink"}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/foodtype",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

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
    **Content:** `[{"id":0,"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0,"ready":1507597500540,"created":null,"delivered":null},{"id":1,"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0,"ready":1507597689373,"created":null,"delivered":null},{"id":2,"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0,"ready":1507597689697,"created":null,"delivered":null}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/foodorder",
      dataType: "json",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```



<a name="addMultipleFoodOrders"/>

**Add multiple food orders**
----
  Add a list of food order.

* **URL**

  [url]/api/foodorder

* **Method:**

  `POST`

* **Data Params**

    **Content:** `[{"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0},{"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0},{"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0,"ready":1507597689697}]`

* **Success Response:**

  * **Code:** 200 <br />


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/foodorder",
      dataType: "json",
      data : JSON.stringify([{"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0},{"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0},{"modification":"","foodId":0,"diningTableId":1,"orderStatusId":0,"ready":1507597689697}]),
      method : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```

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

  [url]/api/measurement

* **Method:**

  `GET`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `[{"name": "kilogram", "prefix": "kg"},{"name": "kilogram", "prefix": "kg"}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/measurement",
      dataType: "json",
      method : "GET",
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

  [url]/api/measurements

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
        method : "POST",
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
 Returns json data about all ingredients.

* **URL**

  [url]/api/ingredient

* **Method:**

  `GET`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `[{"id":0,"name":"Fisk","measurementId":1}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/ingredient",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```


<a name="addIngredient"/>

**Get Add Ingredient**
----
 Add ingredient

* **URL**

  [url]/api/ingredient

* **Method:**

  `POST`

* **Data Params**

 * **Content:** `{"id":0,"name":"Fisk","measurementId":1}`

* **Success Response:**

  * **Code:** 200 <br />
 

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/ingredient",
      dataType: "json",
      method : "POST",
      data : JSON.stringify({"id":0,"name":"Fisk","measurementId":1}),
      success : function(r) {
        console.log(r);
      }
    });
  ```



<!-- 
=======================================================
    Inventory
=======================================================
-->
<a name="addInventoryItem"/>

**Add inventory items**
----
 Add inventory item

* **URL**

  [url]/api/inventory

* **Method:**

  `POST`

* **Data Params**

 * **Content:** `{"ingredientId":0,"amount":15}`

* **Success Response:**

  * **Code:** 200 <br />
 

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/inventory",
      dataType: "json",
      method : "POST",
      data : JSON.stringify({"ingredientId":0,"amount":15}),
      success : function(r) {
        console.log(r);
      }
    });
  ```


<a name="getInventoryItems"/>

**Get All Inventory Item**
----
 Get all inventory items

* **URL**

  [url]/api/inventory

* **Method:**

  `POST`

* **Data Params**


* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `[{"ingredientId":0,"amount":15}]`


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/inventory",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```



<a name="updateInventoryItems"/>

**Update Inventory Item**
----
 Update inventory item

* **URL**

  [url]/api/inventory/{ingredientId}

* **Method:**

  `POST`

* **Data Params**
  * **Content:** `{"amount":15}`

* **Success Response:**

  * **Code:** 200 <br />



* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/inventory/1",
      method : "POST",
      dataType: "json",
      data : JSON.stringify({"amount":15}),
      success : function(r) {
        console.log(r);
      }
    });
  ```


<!-- 
=======================================================
    DiningTable
=======================================================
-->

<a name="getDiningTables"/>

**Get Dining Table**
----
 Get dining tables

* **URL**

  [url]/api/diningtable

* **Method:**

  `GET`

* **Data Params**


* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `[{"id":0,"name":"Table 1"},{"id":1,"name":"Table 2"},{"id":2,"name":"Table 3"},{"id":3,"name":"Table 4"},{"id":4,"name":"Table 5"},{"id":5,"name":"Table 6"}]`


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/diningtable",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

<!-- 
=======================================================
    Opening Hours
=======================================================
-->

<a name="getOpeningHours"/>

**Get Opening Hours**
----

 Get opening hours

* **URL**

  [url]/api/openinghour

* **Method:**

  `GET`

* **Data Params**


* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `[{"id":0,"day":"mon","openingTime":780,"closingTime":1260},{"id":1,"day":"fri","openingTime":1020,"closingTime":1380}]`


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/openinghour",
      method : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```