# Spring Cache Demo

## Test run

1. Start the embedded servlet container after starting MySQL:
```bash
./gradlew bootRun
```

2. Save the data:
```bash
curl -X POST http://localhost:8080/caches -H 'Content-Type: application/json' -d '{"cachekey":"key", "cachevalue": "value", "type": "string"}'
```

3. Query the saved data by its key:
```bash
curl http://localhost:8080/caches/key
```

4. Update the saved data by its key:
```bash
curl -X PUT http://localhost:8080/caches/key -H 'Content-Type: application/json' -d '{"cachevalue": "updatedValue", "type": "string"}'
```

5. Delete the saved data by its key:
```bash
curl -X DELETE http://localhost:8080/caches/key
```

## References
* https://start.spring.io/
* https://adoptopenjdk.net/installation.html
