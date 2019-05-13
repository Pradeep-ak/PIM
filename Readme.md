## PIM
PIM is Product Information Management system. It is help full in maintaining the information of the product which is one of the backend system of many Important systems like E-Commerce site, Fulfillment system etc.
This system is addition to already existing PIM system, most of the system out there are paid and closely connect to one of the system. With below system, we tried to make this system independent and easy plugin buildable to communicate to other system in the eco-system.

_ _ It is built on micro service stack (Full Stack), which make it more easily scalable and zero downtime system._ _

Let jump into project and find answer to few basic like:

# What is the architecture of the system?
We have N different micro service which are framed based of the functionality they add to system.

Below is list of the micro services which frame the PIM system.

- **_Catalog Service_**: It is prime service, which act as orcistator layer as well as the handle the functionality with catalog data (adding, updating, deleting and fetching).
- **_Audit Service_**: This layer adds the auditing functionality to the system. It maintains the audit records for the changes in the catalog data (which include who changed, what was changed).
- **_Search Service_**: This layer adds the searching capability for the catalog data, it is powered by the Elastic search engine. As the data in present in ES, it also provides the report capacity and data analytic capacity with kibana adding to visualize data.
- **_Spark service_**: It is service is mainly to feeding the data into and out of the system. It uses the spark batch processing capabilities to import 1st time data into system, and spark streaming capabilities to process continues input feeds data. It also provides is feature to refresh the elastic search data using data from cassandra.
- **_Kafka_**: It is under lying messaging system between different system, also works as are secondary system for communication different system use it as failback channel. Also, it is primary channel for spark service to consume and publish the data feed to external system.
- **_OAuth Service_**: It is service to handle the user data and maintains role based access. It is primary responsible for authenticating/creating users. It acts as the OAuth service & use the JWT tokens manager to identify and security. It can be integrated with the external IDM system.
- **_Config Service_**: It is to maintain configuration related different service. it uses github to maintain different configuration setting based of label and env. Internal it also uses the RabbitMQ messaging system to provide auto update of configuration changes feature.
- **_Eureka discovery service_**: It is standard discovery service provided by Netflix, it helps in load balancing and discovering different service to communicate between service.
- **_Zuul Service_**: It act as gateway service and proxy layer. It also provides the authentication for all the api calls & works as resource server.
- **_Redux/React OR Angular service_**: These servers are frontend server, which consume the endpoint data and paint the UI for the users. Any of these modules can be used. We personal recommend Angular as it is single page application.


# How to install the application in local/cloud?
All the micro service including the databases application are docker containers. Only pre-request for installing and run the whole application in local machine.

- **_Should have MVN, JDK 1.8 Installed._**
- **_Should have Docker installed and should be running._**

Below is the list of steps to perform run the application.
- Clone the git repository.
```git clone <git repo url>```
- Build the project using MVN command.
```mvn clean install```
- Build and run the docker-compose command.
```Docker-compose up --build```
- Access the application with docker host.
```http://<docker-hostname>/```

We are in process of create the file required to docker-swarm and kubernates to make the docker orchestration easy.

# How to do monitoring & reporting of data?
One of the import aspect of PIM system should be monitor of the data and system as in many case PIM will be master system to maintain product data. We have open source plugin in kibana called sential which provides the feature for setting up alert for any given threshold breach. Which we can setup for measuring different parameter and configuring for respective threshold.

It also provides the feature for setting up rich report (which can have data and trend and graphical representation of data) which are scheduled for run at a given period.

# What are the different features in PIM system?
It provides several features related to creating, maintaining and disturbing of the product related information.
- Role based access of data to view, create and update.
- Asset level life cycle auditing.
 - Stream/batch based external system integration for updating data in & out of the system.
 - Provide search functionality which is useful for narrow down result and plot trend and data analysis.

> Please add you mail id in comment section or mail me : pradeep4jobs@gmail.com
> You can see more details around design [Overview and Other Details](https://pradeepakulkarni.blogspot.com/2018/10/product-information-management.html)
