## Test Scenarios

### Auth - Login

#### TC-AUTH-001: Login success
- Given: email = test@mail.com, password = 123456
- When: POST /api/auth/login
- Then: 200 OK
- And: body = "LOGIN_OK"

#### TC-AUTH-002: Login fail (wrong password)
- Given: email = test@mail.com, password = wrong
- When: POST /api/auth/login
- Then: 401 Unauthorized
- And: body = "LOGIN_FAIL"

#### TC-AUTH-003: Login fail (missing email)
- Given: email is missing, password = 123456
- When: POST /api/auth/login
- Then: 400 Bad Request

#### TC-AUTH-004: Login fail (missing password)
- Given: email = test@mail.com, password is missing
- When: POST /api/auth/login
- Then: 400 Bad Request


### User - Current User

#### TC-USER-001: Get current user
- Given: user exists (stub)
- When: GET /api/users/me
- Then: 200 OK
- And: response JSON contains:
    - id
    - email
    - firstName
    - lastName
    - userNo

#### TC-USER-002: Get current user - not found (future case)
- Given: user does not exist
- When: GET /api/users/me
- Then: 404 Not Found
