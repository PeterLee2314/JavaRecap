#### Git
git status // see what modified and untracked files(new file)
git log // see commit log (q to exit)
git log --pretty=oneline (commit message only)
git log --graph  (more detailed on extension : git graph)

git diff // see what is modified in file
git diff --staged // see files modified in stage after 'git add'
git history
git config

git add // its stage
git commit -am // a (skip staging, no need to use 'git add')

git push -u origin main (-u mean upstream and branch name is main)  // master != main, ppl now use main instead of master
git push origin (origin mean URL)
git remote -v (see the origin URL)

git branch (see how many branch)
git branch --all (see all branch include github)
git checkout -b == git switch -b == git switch -c
git switch - (go previous branch)
git branch -d branchName (remove branch)

#### Way to merge code
git merge
git merge branchName (so it merge the current branch you at and the branch you selected)
git rebase
git pull origin main

#### Git tag
git tag (publish to user, so give a tag, check how many tag so far) [two options: lightweight / annotated]
annotated => more information (-a) 
eg git tag -a v1.0 -m '1st release'
git show v1.0 (give description for particular tag)
git push origin v1.0 (push tag individually to files)

#### git remove from git repo
if delete file from working directory and commit (ITS WRONG)
use 'git rm --cache files.txt' first then directly delete it and then commit 

#### Create new repo on local machine with github
git init -> git add README.md -> git commit -m "init" -> git branch -M main -> git remote add origin HTTPS/SSH -> git push -u origin main

HTTPS (login and authenticate yourself) VS SSH (no need login )
ssh-keygen -o (first time to do it on a machine, so no need to authenticate yourself on computer)
.ssh/id_rsa.pub , copy the RSA and go GITHUB SETTING and 'SSH and GPG keys' paste that key , then now the SSH remote add
is good to go

#### clone project
git clone https
#### fork project 

#### Push my code to the empty github repo


#### Github Action CI/CD pipeline
Push code -> automatically trigger pipeline (Github action workflow) -> GitHub Action read the secret from Secret Store ->
(6 steps ) (1.checkout code 2. Maven test 3. Maven Build package 4. Build image 5. Login to Docker 6. Build and push) ->
Publish to docker hub/aws/azure

#### CI/CD
Continuous Integration / Continuous Deployment
CI refers to regular merging small code changes into repo (test then verify they can integrate with larger code base)
- ensure no break code base
- maintain code quality
CD refers to Continuous Deployement / Continuous Delivery
refers to process of automating the release of software to production
(successful testing, creating builds, running automated test, deploy app to staging environment, release app)
run again the test, check everything, build the application package then deploy

##### Pipeline stages
- Source Control
- Building
- Testing
- Staging 
- Production
Reduce Human Error

follow folder structure and name to have GitHub Action
.github\workflows\main.yml
steps -> checkout code -> setup JDK -> set up Maven and test
```
name: Build & Deploy Spring app

on: ## when
  push: ## push action
    branches: ## which branches
      - main


jobs: ## make jobs
  build-deploy: ##your job name
    name: Build and deploy
    runs-on: ubuntu-latest
    steps: ## each job have different step ,
      - name: Checkout code ## checkout the code (mean copy code)
        uses: actions/checkout@v2  (this is find from github marketplace action)
        
      - name: Steup JDK ## we need to set up JDK
        uses: actions/setup-java@v3 
        with: ## this consider as parameter to set up java
        
        ## run manual script maven test, so its not action (its a command)
      - name: Unit Tests
        run: mvn -B test --file pom.xml  ## we can use pom.xml because the checkout code already copy everything
        ## also get pom.xml to download all artifact
    
        ## Multiple line by (|)
      - name: Build Application
        run: |
          mvn clean
          mvn -B package --file pom.xml
          
      - name: Build Docker Image
        uses: docker/build-push-action@v2
        with:
          context: . ## want to use current path
          dockerfile: Dockerfile ## based on the dockerfile, so need to create this
          push: false ## just want to test can build the image
          tags: ${{secrets.DOCKER_HUB_USERNAME}}/spring-boot-for-beginners:latest ## we can make version(latest) dynamic
          tags: ${{secrets.DOCKER_HUB_USERNAME}}/spring-boot-for-beginners:today ## so the tags is with today
          ## docker username, sensitive , so go github Setting -> Security -> Secret and Variables -> Action
          ## create new repo Secret (docker token and docker username)
      - name: Login to Docker hub
        uses: docker/login-action@v1
        with:
          username: ${{ secrets.DOCKER_HUB_USERNAME }}
          password: ${{ secrets.DOCKER_HUB_ACCESS_TOKEN }}

      - name: Push to Docker hub
        uses: docker/build-push-action@v2
        with:
          context: . ## want to use current path
          dockerfile: Dockerfile ## based on the dockerfile, so need to create this
          push: true
          tags: ${{secrets.DOCKER_HUB_USERNAME}}/spring-boot-for-beginners:today
```

Dockerfile
```
FROM openjdk:17-jdk ##pulling open jdk image

WORKDIR /app  ## define working directory

COPY target/springdemo-1.0.0.jar /app/springdemo.jar  ##copy jar to springdemo.jar 
## After build the application, the runner will create a target folder contain a springdemo-1.0.0.jar
## Then copy this jar to the Docker working directory

EXPOSE 8080 ## Run on 8080

CMD ["java", "-jar", "springdemo.jar"] ## Run the command
```