## Mandatory requirements
It should be a simple java application based on Spring Boot framework.

The application should have a REST API that allows users to search by address and get all addresses by coordinates.

The result should be printed in JSON format.

Your code should be version controlled and publicly accessible for us to review (github/bit-bucket/gitlab/etc)
## Nice to have:
- Implement Authorization/Authentication for result endpoints.
- Implement Cache for storing very often using coordinates.
- Deploy application to Docker container (Dockerfile artifact).


## Task description:
You need to implement application that uses third party api Nominatim API and create the following endpoints:
- To search by address and save coordinates(latitude,longitude) in H2 DB or any Persistence Db
- To get all addresses by coordinates that have been stored in DB. The application must be called by REST API for receiving all addresses from Nominatim API  by saved coordinates.

## Testing
`docker build -t overonix/search .`

`docker run -p 8080:8080 overonix/search`

`curl -u admin:admin -X POST localhost:8080/Kyiv,Ukraine`
`curl -u admin:admin -X GET localhost:8080`
