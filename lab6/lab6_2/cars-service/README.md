Generated code to run:

```bash
mvn clean verify sonar:sonar
-Dsonar.projectKey=lab6_2
-Dsonar.host.url=http://localhost:9000
-Dsonar.login=sqp_ccd1c4495facf1de103394fceb11dbf55ebca42b
```

I got the following results:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/227498699-c15ac2f3-fd57-4d01-bc7b-896c3edbeedd.png">

The technical debt found is 25 mins. 
This concept is a measure of effort to fix all code smells. The measure is stored in minutes in the database. An 8-hour day is assumed when values are shown in days.

There is only one major code smell:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/227501333-574a8d30-4429-4d0b-bfe2-12d45231b0eb.png">

After fixing:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/228874932-4bef910c-626a-4c3d-8a74-28a1111f029e.png">

Coverage values on the SonarQube dashboard:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/228887108-8ceed630-ecaa-44ee-87e5-1f2b7757830d.png">

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/228887173-14ca6c24-462f-489e-948e-97a882e51b64.png">