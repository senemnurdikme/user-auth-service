## ER Diagram – User

### Entity: users

| Column     | Type    | Constraints                  |
|-----------|---------|------------------------------|
| id        | BIGINT  | PK, auto-increment           |
| email     | VARCHAR | UNIQUE, NOT NULL             |
| first_name| VARCHAR | NOT NULL                     |
| last_name | VARCHAR | NOT NULL                     |
| user_no   | VARCHAR | UNIQUE, NOT NULL             |
| password  | VARCHAR | NOT NULL                     |

### Notes
- `email` and `user_no` are unique.
- `password` will be stored as hashed later (not plain text).
