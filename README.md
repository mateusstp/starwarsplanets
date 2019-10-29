# Star Wars Planets
#### Pré-requisitos para execução:
* Maven
    * https://maven.apache.org/index.html
* Docker
    * https://docs.docker.com/install/
* Docker Compose
    * https://docs.docker.com/compose/install/
* Terminal para execução de comando _**sh**_

#### Build e Start do Projeto:
Basta executar o comando a seguir na raiz do projeto:\
```
sh start-project.sh
```
**As portas 8080 e 27017 devem estar disponíveis.**

Após o inicio da aplicação a api estará disponível em:
```
http://localhost:8080/swagger-ui.html
```
Antes de iniciar a utililização da aplicação é necessário preparar o ambiente.
Basicamente a aplicação irá extrair os dados da Api https://swapi.co. Utilize http://localhost:8080/swagger-ui.html#/planet-controller/prepareEnvironmentUsingPOST
e aguarde o sucesso da execução.


#### Padrões do Projeto:
O projecto está baseado no _Design Patterns Business Rule_. Esse padrão facilita a aplicação do _SOLID_ e a 
implementação de testes unitários.

#### Organização do Projeto:
```
src
├── main
│   ├── java
│   │   └── br
│   │       └── com
│   │           └── star
│   │               └── wars
│   │                   ├── businessrule
│   │                   │   ├── activity
│   │                   │   ├── executor
│   │                   │   ├── impl
│   │                   │   └── navegable
│   │                   ├── config
│   │                   ├── controllers
│   │                   ├── integration
│   │                   │   └── swapi
│   │                   ├── model
│   │                   │   ├── dto
│   │                   │   ├── entity
│   │                   │   └── mapper
│   │                   ├── repository
│   │                   └── service
│   └── resources
│       └── config
└── test
    ├── java
    │   └── br
    │       └── com
    │           └── star
    │               └── wars
    │                   ├── businessrule
    │                   │   └── activity
    │                   └── integration
    └── resources
```