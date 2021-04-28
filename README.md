# M2 Backend Challenge

Run the docker image: `docker run -d -p 8080:8080 --name m2 docker.io/bergers/m2` or visit 
[http://3.66.231.145:8080](http://3.66.231.145:8080).
The best way to test is with Postman, but there's also a nice frontend page. 

The available endpoints are

Endpoint URL | Method | Decription
--- | --- | ---
/user | GET | Retrieve all user
/user | POST | Create a new user
/user/{id} | GET | Retrieve user
/user/{id} | PUT | Create or update user
/user/{id} | DELETE | Delete user
/userBreed | PUT | Assign breeds to user

Some security/safety concerns were neglected for this challenge. The app is deployed to AWS ECS for HTTP, not HTTPS. 
There's no login, so everybody can see all users, and the frontend page is missing some error handling,
for example for failed HTTP requests. Also, in production there would be a different database to persist the records.

The app utilises the popular Guava library, mostly for precondition checks in the controllers.

![CI/CD workflow](https://github.com/berger-devel/m2/actions/workflows/maven.yml/badge.svg)
[![Deploy to Amazon ECS](https://github.com/berger-devel/m2/actions/workflows/aws.yml/badge.svg)](https://github.com/berger-devel/m2/actions/workflows/aws.yml)
