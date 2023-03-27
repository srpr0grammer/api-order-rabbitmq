<br>
<h1 align="center">
APi-Cliente + Docker.
</h1>
<br>

## 💬 About the repository

This project is an order management API that utilizes two tables (order and product). 
The project was developed with RabbitMQ for asynchronous communication and MySQL as the database.

⚠ Prerequisites to run the project You will need to have docker and docker-compose installed on your machine.
* docker
* docker-compose


## 📌 How to use?
1. Clone the project repository:
```
git clone git@github.com:srpr0grammer/api-order-rabbitmq.git
```

2. Start the application using Docker Compose:
```
docker-compose up
```

3. Accessing the endpoints via Swagger: To access the API endpoint documentation and test them directly in your browser, use the following URL:
```
http://localhost:port/swagger
```
Here, you will find all available endpoints for the order and product tables. You can test the endpoints directly in Swagger by providing the necessary parameters and clicking the "Execute" button for each endpoint.

Good luck exploring and using the Order Management API!


---