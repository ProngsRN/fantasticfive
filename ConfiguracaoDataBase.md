# Introdução #

Explicarei aqui como instalar e utilizar o Banco de Dados (PostgreSQL) ao longo do nosso projeto.

## Instalação ##

Para isto, vocês irão precisar:

  * Instalar o Postgre em seu computador, através [deste link](http://www.enterprisedb.com/products/pgdownload.do#windows) (clique).

Ao instalar, siga no modo padrão e não é necessário instalar nenhum software adicional. Se por acaso pedir nome de usuário e senha, escolha:

**Nome de usuário:** _admin_

**Senha:** _five_


# Configuração #

## Configuração do Server ##
Iremos configurar o server da nossa aplicação. Com o pgAdminIII (ambiente de administração do Postgre) aberto, clique no ícone de um plug (Add a connection to a server). Por padrão, em nosso projeto estamos utilizando como senha five.

## Configuração do Banco de Dados ##
Agora, vamos configurar um novo Banco de Dados. Com o pgAdmin (Postgre) aberto, crie um novo banco de dados: no painel lateral esquerdo, vá em _Servers –> PostgreSQL 9.1 (localhost:5432)_, clique com o botão direito sobre _Databases_ e escolha a opção _New Database_. O nome deverá ser obrigatoriamente **`dbsigbo`**. Verifique na aba _Definition_ se UTF-8 está selecionado como opção em _Encoding_.


Agora, partiremos para o próximo passo: criar um novo usuário.

### Novo Usuário ###


Clique com o botão direito sobre _Login Roles_ (último na lista), e escolha _New Login Role_. O usuário deverá ter obrigatoriamente os seguintes dados:


> _Aba: **Properties**_

> Role Name: usersigbo

> _Aba: **Definition**_

> Password: five

# Conclusão #

Agora você já está apto a executar o projeto com sucesso. Verifique se o mesmo encontra-se atualizado em sua última versão, para isto, faça um check-out (**recomendado**).