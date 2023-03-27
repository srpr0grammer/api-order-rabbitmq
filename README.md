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

## 💬 About the AWS
Here are all the steps in English for deploying your application and its dependencies (RabbitMQ and MySQL) on AWS using Amazon ECS (Elastic Container Service) and Amazon RDS (Relational Database Service):
1. Create a repository in Amazon ECR (Elastic Container Registry)

   Access the AWS Management Console and go to the Amazon ECR service.
   Create a new repository to store your application's Docker image.


2. Build and push the Docker image to Amazon ECR

   Log in to the AWS CLI and run the aws ecr get-login-password command to get the ECR password.
   Log in to ECR using docker login, with the command provided in the previous step.
   Build your application's Docker image and tag it with the URI of the repository created in Amazon ECR:


3. Start the application using Docker Compose:
```
docker build -t your_ecr_repository_uri .
```
4. Push the Docker image to Amazon ECR:
```
docker push your_ecr_repository_uri
```
5. Create an Amazon RDS instance for MySQL

   Access the AWS Management Console and go to the Amazon RDS service.
   Click on "Create database" and select "MySQL" as the database engine.
   Configure the instance options, such as instance type, storage, database name, username, and password.
   Configure the network options, such as VPC, subnets, and security groups.
   Create the database instance.


6. Create an Amazon MQ instance for RabbitMQ

   Access the AWS Management Console and go to the Amazon MQ service.
   Click on "Create broker" and select "RabbitMQ" as the messaging engine.
   Configure the broker options, such as instance type, storage, and username and password.
   Configure the network options, such as VPC, subnets, and security groups.
   Create the broker instance.


7. Set up Amazon ECS

   Access the AWS Management Console and go to the Amazon ECS service.
   Create a new ECS cluster by choosing the "EC2 Linux + Networking" cluster type.
   Configure the cluster options, such as EC2 instance type, the number of instances, and the VPC.
   Create the ECS cluster.


8. Create an ECS task definition for the application

   In the Amazon ECS console, go to "Task Definitions" and click on "Create new Task Definition".
   Select the "EC2" launch type and configure the task definition.
   Add a container to the task definition, specifying the Docker image URI in Amazon ECR, environment variables, port mappings, and other necessary parameters.
   Create the task definition.


9. Create an ECS service for the application

   In the Amazon ECS console, go to your created cluster's page and click "Create" in the "Services" section.
   Select the previously created task definition and configure the service options, such as the desired number of tasks and deployment strategy.
   Configure the load balancer, if needed. This can be helpful if you want to distribute traffic between multiple instances of your application.
   Configure the network options, such as VPC, subnets, and security groups.
   Create the service.

Now, Amazon ECS will deploy the application according to the task definition and service settings. Amazon RDS and Amazon MQ will handle the database (MySQL) and asynchronous communication (RabbitMQ) dependencies, respectively.
11. Verify and test the deployment

```
Access the Amazon ECS console and check if the service has been deployed successfully.
```
   
---