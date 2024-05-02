# AdviseIT
## Capstone Part 1 Project

![AdviseIT Logo](src/main/resources/static/images/logo.png)

AdviseIT is a course advising tool created for assisting Green River College advising for the Associate of Applied Science in Data Analytics and Software Development degree program.
The Project is built using Java and Spring Boot. It features a robust API that delivers sorted course information. This project serves as Part 1 to my GRC Capstone Project.

## Authors
- Dahlia Claire (ind1goDusk)
- David Kurilla (davidkurilla)

## Try AdviseIT
Use Docker to pull the image from DockerHub
```shell
docker pull davidkurilla/adviseit
```
Run the image in a container
```shell
docker run --name adviseit -p 8081:8080 -d davidkurilla/adviseit
```

Open a web browser and navigate to the following URL:
```
http://localhost:8081
```

To demo the schedule builder navigate to the following URL:
```
http://localhost:8081/schedule-builder
```
