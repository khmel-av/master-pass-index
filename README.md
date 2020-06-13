# Master Pass Index
Система выдачи и проверки пропусков

## Скачать проект

```sh
git clone https://github.com/khmel-av/master-pass-index.git
```

### Требования

- PostgreSQL 10.8 или новее
- JDK 1.8
- Maven 3.6.2 или новее
- IDE для разработки 

### Сборка проекта

```sh
mvn clean install 
```

## Запуск тестов

...

## Стиль кодирования

* [Google Java Styleguide](https://google.github.io/styleguide/javaguide.html)
* [IDEA](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
* [Eclipse](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml)

## Разворачивание

### Для разработки или тестирования

```sh
mvn spring-boot:run 
```

### Для продакшена

#### Упаковка jar
```sh
mvn package -DoutputDirectory=<path>
```

####
```sh
java -jar artifact.jar
```

### База данных

Перед запуском приложения необходимо создать базу данных pass_card, а также создать в базе данных пользователя для flyway с полными правами и указать логин/пароль в настройках приложения. А так же учетные записи pass_card_srv и pass_card_dev.
```text
spring.flyway.user=pass_card_dev
spring.flyway.password=
```
Пользователи pass_card_srv и pass_card_dev создаются для работы сервиса и 
для доступа к данным в базе соответственно. Все необходимые права учетным записям выдаст flyway. 
Все необходимые для работы сервиса объекты в базе данных (в том числе схемы, таблицы, триггеры, функции, индексы) сервис создаст сам посредством flyway.

Пароль пользователям базы данных можно сменить следующим способом:
```sql
ALTER ROLE pass_card_dev LOGIN PASSWORD 'new_password'
ALTER ROLE pass_card_srv LOGIN PASSWORD 'new_password'
```
При этом измененный пароль пользователя pass_card_srv необходимо указать в настройках приложения:
```text
spring.datasource.password=pass_card_srv
```

## UI директория
```text
/src/main/resources/mpi_ui/
```

### Версия node
```text
v12.16.2
```

### Версия npm
```text
6.14.4
```

### Сборка UI отдельно
```sh
npm install
```

### Развернуть UI отдельно
```sh
npm start
```

## Используемые инструменты и зависимости

* [Flyway](https://flywaydb.org/) - open-source database migration tool. It strongly favors simplicity and convention over configuration.
* [Maven](https://maven.apache.org/) - Dependency Management
* [Project Lombok](https://projectlombok.org/) - is a java library that automatically plugs into your editor and build tools, spicing up your java.
* [Spring](https://spring.io/projects/spring) - provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform
* [Spring Boot](https://spring.io/projects/spring-boot) - makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"
* [Spring Data](https://spring.io/projects/spring-data) - provide a familiar and consistent, Spring-based programming model for data access while still retaining the special traits of the underlying data store.
* [Spring Security](https://spring.io/projects/spring-security) - powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

<!--
## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.
-->

## Версионирование

Мы используем [SemVer](http://semver.org/) для версионирования. 
<!--
For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 
-->

## Авторы

* Хмель Антон - *Основной разработчик* - [khmel-av](https://github.com/khmel-av/) / [Написать письмо](mailto:khmel_av@mail.ru)

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
* -->
