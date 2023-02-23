# Lab 1 - Unit Testing (with JUnit 5)

<h2>Code Coverage</h2>

Command to access the coverage level:

```bash
$ mvn clean test jacoco:report
```

The result can be found on `target/site/jacoco/index.html` (print of the result on folder `prints`)

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/220936451-ce05326f-c67d-4a8e-b8c6-d3632006a879.png">

Coverage for `"BoundedSetOfNaturals"`:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/220939351-3bfc63b8-0c59-419d-9495-4a4b66390a9d.png">

Coverage for `"BoundedSetOfNaturals"` after the changes:

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/98337993/220954415-decaec0f-6e6e-42de-99e4-e18cd61dfec8.png">