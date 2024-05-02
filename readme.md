# Sistema de Gerenciamento de Biblioteca E2E Treinamentos

Este é o Sistema de Gerenciamento de Biblioteca, uma aplicação desenvolvida para facilitar o gerenciamento de livros em uma biblioteca.

## Sobre

O Sistema de Gerenciamento de Biblioteca é uma ferramenta poderosa para bibliotecários controlarem o acervo de livros de uma biblioteca de forma eficiente. Ele permite o cadastro, busca, edição e exclusão de livros, bem como o registro de empréstimos e devoluções. Além disso, oferece uma interface intuitiva e amigável para facilitar a interação com os usuários.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- MySQL


##Como Executar

Para executar este projeto localmente, siga as instruções abaixo:

**Clone este repositório:**

    ```bash
    git clone https://github.com/seu-usuario/sistema-gerenciamento-biblioteca.git
    ```
    
**Configure as variáveis de ambiente:**

    - **Java:** Certifique-se de que o JDK (Java Development Kit) esteja instalado em sua máquina e que a variável de ambiente `JAVA_HOME` esteja configurada corretamente.
    
    - **Maven:** Instale o Maven e configure a variável de ambiente `MAVEN_HOME`. Adicione o diretório `bin` do Maven ao `PATH`.
    
    - **MySQL:** Instale o MySQL Server e configure o acesso ao banco de dados. Defina as variáveis de ambiente `MYSQL_HOME` e adicione o diretório `bin` do MySQL ao `PATH`. Configure as credenciais de acesso ao banco de dados no arquivo `application.properties`.

  

**Configure o banco de dados MySQL:**

    - Crie um banco de dados com o nome `librarye2etreinamentos`.
    - Configure as credenciais de acesso ao banco de dados no arquivo `application.properties`.

**Execute o projeto:**

    ```bash
    mvn clean install
    mvn spring-boot:run

    
**Acessar apis:**


http://localhost:8085/api/