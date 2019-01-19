# SURL
URL Shortener

## Public EndPoints

	GET  /{uid}     - forwards to the registred link 
	POST /shortUrls - Create a new shorted URL 
	POST /login	    - Authentication endpoint
	
## Authorized EndPoints

### ShortUrls

	GET    /shortUrls       - Retrive All Shorted URL 
	GET    /shortUrls/{uid} - Retrive a specific Shorted URL
	PUT    /shortUrls       - Update a shorted URL 
	DELETE /shortUrls/{uid} - Delete a shorted URL
	
### Users

	GET    /users       - Retrive All Users 
	GET    /users/{id}  - Retrive a specific User
	POST   /users       - Create a new User 
	PUT    /users       - Update a User
	DELETE /users/{uid} - Delete a User



