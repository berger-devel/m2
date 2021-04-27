# M2 Backend Challenge

Visit [https://m2.stefan-berger.me:8080](https://m2.stefan-berger.me:8080) or run the docker image: `docker run -d -p 8080:8080 --name m2 docker.io/bergers/m2`.
The best way to test is with Postman, but there's also a nice frontend page. 

The available endpoints are

Endpoint URL | Method | Decription
--- | --- | ---
/user | POST | Create a new User
/user/{id} | GET | Retrieve user
/user/{id} | PUT | Create or update user
/user/{id} | DELETE | Delete user
/userBreed | PUT | Assign breeds to user

Some security/safety concerns were neglected for this challenge. The app is deployed to AWS ECS for HTTP, not HTTPS. 
There's no login, everybody can see all users, and the frontend page is missing some error handling,
for example for failed HTTP requests. Also, in production there would be a better test coverage.

![CI/CD workflow](https://github.com/berger-devel/m2/actions/workflows/maven.yml/badge.svg)
[![Deploy to Amazon ECS](https://github.com/berger-devel/m2/actions/workflows/aws.yml/badge.svg)](https://github.com/berger-devel/m2/actions/workflows/aws.yml)
