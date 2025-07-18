
# Library Manager: LEVEIN coding test: Chalaka Ellawala

This application is capable of allowing the members to borrow/ return books, Admins to delete books, and everyone to view the books borrowed by a member(This will return a paginated response). The project will be available in the **`library_manager`** folder.

## 🔐 Authentication

Basic Authentication is configured. The credentials are as follows:

- **Admin User**  
  Username: `admin`  
  Password: `admin123`

- **Normal User**  
  Username: `user`  
  Password: `user123`

> 🔒 Only the `admin` can access the DELETE endpoint `/api/books/delete`. If a normal user tries to delete a book, they will receive a `403 Forbidden` error in both Postman and Swagger.

The authentication configuration can be found in:  
📄 `src/main/java/chalaka/ellawala/library/manager/config/SecurityConfig.java`

## 📓 API Documentation

Swagger UI is available at:  
**http://localhost:8080/swagger-ui/index.html#/**

When directing to the above URL, the user will be asked to provide the username and password. For that, one of the above mentioned details must be provided.

## 💾 Database

The app connects to a PostgreSQL database with the following details:

```
spring.datasource.url=jdbc:postgresql://localhost:5433/library_manager
spring.datasource.username=postgres
spring.datasource.password=admin123
```

These details are set in `application.properties` you can change them based on your DB configurations.

## 🧾 DB Scripts

Once the DB configuration is done, run the SQL scripts provided in the **`db_scripts`** folder.

## 🧾 Using the app with Postman

A postman collection is provided in the base directory with the name **`LibraryManagerPostmanCollection.postman_collection`**. All the requests in the collection are configured with the Basic Auth Authorization. **`http://localhost:8080/api/books/delete`** API will be using admin/admin123 as the credentials and the other APIs are using user/user123 as credentials. You can use other APIs with the admin/admin123 as well.
