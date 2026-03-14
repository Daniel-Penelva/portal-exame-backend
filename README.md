# ▶️ Executando o projeto localmente

Este projeto originalmente foi configurado para execução em ambiente de produção com deploy online.
Para executá-lo localmente é necessário realizar algumas alterações no **backend** e garantir que o **frontend utilize o ambiente de desenvolvimento**.

---

# ⚙️ Backend (Spring Boot)

## 1️⃣ Ativar perfil de desenvolvimento

No arquivo:

```
src/main/resources/application.properties
```

alterar:

```
spring.profiles.active=${APP_PROFILE:prod}
```

para:

```
spring.profiles.active=dev
```

Isso fará com que o projeto utilize o arquivo:

```
application-dev.properties
```

que contém as configurações do banco de dados local.

---

## 2️⃣ Configurar CORS para permitir acesso do frontend local

No arquivo:

```
GlobalCorsConfig.java
```

alterar:

```java
corsConfiguration.setAllowedOrigins(List.of("https://dancing-nougat-1402b3.netlify.app"));
```

para:

```java
corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
```

Isso permite que o frontend executado localmente consiga acessar a API.

---

## 3️⃣ Configurar banco de dados MySQL

Certifique-se de possuir um banco MySQL rodando localmente com as seguintes configurações:

```
Database: portal_exame_backendBD
Username: root
Password: root
Port: 3306
```

Essas configurações estão definidas no arquivo:

```
application-dev.properties
```

```
spring.datasource.url=jdbc:mysql://localhost:3306/portal_exame_backendBD
spring.datasource.username=root
spring.datasource.password=root
```

---

## 4️⃣ Executar o backend

Na pasta do backend execute:

```
mvn spring-boot:run
```

ou execute a classe principal da aplicação pela sua IDE.

A API ficará disponível em:

```
http://localhost:8080
```

---

# 💻 Frontend (Angular)

O frontend já está configurado para utilizar diferentes ambientes.

### Ambiente de desenvolvimento

Arquivo:

```
src/environments/environment.ts
```

```
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/'
};
```

### Ambiente de produção

Arquivo:

```
src/environments/environment.prod.ts
```

```
export const environment = {
  production: true,
  apiUrl: 'https://portal-exame-backend-production.up.railway.app'
};
```

Quando executado localmente com:

```
ng serve
```

o Angular automaticamente utiliza o arquivo **environment.ts**, apontando para a API local.

---

## 1️⃣ Instalar dependências

Na pasta do frontend execute:

```
npm install
```

---

## 2️⃣ Executar o projeto

```
ng serve
```

---

## 3️⃣ Acessar aplicação

Frontend:

```
http://localhost:4200
```

Backend:

```
http://localhost:8080
```

Após iniciar ambos os serviços, o sistema estará funcionando completamente em ambiente local.

---

# ⚠️ Observação

A aplicação foi originalmente configurada para execução em ambiente de produção com deploy online.
As alterações descritas acima permitem executar o sistema totalmente em ambiente local para fins de desenvolvimento e testes.

---
## Autor: Daniel Penelva de Andrade