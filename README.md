[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) 

# Mediscreen
> Mediscreen specializes in detecting risk factors for disease. Our screenings using predictive analysis of patient populations at an affordable cost.
***
:warning: ***4 modules are required in order for the Mediscreen application to function :***
- Front-end app
- [Patients Micro-Service](https://github.com/gwnll/Mediscreen_Patients)
- [Notes Micro-Service](https://github.com/gwnll/Mediscreen_Notes)
- [Reports Micro-Service](https://github.com/gwnll/Mediscreen_Reports)
***
## Mediscreen_Front
Mediscreen_Front is the front-end of the application. The front interacts with the 3 REST API in order to handle the patients list, their respectives notes and the generation of the reports.

### Technologies
- Java 11
- Maven 3.8.1
- Spring 2.6.7
- Docker
- Postgre
- MongoDB

### Deployment with Docker
1) Build the jar (you can use ``mvn install``)
2) Create the image of the front module with ``build -t mediscreen-front``
3) Once the images of the other micro-services are created, you can use ``docker compose up`` in the front app.
