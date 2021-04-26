# M2 Backend Challenge

Run the docker image: `docker run -d -p 8080:8080 --name m2 bergers/m2`.
The best way to test is with Postman. 

The available endpoints are

Endpoint URL | Method | Decription
--- | --- | ---
/user | POST | Create a new User
/user/{id} | GET | Retrieve user
/user/{id} | PUT | Update user
/user/{id} | DELETE | Delete user
/userBreed | PUT | Assign breed to user
/userBreed | DELETE | Remove breed from user
