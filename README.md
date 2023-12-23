# BCI

Este proyecto tiene como objetivo lanzar ofertas a clientes aprovechando la fecha de Black Friday. 


## HERRAMIENTAS NECESARIAS
* Java 17
* SpringBoot: Para facilitar el desarrollo y creacion del proyecto.
* Maven: Para gestion de proyecto
* Spring Security: Como modulo de seguridad para crear y administrar usuario como asi tambien comprobar la identidad del mismo mediante la autenticación y manejo de token.
* Postman: Como cliente para realizar peticiones http
* Kafka: Como plataforma de eventos para publicar a un topico las ofertas seleccionadas a cliente. Cualquier consumidor suscrito podra conocer estas ofertas.
* Swagger: Para documentar la API y utilizar los servicios RESTful.
* MySQL workbench: Como herramienta para administrar la base de datos relacional.
* Amazon RDS: Como servicio de BD en la nube para facilitar la configuracion, disponibilidad y escalabilidad de los datos.
* Elastic Beanstalk: Como servicio para crear y deplegar la app desarrollada en la nube.
  
## POSTMAN COLLECTION
https://github.com/juampidemarco/blackFriday/blob/master/src/main/resources/Black%20Friday.postman_collection.json

## DIAGRAMS
Not implement

## EJECUCION

Para correr el proyecto y hacer pruebas, se debe tener presente lo siguiente:

1. singup: Este método registra el usuario con los datos enviados y retorna los datos del usuario creado.
* Endpoint: http://localhost:8081/api/v1/auth/signup
* Metodo: POST
* Body: {   
  "email": "juanpablo@gmail.com",
  "password": "12345678",
  "lastName": "de marco",
  "firstName": "juan pablo"
  }

2. signin: Si el usuario ya se encuentra registrado, debe acceder al servicio de login para hacer el proceso de autenticación y obtener el token JWT con el que se consumirán los métodos privados.
* Endpoint: http://localhost:8081/api/v1/auth/signin
* Metodo: POST
* Body: {   
  "email": "juanpablo@gmail.com",
  "password": "12345678"
  }

3. CreateProduct: Se utiliza para crear un producto y guardarlo en la base de datos.
* Endpoint: http://localhost:8081/api/v1/product/create
* Metodo: POST
* Body: {   
  "name": "pantalon",
  "price": 15999,
  "stock": 15,
  "discount": 1
  }

4. GetProduct: Se utiliza para obtener un producto por id.
* Endpoint: http://localhost:8081/api/v1/product/1
* Metodo: GET

5. CreateOffer: Se utiliza para generar una oferta para un usuario, asociandole un producto.
* Endpoint: http://localhost:8081/api/v1/offer/create
* Metodo: POST
* Body: {   
  "idProd": 1,
  "idClient": 1,
  "price": 10.8,
  "amount": 6,
  "importance": 3,
  "duration": 2,
  "urgency": 1,
  "category": 3,
  "paymentMethod": "CASH"
  }

6. ShowOffer: Se utiliza para obtener una oferta por id.
* Endpoint: http://localhost:8081/api/v1/offer/show/1
* Metodo: GET

7. Publish: Se utiliza para postear a un topico una oferta seleccionada a un cliente
* Endpoint: http://localhost:8081/api/v1/offer/publish
* Metodo: POST
* Body: {
  "idProd": 1,
  "idClient": 1,
  "price": 10.0,
  "amount": 6,
  "importance": 1,
  "duration": 1,
  "urgency": 1,
  "category": "1",
  "paymentMethod": "DEBIT"
  }

## NOTAS
Se crea un microservicio consumidor para testear los mensajes llegados al topico.
https://github.com/juampidemarco/test-consumer

Servicio RDS
* Punto de enlace: bcidb.cxke8mokiz26.us-east-1.rds.amazonaws.com
* Puerto: 3306