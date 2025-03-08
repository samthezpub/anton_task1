# Мой маленький проект
Использовал Spring Boot, Spring Security, Spring Data, Maven, PostgreSQL, JWT, Liquibase
Можно использовать в качестве шаблона для ваших проектов, реализована базовая логика для работы с сущностями и REST, архитектура позволит в будущем без проблем внедрять новые изменения

## Установка и запуск проекта
1. Выполните git clone для скачивания кода
2. Разверните в докере контейнер с PostgeSQL (```docker run --name habr-pg-13.3 -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=db -d postgres:13.3```)
5. Измените пароль, имя пользователя, базу данных в application.properties модуля rest
6. Выполните mvn clean, mvn install
7. Запустите приложение!
