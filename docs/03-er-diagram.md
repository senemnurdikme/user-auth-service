## ER Diagram – User

This document describes the **User** entity that will be used in the application.
Even though the current implementation uses **mock data (no database)**, we document the database design to show how it would look once persistence is added.

---

## Entity: `users`

**Purpose:**  
Stores basic user identity information used by the system.

**Primary Key:**
- `id` uniquely identifies each user record.

**Uniqueness Rules:**
- `email` must be unique (one account per email)
- `user_no` must be unique (internal student/employee number etc.)

---
## ER Diagram – User

### Entity: users

| Column     | Type    | Constraints             | Description                     |
|-----------|---------|-------------------------|---------------------------------|
| id        | BIGINT  | PK, auto-increment      | Unique user identifier          |
| email     | VARCHAR | UNIQUE, NOT NULL        | Login identifier (email)        |
| first_name| VARCHAR | NOT NULL                | User first name                 |
| last_name | VARCHAR | NOT NULL                | User last name                  |
| user_no   | VARCHAR | UNIQUE, NOT NULL        | Internal reference number       |
| password  | VARCHAR | NOT NULL                | Stored securely (hashed)        |

---

## Mermaid ER Diagram

```mermaid
erDiagram
    USERS {
        BIGINT id PK
        VARCHAR email UNIQUE
        VARCHAR first_name
        VARCHAR last_name
        VARCHAR user_no UNIQUE
        VARCHAR password
    }
