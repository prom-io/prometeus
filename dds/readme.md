# Prometheus DDS / Billing
## Stub service
### Building and running Docker image
1. `docker build -f stub/Dockerfile -t dds .`
2. `docker run -d --rm --name dds -p 8080:8080 dds`

### Testing in Postman
1. Import `postman/dds.postman_collection.json`
2. Configure postman environment variable `server` as `localhost:8080` if you've bind docker container to `8080` 
port as described above
3. play around with 6 requests which are done with tests