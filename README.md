# Бот-помощник для Telegram

Бот для быстрого доступа к полезным ресурсам компании.

## Что это за бот?

### Реализованная функциональность

- Просмотр статей из внутренней базы компании.
- Просмотр паролей Wi-Fi сетей внутри офисов.
- Управление встречами и напоминания для Random Coffee.

### Особенности проекта

- Stateless подход — бот не хранит никакого состояния, все данные он получает с сервера компании.
  Это гарантирует, что пользователю всегда доступна актуальная информация.
- Масштабируемость — использование сущностей `Callback` и `PayloadType`, а также
  архитектуры, основанной на [TEA](https://guide.elm-lang.org/architecture/); разбиение на слои
  и грамотное внедрение зависимостей позволяют создавать ботов высокой сложности с большой вариативностью,
  легко покрывать код автотестами.
- Безопасность — сотрудники не имеют прямого доступа к управлению ботом; взаимодействие с ботом доступно только
  для тех сотрудников, которые указали свой Telegram-аккаунт во внутреннем сервисе компании.

### Основной стек технологий

- [Kotlin](https://github.com/JetBrains/kotlin)
- [Ktor](https://github.com/ktorio/ktor)
- [TelegramBots](https://github.com/rubenlagus/TelegramBots)
- [Docker](https://github.com/microsoft/docker)
- [Git](https://github.com/git/git)

### Демо

Бот доступен в Telegram: [@RosmolodezHelperBot](https://t.me/RosmolodezHelperBot)

Для демонстрации проверка Telegram-аккаунтов отключена на стороне сервера — бот доступен всем.

## Запуск бота

### Настройка учётных данных

Для начала необходимо получить имя бота и его токен в [BotFather](https://t.me/BotFather).

Необходимо задать соответствующие значения переменным окружения `TELEGRAM_BOT_TOKEN` и `TELEGRAM_BOT_USERNAME`.

### Запуск с помощью Docker

Сначала необходимо собрать .jar-образ:

```
docker build -t rosmolodez-onboarding .
```

Затем запустить приложение:

```
docker run --env-file env.list rosmolodez-onboarding
```

### Запуск без Docker

Сначала необходимо собрать .jar-образ с помощью Gradle:

```
./gradlew fatJar
```

Затем запустить приложение:

```
java -jar ./build/libs/rosmolodez-onboarding-$VERSION.jar
```
