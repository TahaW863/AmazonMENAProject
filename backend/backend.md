# PLEASE NOTE
We will replace any service that amazon already have to save cost (time, money), we used firebase for the services, and twilio for sms & call service when you can use amazon SNS. We open a channel on request to chat by the driver to customer. we didn't integrate the auth service due to the limited time, we wanted to use in future OAuth 2.0. 

# BACKEND Services
Used technologies like Java, Kafka, MongoDB, and each plays its role. Java for building the business logic and services, Kafka acts as our fast layer and Kafka streams as our slow layer to make the dataflow seamless. MongoDB, we used it to make our stack stateful and store the relevant data. 

Java services are auth, call-sms, customer/driver infromation, orders/products, notifications, and realtime-chat service. 

# Documentation
The documentation can be found under the link IP:{servicePort}/swagger-ui/#/

services ports as following:
 * customerInformationService: 8080
 * callSmsService: 8081
 * orderProductService: 8082
 * webFluxRealTimeChatService: 8083

The auth is still not ready. Notification is only used within the project, we still didn't intergate it.


# Docker

An image was prodcued by docker for each image. you can find different docker-compose for building, deploying on aws, and more.

