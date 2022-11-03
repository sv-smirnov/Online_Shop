## Online Shop v2
***
### Описание проекта:
Вторая версия интернет-магазина, разработанная на микросервисной архитектуре. Данное веб-приложение написано на языке Java с использованием библиотек Spring-а.
В приложении реализованы функции авторизации при помощи JWT Token-ов, корзина хранит заказ используя No-SQL Redis, а заказы формируются с использованием шины Apache Kafka.
Фронтенд реализован на основе JS Angular. Добавлена довзможность получения оплаты заказов, при помощи QIWI (для этого нужно указать SecretKey своего кошелька: order-service/src/main/resources/secrets.properties).
Для подключения сторонних зависимостей (Redis и Kafka) созданы соответствующие docker-compose.yml файлы. 
***
### Особенности проекта:
- Apache Kafka
- Redis
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- WildFLy
- HTML & CSS & JS Angular
- Docker
- QIWI кошелек
***
### Запуск приложения:
1. Запустить окружение Redis и Kafka, используя Docker
2. Для онлайн-оплаты нужно указать SecretKey: order-service/src/main/resources/secrets.properties
3. Запустить все микросервисы
4. Подключаемся к http://localhost:3000/front
5. Приятного шоппинга :wink:
* ![Текст с описанием картинки](/shop.jpg)
