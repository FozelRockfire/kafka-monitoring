# Kafka-monitoring
Система мониторинга с использованием Spring Kafka

## Структура проекта
Проект состоит из 2-х микросервисов:
- _**Metrics Producer**_ собирает метрики работы приложения и отправляет их в Kafka топик "metrics-topic". Раз в 5 секунд
отправляет одну из основных метрик в сервис-потребитель.
- _**Metrics Consumer**_ принимает метрики из Kafka топика "metrics-topic" и сохраняет их в БД PostgreSQL. Позволяет получить список всех 
сохраненных метрик или конкретную метрику по id.

Swagger документация _**Metrics Producer**_ доступна по ссылке: _http://localhost:8081/swagger-ui/index.html_

Swagger документация _**Metrics Consumer**_ доступна по ссылке: _http://localhost:8082/swagger-ui/index.html_

Микросервисы успешно обмениваются данными через Kafka и обеспечивать сбор и хранение метрик работы приложения. 
Контроллеры и сервисы покрыты unit-тестами.

Для старта приложения необходимо:
- запустить postgreSQL через docker.compose или изменить настройки подключений к бд в application.yml микровсервиса
  _**Metrics Consumer**_
- запустить Kafka и Zookeeper через docker.compose

Стек технологий:
`Java 17` `Maven` `Spring` `PostgreSQL` `Flyway` `Kafka`

## Автор:<br>
Попов Илья ([FozelRockfire](https://github.com/FozelRockfire))<br>
