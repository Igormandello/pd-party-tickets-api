# PD Party Tickets API

#### An API to serve the [PD Party Tickets Flutter application](https://github.com/Igormandello/pd-party-tickets)

---

### Setting the things up
First of all, you have to clone this repo
```
$ git clone https://github.com/Igormandello/pd-party-tickets-api
```

After cloning it, you must create two required configuration files, both named 
application.conf, one in the `pd-party-tickets-api-server` module and the other
in `pd-party-tickets-api-service`.
```
$ cd pd-party-tickets-api/
$ touch pd-party-tickets-api-server/src/main/resources/application.conf
$ touch pd-party-tickets-api-service/src/main/resources/application.conf
```

In the first file, you have to set some properties:
```
admin.username=<your_username>
admin.password=<your_password>
```
This will be your first user, other users can be created using this user
token (you may use the `/v1/auth/login` route to get your token) 

In the second file, you have to set just one property:
```
authentication.secret=<your-256+bit-secret>
``` 
This will be the secret that will be used to generate the JWTs

### Starting the server
Now you're ready to go! You just have to run the `Application.kt` file inside the
`pd-party-tickets-api-server` and use the endpoints.