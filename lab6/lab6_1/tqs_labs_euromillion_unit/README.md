Generated code to run:

```bash
 mvn clean verify sonar:sonar 
-Dsonar.projectKey=lab6_1 
-Dsonar.host.url=http://localhost:9000 
-Dsonar.login=sqp_daa7e499b61d08cf0076ed38d6bdeb9adfd30b6e
```

Obtive os seguintes resultados:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/227488632-111d1a13-4951-481c-9cb0-42c16dba6147.png">

| Issue                 | Problem description                   | How to solve                  |
| :-------------------- |:------------------------------------- | :---------------------------- |
| Bug                   | Nothing found                         |                               |
| Vulnerability         | nothing found                         |                               |
| Code smell (major)    | Invoke method(s) only conditionally   | Create a condition to invoke  |