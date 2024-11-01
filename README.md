# Библиотечный API

## Обзор

Библиотечный API — это серверное приложение, предназначенное для управления библиотечной системой. Оно предоставляет функции для управления авторами, читателями, книгами и транзакциями. Этот проект построен с использованием **Spring Boot** и использует **Keycloak** для аутентификации и **PostgreSQL** для хранения данных.

## Предварительные требования

Перед началом убедитесь, что у вас установлены следующие компоненты на вашем компьютере:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/downloads)

## Начало работы

Следуйте приведенным ниже шагам для настройки вашей среды разработки.

### 1. Клонирование репозитория

Клонируйте этот репозиторий на свой локальный компьютер:

```bash
git clone https://github.com/yourusername/library-api.git
cd library-api
```

### 2. Создание сети Docker

Создайте сеть Docker для приложения:

```bash
docker network create library-network
```

### 3. Композирование инфраструктуры

Установите следующие переменные окружения в вашем `infrastructure-compose.yml`:

```yaml
KC_BOOTSTRAP_ADMIN_USERNAME={KEYCLOAK-USERNAME}
KC_BOOTSTRAP_ADMIN_PASSWORD={KEYCLOAK-PASSWORD}
POSTGRES_DB={POSTGRES-DBNAME}
POSTGRES_PASSWORD={POSTGRES-PASSWORD}
POSTGRES_USER={POSTGRES-USERNAME}
```

Затем запустите инфраструктуру с помощью Docker Compose:

```bash
docker-compose -f infrastructure-compose.yml up
```

### 4. Настройка Keycloak

1. Создайте новую область, например, `library-realm`.
2. Создайте нового клиента:
    - Перейдите в раздел **Clients**.
    - Нажмите **Create Client**.
    - Установите идентификатор клиента.
    - Включите аутентификацию и авторизацию клиента.
    - Установите **Root URL** на `http://localhost:{PORT}`, где `{PORT}` — это порт, который вы установили в `infrastructure-compose.yml`.
    - Установите **Valid redirect URIs** на `{Root URL}/*`.
3. Откройте созданный клиент, перейдите на вкладку **Credentials** и скопируйте **Client Secret**. Это будет использоваться в переменных окружения для Java-приложения в `app-compose.yml`.
4. В этом же окне создайте новую роль с именем `Employee` на вкладке **Roles**.
5. Создайте нового пользователя:
    - Перейдите в раздел **Users** и нажмите **Add User**.
    - Установите информацию о пользователе, включая подтверждение электронной почты, имя пользователя и т. д.
6. Установите пароль для вновь созданного пользователя на вкладке **Credentials**.
7. Перейдите на вкладку **Role Mapping** для созданного пользователя и назначьте роль `Employee`.

### 5. Композирование приложения

Установите следующие переменные окружения в вашем `app-compose.yml`:

```yaml
KEYCLOAK_ADMIN_CLIENT_ID: admin-client-id
KEYCLOAK_ADMIN_PASSWORD: {KEYCLOAK-PASSWORD}
KEYCLOAK_ADMIN_USERNAME: {KEYCLOAK-USERNAME}
KEYCLOAK_APPLICATION_REALM_NAME: {REALM-NAME}
KEYCLOAK_CLIENT_ID: {CLIENT-NAME}
KEYCLOAK_CLIENT_SECRET: {CLIENT-SECRET}
KEYCLOAK_HOST: keycloak
KEYCLOAK_MASTER_REALM_NAME: master
KEYCLOAK_PORT: {KEYCLOAK-PORT}
KEYCLOAK_REALM: {REALM-NAME}
POSTGRES_DB_PASSWORD: {POSTGRES-PASSWORD}
POSTGRES_DB_URL: jdbc:postgresql://postgres:5432/{POSTGRES-DBNAME}
POSTGRES_DB_USERNAME: {POSTGRES-USERNAME}
```

Наконец, запустите приложение с помощью Docker Compose:

```bash
docker-compose -f app-compose.yml up
```