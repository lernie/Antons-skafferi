**Get All Measurements**
----
  Returns json data about all measurements.

* **URL**

  /api/measurements/getall

* **Method:**

  `GET`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "http://37.139.13.250:8080/api/measurements/getall",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```