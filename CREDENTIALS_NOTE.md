# Credentials Handling Update

- Hardcoded DB credentials were removed from `src/shon_daniel/data_base/DBapi.java`.
- Add a real `config.properties` file alongside `src/resources/config.properties.example`:
  ```properties
  db.url=jdbc:postgresql://localhost:5432/college
  db.user=postgres
  db.password=afeka
  ```
- `config.properties` is git-ignored to prevent accidental commits.
