# Exercise 3.1

<h2>a) Identify a couple of examples that use AssertJ expressive methods chaining.</h2>

No teste A_EmployeeRepositoryTest:
```bash
assertThat( found ).isEqualTo(alex)  
assertThat(fromDb).isNull()
```

No teste B_EmployeeService_UnitTest:
```bash
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

<h2>b) Identify an example in which you mock the behavior of the repository (and avoid involving a 
database).</h2>

É utilizado no ficheiro de teste B_EmployeeService_UnitTest. O EmployeeRepository employeeRepository; é inicializado com a anotação @Mock e depois, na função de configuração, Mockito é utilizado para dar mock do comportamento do repositório:
```bash
 Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
 Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
```

<h2>c) What is the difference between standard @Mock and @MockBean?</h2>

Podemos usar @Mock para criar e injectar instâncias mocked sem ter de chamar Mockito.mock, manualmente. Esta anotação só deve ser usada em classes de teste.

O @MockBean adiciona objectos de mock à aplicação Spring. Irá substituir qualquer bean existente do mesmo tipo. Se nenhum do mesmo tipo for definido, será adicionado um novo.
Útil em testes de integração onde um determinado bean precisa de ser mocked.

<h2>d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be 
used?</h2>

O ficheiro application-integrationtest.properties tem as propriedades para se ligar a uma base de dados e configurar persistência. Por exemplo, no ficheiro de teste D_EmployeeRestControllerIT, se em vez da anotação @AutoConfigureTestDatabase tivéssemos @TestPropertySource(locations = "application-integrationtest.properties") estaríamos a aceder a uma base de dados real com o ficheiro application-integrationtest.properties.

<h2>e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed 
with SpringBoot. Which are the main/key differences?<h2>

As estratégias D e E apresentam algumas semelhanças enquanto que a C mostra-se um pouco mais distante de ambas.

O teste C não envolve a base de dados, mas o teste D e E envolvem. O teste C simula as dependencias relacionadas com o service implementation com a anotação @MockBean. Usa, ainda, o @WebMvcTest, que simula o comportamento de um servidor da aplicaçao num ambiente simples, e além disso usa o MockMvc que fornece uma API expressiva.

Já tanto o teste D como o E são testes de integração que envolvem vários componentes. Ambos envolvem as componentes: service implementation, o repository e a base de dados. Os dois testes iniciam o contexto da Web completo através da anotação @SpringBootTest e a API é implementada no contexto SpringBoot. A diferença entre estes dois testes é nesta API implementada, onde o teste D usa o Mockvc como entry point para suporte de teste Spring MVC do lado do servidor, porém o teste E usa o TestRestTemplate, um cliente REST para criar pedidos realistas, envolvendo também respostas.