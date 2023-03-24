Generated code to run:

```bash
mvn clean verify sonar:sonar 
-Dsonar.projectKey=lab6_2 
-Dsonar.host.url=http://localhost:9000 
-Dsonar.login=sqp_cc5975eb49283d3d78810b71c7df530684ec14c8
```

I got the following results:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/227498699-c15ac2f3-fd57-4d01-bc7b-896c3edbeedd.png">

The technical debt found is 25 mins. 
This concept is a measure of effort to fix all code smells. The measure is stored in minutes in the database. An 8-hour day is assumed when values are shown in days.

There is only one major code smell:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/227501333-574a8d30-4429-4d0b-bfe2-12d45231b0eb.png">

After fixing:

