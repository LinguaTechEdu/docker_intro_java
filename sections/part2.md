# 2: Creating a Docker Image

Now that you've installed Docker have seen how it works, we'll get started by creating our own Docker image.

1. Make sure you've cloned this repository.
2. Add a new file to it named `Dockerfile`
3. Open the project in your code editor and edit the `Dockerfile`.
4. Copy and paste the following into it:

```Dockerfile
# Use Java distribution from Docker
FROM openjdk:11-jdk

# Temporary data storage used by data services like Redis
VOLUME /tmp

# Working directory for this app
WORKDIR /app

# Copy this application to a new location
COPY . /app

# Build
CMD ["./gradlew", "build", "docker"]

# Run the application from the app build folder
ENTRYPOINT ["java","-jar","/app/build/libs/gs-spring-boot-0.1.0.jar"]
```

Read the comments of the file to understand what each command is doing. 

**Dockerfile**s are used to define how your application should run. Everyone on your team will use this to run and/or deploy this app as a service.

Finally, build and run the app: 

```bash
# Step 1: Build. Think of the tag as the build version for the Docker Image
docker build -t apidemo:latest .

# Step 2: Run. Name the Container instance apidemo and run app port 8080 on local 4000. 
docker run -p 4000:8080 apidemo

# OR run as a detached container instead, which does the same thing. Try both!
docker run -d -p 4000:8080 apidemo

# Step 3: Open your browser to localhost:4000 to see your app running.

# Step 4: Stop your container. To stop a detached container, first get the container ID
docker container ls

# ...then use the stop command
docker container stop paste_container_id_here

```
> NOTE: If you don't provide a tag (image name) Docker assumes 'latest'. Make sure you're working from the correct image! Here we are working from apidemo:latest even though we didn't provide 'latest'.

You've built a Docker app. JOY! But you're using Docker for a reason: to share and collaborate. Next we'll talk about how to share your apps.

[Continue to the next section ...](part3.md)
