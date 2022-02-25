<h3 align="center">Find Nearest Shops</h3>

## Table of Contents

* [Notes](#notes)
    * [Request Format](#request-format)
    * [Response Format](#response-format)
* [Built With](#built-with)
* [Getting Started](#getting-started)
    * [Clone](#clone)
    * [Build ](#build-the-project)

## Request / Response Format

---

### Request Format
```java
{
  "location": {              // Current customer location
    "latitude": 42.546245,   // Current latitude
    "longitude": 1.601554    // Current longitude
  },
  "limit": 1                 // Number of shops to return
}
```

### Response Format
```java
[
  {
    "id": 1,                  // Shop ID
    "name": "Shop name",      // Shop name
    "location": {             // Shop location
      "latitude": 42.546245,  // Shop latitude
      "longitude": 1.601554   // Shop longitude
    },
    "distance": 10.0          // Distance from customer location
  },
  {
    "id": 2,
    "name": "Shop 2 name",      
    "location": {
      "latitude": 45.546245,
      "longitude": 1.701554
    },
    "distance": 12.0
  },
]
```

## Notes

---

- This solution is a simple implementation.
- Assume `ShopController` would call `ShopService#getShops`
- To fully complete this implementation things I would consider:
  - Use Spring Boot with all Spring Data, Validation, MVC, etc. capabilities
  - Use a real database for production and H2 for local development
  - Implement proper controllers, services, repositories
  - Add necessary validations for DTOs
  - Depending on the use case implement authentication using Spring Security
  - Consider performance issues:
    - Currently `ShopRepository` is using dummy data and it isn't a large dataset.
    - If we need to calculate all shop distance data, we might need to consider parallel calculations.
    - Handover processing to DB to return data we need without any processing in the application layer.
    - In a real-world scenario we might end up having thousands of shops stored in a DB 
    and we don't want to load thousands of shops to memory to process.
    - Limit and pagination can be used.
    - Introduce caching.


## Built With

---

* Java 17
* Gradle

## Getting Started

---

**Clone the repo and build the project:**

### Clone:
```
git clone git@github.com:huseyinkombayci/nearest-shops.git
```

### Build the project:
```
./gradlew clean build
```