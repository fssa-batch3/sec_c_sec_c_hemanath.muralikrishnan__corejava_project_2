# Agrokart Backend Core Java Project

## Problem Statement

Agriculture is the backbone of India. We all know how the farmers are facing many issues at this time. I am going to create a web application to help farmers. The major problem faced by farmers was that they couldn’t get fair prices for their vegetables and fruits. And also, they are facing transportation problems while getting cultivated vegetables and selling them in the market. They need four-wheeler vehicles to bring the produced vegetables to the wholesale market or daily market. If the cultivated crops are in massive amounts.

## Solution

I will develop a website where the farmer can sell their products, and end customers can buy the products and get to their doorstep. If some other organizations want, they can also buy.

## About this repo:

This repository contains the backend code for the Agrokart web application. The frontend development is handled separately in the following repository: [Link to Frontend Repository](https://github.com/fssa-batch3/hemanath.muralikrishnan__web_project/tree/dev).

The backend is developed using Core Java and integrated with an SQL database for data storage .

### Project Structure

The core components of the backend are organized as follows:

- [src/main/java/com/fssa/agrokart/model](src/main/java/com/fssa/agrokart/model): This directory contains the model classes that represents the objects.

- [src/main/java/com/fssa/agrokart/service](src/main/java/com/fssa/agrokart/service) : This directory contains service classes that implement business logic for the Agrokart web application.

- [src/main/java/com/fssa/agrokart/dao](src/main/java/com/fssa/agrokart/dao): The Data Access Object (DAO) classes are stored in this directory. The DAO classes handle interactions with the database and provide methods for CRUD operations.

- [src/test/java/com/fssa/agrokart](src/test/java/com/fssa/agrokart) : This directory contains unit test cases for the Agrokart backend.

<hr>

## Milestone -1 

In Milestone-1, we will focus on implementing the backend logic and functionalities to support the Product CRUD operations.

### Key Tasks

1. [X] **Product Model**: Design a Product model to represent the properties of a product, such as name, description, price, quantity, and any other relevant attributes.

2. [X] **Create Product**: Implement a function that allows adding products to the platform. Users can provide product details, and the backend will store the product information in the database.

3. [X] **Read Products**: Implement a function to retrieve a list of all available products.

4. [X] **Read Products by Name**: Implement a function to filter products by name and retrieve a list of products matching the provided name.

5. [X] **Update Product**: Implement a function that enables updating the details of products. Users can modify product information such as name, description, price, quantities and any other relevant attributes.

6. [X] **Delete Product**: Implement a function that allows removing a product from the platform.

7. [X] **Validation and Error Handling**: Implement validation checks on input data to ensure that only valid and complete product information is accepted. Handle errors gracefully and provide appropriate error responses when necessary.

8. [X] **Unit Testing**: Write comprehensive unit tests using JUnit to ensure the correctness and robustness of the Product CRUD operations. Test-edge cases and error scenarios to verify that the backend functions as expected.

## GitHub Issue - Milestone - 1:
Link to GitHub Issue: [Milestone -1 GitHub Issue](https://github.com/fssa-batch3/sec_c_sec_c_hemanath.muralikrishnan__corejava_project_2/issues?q=is%3Aopen+is%3Aissue+milestone%3A%22MileStone+-+1%22)
<hr>

## Code Quality Analysis

We have integrated our project with SonarCloud to perform code quality analysis and static code analysis. You can find the overall analysis report on the SonarCloud platform:

- [SonarCloud Analysis](https://sonarcloud.io/summary/overall?id=fssa-batch3_sec_c_sec_c_hemanath.muralikrishnan__corejava_project_2)

### Tech Stack

- Core Java
- SQL Database
- JUnit (for unit testing)
- SonarCloud (for code quality analysis and static code analysis)
