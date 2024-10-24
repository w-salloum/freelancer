### How to run the application
1. Clone the repository
2. Run `docker-compose up -d db` in the root directory of the project, to start the database
3. Run `./gradlew clean build` in the root directory of the project, to build the application
4. To run the application `docker-compose up --build`
5. The application will be running on `http://localhost:8080/api/freelancers`

### How to run the tests
- To run the tests `./gradlew test`