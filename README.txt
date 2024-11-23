Product Catalog API 
--------------------------
This project implements a RESTful API for managing a product catalog. 
The product catalog supports complex product attributes like categories, 
availability, ratings, and additional attributes. The backend is built with 
Java using Spring Boot and MongoDB as the database.

Overview
------------
This API allows users to manage products, perform searches with filters (e.g., by name, category, or attributes), and rate products. 
MongoDB is used for storing product data, taking advantage of its flexible, document-based storage for complex nested data structures.

Prerequisites
-----------------
Before setting up the application, ensure the following are installed:

--Java 11 or higher: Required for running the Spring Boot application.
--Maven: For building and running the Java application.
--MongoDB: A local or remote MongoDB instance is required for storing the data.
--Postman: For testing the API Endpoints.
--IntelliJ IDEA: This project is best developed using IntelliJ IDEA, 
	        which offers excellent support for Spring Boot and Java development. 
	        You can download IntelliJ IDEA from JetBrains.
--Spring Initializr: Spring Initializr is a tool for generating Spring Boot projects. 
	             If you're setting up the project from scratch, use the Spring Initializr 
	             to quickly generate the base project with Spring Boot and MongoDB dependencies.



Setup Instructions
----------------------
	1) Clone the Repository.
	
	2) Install Dependencies:
	    Install required dependencies using Maven.
	    mvn install.

	3) Configure MongoDB:
	     Make sure MongoDB is running. You can either:

	     **Install MongoDB locally: MongoDB Installation Guide
	     **Use a hosted MongoDB instance, such as MongoDB Atlas.
	     Configure your MongoDB connection in the application.properties file. 
	     Update the following properties based on your MongoDB setup:
	
	      spring.data.mongodb.uri=mongodb://localhost:27017/productdb //If you are using Atlas then provide your Atlas URI followed by /your_database_name

	4) Build and Run the Application:
	     
		mvn spring-boot:run

	    This will start the application, and the API will be accessible at http://localhost:8080.

API Endpoints
-----------------
1. Create a Product:

	Endpoint: POST /api/products
	Description: Adds a new product to the catalog.
	Request Body:
	
	"id": "product123"
	"name": "Laptop",
 	"description": "High-performance laptop for gaming and development.",
 	"price": 1200.99,
  	"categories": [
  	  "Electronics",
   	  "Computers"
  	],
  	"attributes": [
   	 {
      	"brand": "Dell"
    	},
    	{
      	"color": "Black"
    	},
    	{
      	"size": "15 inches"
    	}
  	],
  	"availability": {	
    	"instock": true,
    	"quantity": 50
  	},
  	"ratings": []	
 
2. Get Product by ID or All:

	Endpoint: GET /api/products/{id} or  GET /api/products
	Description: Retrieves a product by its unique ID.
	Response:

	"id": "product123"
	"name": "Laptop",
 	 "description": "High-performance laptop for gaming and development.",
 	 "price": 1200.99,
  	"categories": [
  	  "Electronics",
   	  "Computers"
  	],
  	"attributes": [
   	 {
      	"brand": "Dell"
    	},
    	{
      	"color": "Black"
    	},
    	{
      	"size": "15 inches"
    	}
  	],
  	"availability": {	
    	"instock": true,
    	"quantity": 50
  	},
  	"ratings": []

3. Update Product by ID:

	Endpoint: PUT /api/products/{id}
	Description: Updates a product's details by ID.
	Request Body:

	"name": "Updated Product",
 	 "description": "Updated description of the product.",
 	 "price": 1200.99,
  	"categories": [
  	  "Electronics",
   	  "Computers"
  	],
  	"attributes": [
   	 {
      	"brand": "Dell"
    	},
    	{
      	"color": "Black"
    	},
    	{
      	"size": "15 inches"
    	}
  	],
  	"availability": {	
    	"instock": true,
    	"quantity": 50
  	},
  	"ratings": []

4. Delete Product by ID:
	
	Endpoint: DELETE /api/products/{id}
	Description: Deletes a product by its ID.

5. Search Products:

	Endpoint: GET /api/products/search
	Query Parameters:
		name: Filter by product name.
		category: Filter by product category.
		attributes: Filter by specific attribute values.
	Example Request:
		GET /api/products/search?name=Laptop&category=Electronics&attributes_brand=Dell

	Response:

	"id": "product123"
	"name": "Laptop",
 	"description": "High-performance laptop for gaming and development.",
 	"price": 1200.99,
  	"categories": [
  	  "Electronics",
   	  "Computers"
  	],
  	"attributes": [
   	 {
      	"brand": "Dell"
    	},
    	{
      	"color": "Black"
    	},
    	{
      	"size": "15 inches"
    	}
  	],
  	"availability": {	
    	"instock": true,
    	"quantity": 50
  	},
  	"ratings": []


6. Rate a Product:

	Endpoint: POST /api/products/{id}/ratings
	Description: Adds a rating to a product.
	Request Body:	
	
	{
  	"userID": "user123",
  	"rating": 4,
  	"comment": "Great product!"
	}


	Response:

	"id": "product123"
	"name": "Laptop",
 	"description": "High-performance laptop for gaming and development.",
 	"price": 1200.99,
  	"categories": [
  	  "Electronics",
   	  "Computers"
  	],
  	"attributes": [
   	 {
      	"brand": "Dell"
    	},
    	{
      	"color": "Black"
    	},
    	{
      	"size": "15 inches"
    	}
  	],
  	"availability": {	
    	"instock": true,
    	"quantity": 50
  	},
  	"ratings": [
	{
  	"userID": "user123",
  	"rating": 4,
  	"comment": "Great product!"
	}
	]


7. Get All Products with Pagination:


	Endpoint: GET /api/products/search
	Query Parameters:
		page: The page number (for pagination).
		size: The number of products per page.
		sort: The field to sort by (e.g., price, name).
		order: The sort order (asc or desc).
	Example Request:

		GET /api/products/search?page=0&size=10&sort=price&order=asc

	Response:
	
	"id": "product123"
	"name": "Laptop",
 	"description": "High-performance laptop for gaming and development.",
 	"price": 1200.99,
  	"categories": [
  	  "Electronics",
   	  "Computers"
  	],
  	"attributes": [
   	 {
      	"brand": "Dell"
    	},
    	{
      	"color": "Black"
    	},
    	{
      	"size": "15 inches"
    	}
  	],
  	"availability": {	
    	"instock": true,
    	"quantity": 50
  	},
  	"ratings": [
	{
  	"userID": "user123",
  	"rating": 4,
  	"comment": "Great product!"
	}
	]

	"page": {
    	    "size": 5,
    	    "totalElements": 1,
    	    "totalPages": 1,
    	    "number": 0
    	}
	
Database Setup
------------------

MongoDB Setup:
	Install MongoDB if you don't have it already.
	https://docs.mongodb.com/manual/installation/
	
	Database Connection: Configure the application.properties file to point to your MongoDB instance. 
			   For local MongoDB, it will look like this:
				spring.data.mongodb.uri=mongodb://localhost:27017/productdb
	
	Create Database: MongoDB will automatically create the database productdb once the application connects and starts saving data.


Running the Application
-----------------------------

1) Start MongoDB: Ensure MongoDB is running. You can start it with:
		
	mongod

2) Build and Run the Application: In the project directory, run:

	mvn spring-boot:run

	The application should be accessible at http://localhost:8080.
