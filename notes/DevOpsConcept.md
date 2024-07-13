#### DevOps

### CI/CD 
Agile (2-3 week part of product to client) eg some features => better feedback
Unit Testing + Integration Testing (When integrate modules) => Automation Test (Bugs could Happen) => Solution : CI
Continuous Integration (Commit on the Github everytime it will build and execute the automation test, every commit will execute a test)
Continuous Delivery (After commit it may done the features, show them in a mock server and run current project, show things are working + client understand it + know performance)
Eventually, we manually deploy it on Production server
Continuous Deployment (When commit to github, it build and automate test, once pass , it Automatically pushed to production server)
Integration (Pushing Code) , Delivery (making it available for deployment), Deployment (pushed to production server)
Some company go CI, some company go CD 
eg MicroService, company go for Continuous Deployment
Diificult, team ready, pipeline

### Docker
Container : everything included (standalone) to run a piece of software
Container are designed to provide a consistent and reproducible env across different platform, simplify dev, deployment and scailing
Container Image: lightweight, standalone executable package that include everything
Container Registry: service that store and distribute image, allow developer to pull or push images eg Docker hub, AWS elastic registry
3 type of container
1. Container repository (self-hosted) eg nexus repo
2. private repo
3. public repo
Docker Container (actually a set of images)

docker run -d (-d is detach mode, without d to see all the log, so the cmd cant type anything)
docker run --name peter -e POSTGRES_PASSWORD=password postgres
docker ps (show list of running container)
docker ps -a (show all include not running)
docker stop <container_id>  (get id by "docker ps")
it will create new docker container everyime by "docker run"
docker start <container_id> , it will restart that container
docker logs <container_name>, will show the logs
docker logs -f <container_name>, continue show logs (Ctrl + C to escape log)
docker rm <container_id> (remove containers)
docker rmi <image_id> (remove image)
docker images
docker pull <image_name:version> 

#### Run command on docker by login container
-it (interactive terminal)
docker exec -it <container_name> (login to container cmd)
docker exec -it <container_name> <mode how we connect> -U postgres 
eg docker exec -it affectionate_dirac psql -U postgres


#### Docker Architecture
Client(terminal) -> Docker Host (Local Machine) -> Registry(different docker images)
Client(docker build, docker pull, docker run)
Docker Host (docker daemon) will search if we have image or not, then go to Registry to fetch the image, then store it
When docker run, Docker Host if image already pulled and then create a new containers out of that image

#### Docker Volumes  (docker run -v)
everytime restart/ remove, we lost the data
Container have virtual file system,
As for Harddisk have physical file system 
Data is automatically replicated (copy) which mean physical file system will mount on virtual one

Host Volume : docker run -v <host path>:<container path>  (container data will stored on host)
Anonymous volumes : docker run -v <host path> (container file is generated and mounted by Docker)
Named Volumes : improvement of previous one : docker run -v named_volume:<container path>
Named Volumes just reference volume by name
Named Volumes should be using in Production : improve performance and maintainability in data management

docker run --name postgres-sql -e POSTGRES_PASSWORD=password -v /Users/wwwlo/docker/volumes/postgres/data:/var/lib/postgres/data postgres
the path is take care by the current cmd directory pointing to , so make it to
docker run --name postgres-sql -e POSTGRES_PASSWORD=password -v /docker/volumes/postgres/data:/var/lib/postgres/data postgres

#### Life after containers
- isolated env (env depend on the host)
- package with the required configuration 
- One same command to install and run the App
- RUn on different env and versions

pull a containeer which contain Script, Config, Postgres (application)

#### Deploy before having containers (Version conflict + Misunderstanding documentation)
Provide jar file, configuration, documentation, version , send these to the Operation(Ops) ppl
Ops set up server,config, dependencies
#### Deploy after having containers 
Provide a container with JAR, config, dependencies TO Ops
No env configuration on the server, (the server always need to have docker Installed and pull the container and run)
everyone use the same setting and Operation and Developer work together -> DevOps

#### Docker ports (Container Port(Inside Docker), Host Port(Outside Docker))
connect Docker postgres , cant connect directly because its isolated and we need docker port
So Docker container will bind to a ports 
eg Postgres using 5432, and antoher Postgres container using 5432, they are isolated so docker will bind a ports for them
eg 5432 and 1234, because if ports is not using it will directly bind the same port, else bind a new port
So that Multiple containers can run on a Host , and A host has certain ports available and solve Conflict when using the same port on your host by
giving new ports

#### Bind Ports for container 
-p <host port>:<container port>
-p 5432:5432 (set the port for container to outside world)  , by default its 5432/tcp so we want 0.0.0.0:5432->5432/tcp
0.0.0.0:5432 (is the local address) , 5432/tcp is the container port
docker run --name postgres-sql -p 5432:5432 -e POSTGRES_PASSWORD=password -v /docker/volumes/postgres/data:/var/lib/postgres/data postgres 
