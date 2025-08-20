# 🔗 URL Shortener - Spring Boot Backend

A simple and secure **URL Shortener backend** built with **Spring Boot** and **JWT-based authentication**.  
This service allows users to register, log in, shorten URLs, track analytics, and securely redirect to original URLs.

---

## 🚀 Features
- User authentication & authorization with **JWT**
- Generate unique short URLs (handles duplicate conflicts)
- Redirect short URL → original URL
- Track click events & analytics
- View user-specific shortened URLs
- Date-range based analytics and click statistics

---

## ⚙️ Tech Stack
- **Java 17+**
- **Spring Boot 3+**
- **Spring Security + JWT**
- **Hibernate / JPA**
- **H2 / MySQL / PostgreSQL** (configurable)
- **Lombok**

---

## 🔑 Authentication
- JWT-based authentication  
- Roles: `USER`  

All endpoints under `/api/urls/**` require authentication.  
Use the `Authorization: Bearer <token>` header in your requests after login.

---

## 📌 API Endpoints

### 🧑 Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/public/register` | Register a new user |
| POST | `/api/auth/public/login` | Login and get a JWT token |

---

### 🔗 URL Shortening
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/urls/shorten` | ✅ USER | Shorten a long URL. Request body: `{ "originalUrl": "https://example.com" }` |
| GET | `/api/urls/myurls` | ✅ USER | Get all shortened URLs for the logged-in user |
| GET | `/{shortUrl}` | ❌ Public | Redirect to the original URL |

---

### 📊 Analytics
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/urls/analytics/{shortUrl}?startDate=2024-12-01T00:00:00&endDate=2024-12-07T23:59:59` | ✅ USER | Get detailed click events for a given short URL between date ranges |
| GET | `/api/urls/totalClics?startDate=2024-12-01&endDate=2024-12-07` | ✅ USER | Get total clicks grouped by date for the logged-in user |

---

## 🛡️ Handling Duplicates
- The service ensures that duplicate short URLs are **not generated**.  
- If a generated short URL already exists, a new one is created until uniqueness is guaranteed.  

---

## ▶️ Run the Project

### 1️⃣ Clone the repository
```bash
git clone https://github.com/your-username/url-shortener-backend.git
cd url-shortener-backend
````

### 2️⃣ Build and run

```bash
./mvnw spring-boot:run
```

### 3️⃣ Access the API

* Base URL: `http://localhost:8080`
* Example redirect: `http://localhost:8080/abc123`

---

## ✅ Example Usage

### Register

```http
POST /api/auth/public/register
Content-Type: application/json

{
    "username" : "nahid",
    "email" : "nahid@gmail.com",
    "role" : ["USER_ROLE"],
    "password" : "passwordnahid"
}
```

### Login

```http
POST /api/auth/public/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "securePass123"
}
```

*Response:*

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

### Shorten a URL

```http
POST /api/urls/shorten
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "originalUrl": "https://www.google.com"
}
```

*Response:*

```json
{
  "shortUrl": "abc123",
  "originalUrl": "https://www.google.com"
}
```

---

## 📌 Future Improvements

* Admin dashboard for managing users and URLs
* Expiry dates for shortened URLs
* Rate limiting / throttling
* Custom aliases for URLs

---

## 📄 License

This project is licensed under the MIT License.

```

---

⚡ This README is ready to drop into your repo.  
Do you want me to also make a **sequence diagram (image)** for how the request flow works (Register → Login → Shorten URL → Redirect)? That would make your project README stand out.
```
