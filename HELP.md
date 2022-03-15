# Pet Store Demo Application

### Reference Documentation
A demo application created using the Spring boot Framework. This application requires access to a database.

When running the pet-store, the following environment variables are expected:

* DB_User
* DB_Password
* DB_URL

### Services
The pet store service which controls an inventory of pets in a persistent database. The following routes are exposed:

* GET /pets 
    - List all pets in inventory.
    - Returns http response 200. 
* GET /pets/{petid}
    - Retrieve information on a pet.
    - if Id is not provided, service returns 400 response.
* POST /pet
    - Creates new pet.
* Delete /pet/{petId}
        - Deletes the pet if available.

