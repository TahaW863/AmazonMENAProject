# PROJECT REPORT

Video Link: https://youtu.be/Do6Egp7oqdo
Backend developer: Taha Gharaibeh, LinkedIn: https://www.linkedin.com/in/taha-gharaibeh-77733b1a2/
Frontend developers:
1- Yazan Radaideh, https://www.linkedin.com/in/yazan-radaideh-3805b4201
2- Mohammad Al-Smadi, LinkedIn: https://www.linkedin.com/in/mohammad-al-smadi-35a0171a1

## INTRO
we would like to show you what we think about features to improve shopping and delivery experience for Amazon customers in MENA (Middle East and North Africa) region. 
As you may know Amazon operates on first world standards, that’s why Amazon delivery system assumes things might not be true like having a box in front of your house or an accurate address for the customers or simply a ring for your room or the apartment. Most of the buildings in this region don’t have a ring to call or inform the customer about the delivery guy presence, and not to mention how big that building could be. 

Our system is going to allow Amazon to expand in this region by having delivery management system that suits the case. 
We would like to divide this region even further 
Class A: where we have Amazon depots 
And 
Class B where we don’t 
We want to make amazon work in the class B region effectively, to reach as fast as possible to be classified as A.
In the class A, and after receiving the package at the nearest depot to customer. The Amazon fleet will load orders.

For class B it’s a bit different, after knowing in which delivery post is the package. Amazon will have a portal to allow third parties and anonymous drivers to be part of next process.

Let’s name them amazon buddies, these ppl will be responsible on delivering the packages to customers according to our delivery management system.
Our system as you can see have actions, we will have informative notifications about who’s your buddy today! And how he can interact with you. A day before the delivery to taking place, the system will ask using SMS or notification to the customer If he wants his delivery to be delivered the same or next day. 
Then our buddy after he reaches his destination, he can call or chat with customer and in a secured way like having in between amazon company phone number as proxy.  If there’s no response from our customer and our buddy must send the package to the nearest hotspot.

Hotspot is our term to name a trusted place to put the package in, the place could be Amazon box or a Market place near the customer. We will send a QR Code via the app and email to be used by the customer to get his package.

Each package should follow amazon packaging standards by having a common place in that city to do so. Once it’s ready, then it’s out for delivery by our buddies.
This procedure is also applicable on amazon fleet for class A.

We allow the PRIME members to choose which day and a range of 3 hours in the week to get his package. 
The buddies can register on amazon delivery management system if the meet our system delivery criteria. 
Our Tech behind the system
# TECH
Our system is built on a very modern real-time, distributed system. The architecture is no different, microservices with Java, Kafka, MongoDB, and each plays its role. Java for building the business logic and services, Kafka acts as our fast layer and Kafka streams as our slow layer to make the dataflow seamless. MongoDB, we used it to make our stack stateful and store the relevant data. 

We have several microservices, as we can see for customer, and truck application.

Our app with some UIs 
Like any trackable app
we have login, and …
