# SURL
URL Shortener
User: admin
password: 123deoliveira4

## Public EndPoints

	GET  /{uid}     - forwards to the registred link 
	POST /shortUrls - Create a new shorted URL 
		{ 
			"url":"",
		}
	POST /login	    - Authentication endpoint
		{ 
			"login":"",
			"password":""
		}
## Authorized EndPoints

### ShortUrls

	GET    /shortUrls       - Retrive All Shorted URL 
	GET    /shortUrls/{uid} - Retrive a specific Shorted URL
	PUT    /shortUrls       - Update a shorted URL 
		{ 
			"url":"",
			"uid":"",
		}
	DELETE /shortUrls/{uid} - Delete a shorted URL
	
### Users

	GET    /users       - Retrive All Users 
	GET    /users/{id}  - Retrive a specific User
	POST   /users       - Create a new User 
		{ 
			"login":"",
			"password":""
		}
	PUT    /users       - Update a User
		{ 
			"login":"",
			"password":""
		}
	DELETE /users/{uid} - Delete a User

### Statistics

	GET    /stats/ 				-	Number of shorted and forwarded urls for a specific URL
	GET    /stats/{uid}			-	Number of ever shorted and forwarded urls 
	GET    /stats/today			-	Number of shorted and forwarded urls today
	GET    /stats/domain/			-	Number of shorted and forwarded urls by domain
	GET    /stats/domain/{domain}		-	Number of shorted and forwarded urls in a specific domain

## Starting with Docker
	Pull this git repository
	Build the image
		docker build -t lgb/surl:0.0.1 -t lgb/surl:latest ${path_to_dockerfile} 
		
	Execute the container
		docker run  --mount type=bind,source=${path_tp_db},target=/db lgb/surl:latest -p 8080:8080 
