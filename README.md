# Agrokart Backend Core Java Project

## Problem Statement

Agriculture is the backbone of India. We all know how the farmers are facing many issues at this time. I am going to create a web application to help farmers. The major problem faced by farmers was that they couldn’t get fair prices for their vegetables and fruits. And also, they are facing transportation problems while getting cultivated vegetables and selling them in the market. They need four-wheeler vehicles to bring the produced vegetables to the wholesale market or daily market. If the cultivated crops are in massive amounts.

## Solution

I will develop a website where the farmer can sell their products, and end customers can buy the products and get to their doorstep. If some other organizations want, they can also buy.

## About this repo:

This repository contains the backend code for the Agrokart web application. The frontend development is handled separately in the following repository: [Link to Frontend Repository](https://github.com/fssa-batch3/hemanath.muralikrishnan__web_project/tree/dev).

The backend is developed using Core Java and integrated with an SQL database for data storage.

## Milestone -1

In Milestone-1, we will focus on implementing the backend logic and functionalities to support the Product CRUD operations.

### Key Tasks

1. [ ] **Product Model**: Design a Product model to represent the properties of a product, such as name, description, price, quantity, and any other relevant attributes.

2. [ ] **Create Product**: Implement a function that allows adding products to the platform. Users can provide product details, and the backend will store the product information in the database.

3. [ ] **Read Products**: Implement a function to retrieve a list of all available products.

4. [ ] **Read Products by Name**: Implement a function to filter products by name and retrieve a list of products matching the provided name.

5. [ ] **Update Product**: Implement a function that enables updating the details of products. Users can modify product information such as name, description, price, and quantity.

6. [ ] **Delete Product**: Implement a function that allows removing a product from the platform.

7. [ ] **Validation and Error Handling**: Implement validation checks on input data to ensure that only valid and complete product information is accepted. Handle errors gracefully and provide appropriate error responses when necessary.

8. [In Progress] **Unit Testing**: Write comprehensive unit tests using JUnit to ensure the correctness and robustness of the Product CRUD operations. Test-edge cases and error scenarios to verify that the backend functions as expected.

## Product Database Design

You can find the SQL script for creating the Product table in the following location:

- [Product SQL Script](src/main/sql/productscripts.sql)

<div align="center">
  <img src="readme_resources/product_database_design.png" alt="Product Database Design" width="80%">
</div>

<hr>

## Unit Testing

For comprehensive unit testing, including test cases for all the operations, please refer to the "tests" directory:

- [Unit Test Cases](src/test/java)

We use JUnit for writing these test cases to ensure the correctness and robustness of our backend functions.

## Code Quality Analysis

We have integrated our project with SonarCloud to perform code quality analysis and static code analysis. You can find the overall analysis report on the SonarCloud platform:

- [SonarCloud Analysis](https://sonarcloud.io/summary/overall?id=fssa-batch3_sec_c_sec_c_hemanath.muralikrishnan__corejava_project_2)

### Tech Stack

- Core Java
- SQL Database
- JUnit (for unit testing)
- SonarCloud (for code quality analysis and static code analysis)
