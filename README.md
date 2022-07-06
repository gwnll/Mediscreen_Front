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
- Java 1.8 JDK (Java 8)
- Maven
- Spring 2.1.6
- Docker
- MySQL

### Deployment with Docker
1) Build the 3 different jar
2) Create the 3 different images with docker ``build -t (image_name)``
3)
