# SimpleBank

## О проекте

В данном репозитории содержится RESTful API backend приложение для создания банковских счетов и перевода денег между ними.

## Стек в данном приложении

- Java 17
- Maven
- Spring Boot
- PostgreSQL

## Запуск

#### Запустить приложение можно с помощью Docker Compose или локально, выполнив следующие шаги:

1. Для работы приложения нужно подключение к бд:
   ```bash
   jdbc:postgresql://localhost:5432/simplebank
   username=simplebankuser
   password=postgres
   
2. Создание базы данных:

   ```bash
   psql -U postgres
   CREATE DATABASE simplebank;
   CREATE USER simplebankuser WITH PASSWORD postgres;
   GRANT ALL PRIVILEGES ON DATABASE simplebank TO simplebankuser;
   GRANT ALL PRIVILEGES ON SCHEMA public TO simplebankuser;
   
3. После запуска приложение обрабатывает запросы по URL:

   ```bash
   http://localhost:8080/simplebank
   
## Реализованные эндпоинты:

1. Создание бенефициара (POST)

   ```bash
   http://localhost:8080/simplebank/api/v1/beneficiaries
   
   Body Example:
   {
    "name": "John Johnson"
   }
   
   Response Example:
   200 OK
   {
    "beneficiaryId": 9
   }
   
2. Создание счёта (POST)

   ```bash
   http://localhost:8080/simplebank/api/v1/accounts/{beneficiaryId}
   
   Body Example:
   {
    "pinCode": 2222,
    "balance": 200.00
   }

   Response Example:
   200 OK
   {
    "accountNumber": "83745765901405580096"
   }
   
   
3. Перевод денежных средств (POST)

   ```bash
   http://localhost:8080/simplebank/api/v1/transfers/{beneficiaryId}/from/{accountNumber}
   
   Body Example:
   {
    "pinCode": 1111,
    "toAccountNumber": 50962027508738960975,
    "amount": 100.00
   }
   
   Response Example:
   200 OK
   
4. Получение списка счетов (GET)

   ```bash
   http://localhost:8080/simplebank/api/v1/accounts
   
   Response Example:
   200 OK
   [
    {
        "accountNumber": "63065874077307155631",
        "beneficiaryName": "John Smith",
        "balance": 100.00
    },
    {
        "accountNumber": "83745765901405580096",
        "beneficiaryName": "John Johnson",
        "balance": 200.00
    },
    {
        "accountNumber": "45374809489246622528",
        "beneficiaryName": "John Smith",
        "balance": 9900.00
    },
    {
        "accountNumber": "50962027508738960975",
        "beneficiaryName": "John Johnson",
        "balance": 20100.00
    }
   ]
   
5. Получение списка транзакций по конкретному счету (GET)

   ```bash
   http://localhost:8080/simplebank/api/v1/transactions/account/{accountId}
   
   Response Example:
   200 OK
   [
    {
        "action": "TRANSFER",
        "amount": 100.00,
        "timestamp": "2024-10-11T12:04:17.775388",
        "fromAccountNumber": "45374809489246622528",
        "toAccountNumber": "50962027508738960975"
    },
    {
        "action": "TRANSFER",
        "amount": 100.00,
        "timestamp": "2024-10-11T12:59:04.711819",
        "fromAccountNumber": "45374809489246622528",
        "toAccountNumber": "50962027508738960975"
    },
    {
        "action": "TRANSFER",
        "amount": 100.00,
        "timestamp": "2024-10-11T13:12:01.648771",
        "fromAccountNumber": "45374809489246622528",
        "toAccountNumber": "50962027508738960975"
    }
]