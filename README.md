# Wine Search Sample App

Steps to run the app locally:

1. Start the Wine API Spring Boot App (/wine-api/README.md). Open a terminal:

    `$ cd wine-api`
    
    `$ ./mvnw spring-boot:run`
    
    Then access Swagger UI to test available REST APIs:
    
    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    
    ```
   Mock database only contains three lot codes:
    - 11YVCHAR001
    - 11YVCHAR002
    - 15MPPN002-VK
   ```

2. Start the Wine API React App (/wine-client/README.md). Open a new terminal:

    `$ cd wine-client`
    
    `$ npm start`
    
    Then access the app at [http://localhost:3000](http://localhost:3000)
    
   ```
   Mock database only contains three lot codes:
    - 11YVCHAR001
    - 11YVCHAR002
    - 15MPPN002-VK
   ```