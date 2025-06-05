# Screenmatch – Sistema de Busca de Filmes e Séries
API REST desenvolvida em Java com Spring Boot para consultas de filmes e séries, através do consumo da https://www.omdbapi.

## 🛠 Tecnologias utilizadas
- Java 21
- Spring Boot
- Lombok
- ModelMapper
- Maven
- Springdoc OpenAPI (Swagger) para documentação automática da API

## 🧱 Arquitetura e Organização de Pacotes
```
src/main/java/br/com/casa/vollmed
├── application
│   ├── exception              
│   └── service                
│
├── domain
│   ├── model                  
│   ├── repository             
│   └── service
│       └── validation        
│
├── infrastructure
│   └── security              
│
├── interfaces
│   └── web
│       ├── controller       
│       └── dto
│           ├── input          
│           └── output         
│
└── config                     

```
>Este projeto adota uma arquitetura orientada a DDD (Domain-Driven Design), priorizando a separação de responsabilidades entre as camadas, o encapsulamento das regras de negócio no domínio e a aplicação de boas práticas.

## 🔄 Funcionalidades previstas
- [x] Consulta de filmes
- [x] Consulta de séries

## 🧪 Testes
🔧 Em desenvolvimento:
Testes unitários e de integração serão adicionados em breve.

## 🚀 Como executar o projeto
```
# Clone o repositório:
git clone https://github.com/franze-ernesto/screenmatch.git

# Acesse o diretório do projeto:
cd vollmed

# Configure o MySQL conforme indicado abaixo e execute a aplicação com sua IDE ou via terminal:

#Execute a aplicação pela IDE ou com Maven:
./mvnw spring-boot:run
```


## 📝 Licença

Projeto desenvolvido por **Anderson Ernesto**. Parte da estrutura inicial foi baseada em um curso da [Alura](http://alura.com.br), com evoluções que incluem:
```
- Separação de DTOs por entrada e saída

- Regras de negócio isoladas e validadas dinamicamente

- Arquitetura orientada a DDD (Domain-Driven Design)

- Documentação via Swagger com Springdoc OpenAPI
```    


Este projeto é para fins educacionais.

**Projeto em constante desenvolvimento

---



