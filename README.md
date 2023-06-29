<h1 align="center">📚<br>Gerenciamento de Biblioteca</h1>
<p align="center">
Este projeto é um sistema de gerenciamento de biblioteca desenvolvido em Java/Spring.
</p>
<p align="center">
  <a href="https://www.java.com">
    <img src="https://img.shields.io/badge/Java-17-yellow.svg">
  </a>
  <a href="https://spring.io/">
    <img src="https://img.shields.io/badge/Spring-3.0.6-green.svg">
  </a>
</p>

Para a versão em inglês, consulte o arquivo [README-EN.md](./README-EN.md).


## Funcionalidades

- Cadastro e gerenciamento de livros, autores, editoras, e usuários
- Pesquisa e filtragem de livros
- Empréstimo e devolução de livros
- Envio de e-mails automáticos para notificar usuários sobre empréstimos, devoluções, etc.
<br></br>

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://https://github.com/laramt/lib-management.git
   ```

2. Importe o projeto na sua IDE preferida.

3. Configure as variáveis de ambiente e o Spring Mail no projeto.

3. Configure o arquivo application.properties para incluir as configurações de SMTP necessárias.

4. Execute o projeto na sua IDE.
<br></br>

## Configuração

Para o envio de e-mails automáticos, é necessário as configurações de email no arquivo ``application.properties``.

```` properties
# EMAIL
spring.mail.host= seu_host_smtp
spring.mail.port= sua_porta_smtp
spring.mail.username= seu_email
spring.mail.password= sua_senha
spring.mail.properties.mail.smtp.auth= true
spring.mail.properties.mail.smtp.starttls.enable= true
 ````
Substitua os valores seu_host_smtp, sua_porta_smtp, seu_email e sua_senha pelas suas configurações específicas do servidor SMTP. Certifique-se de ter as credenciais apropriadas e as informações do servidor SMTP de seu provedor de serviços de e-mail.
<br></br>

## Contato

Se você tiver alguma dúvida ou comentário, sinta-se à vontade para entrar em contato [aqui](mailto:laramnckt@gmail.com).