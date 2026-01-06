## Test Scenarios

This document describes the test scenarios for the authentication and user-related endpoints.
The application currently uses mocked data and does not include database integration.

---

## Auth – Login

### TC-AUTH-001: Successful login with valid credentials

**Description:**  
Verifies that a user can successfully log in when valid credentials are provided.

**Given:**
- Email: `test@mail.com`
- Password: `123456`
- Authentication data is mocked in the service layer.

**When:**
- A `POST` request is sent to `/api/auth/login`.

**Then:**
- HTTP Status: `200 OK`
- Response Body: `"LOGIN_OK"`

**Postconditions:**
- User is considered authenticated for the current session (mocked behavior).

---

### TC-AUTH-002: Login failure due to wrong password

**Description:**  
Ensures that the system rejects login attempts with an incorrect password.

**Given:**
- Email: `test@mail.com`
- Password: `wrong`

**When:**
- A `POST` request is sent to `/api/auth/login`.

**Then:**
- HTTP Status: `401 Unauthorized`
- Response Body: `"LOGIN_FAIL"`

---

### TC-AUTH-003: Login failure due to missing email

**Description:**  
Validates request body validation when the email field is missing.

**Given:**
- Email: not provided
- Password: `123456`

**When:**
- A `POST` request is sent to `/api/auth/login`.

**Then:**
- HTTP Status: `400 Bad Request`
- Request is rejected due to invalid input.

---

### TC-AUTH-004: Login failure due to missing password

**Description:**  
Validates request body validation when the password field is missing.

**Given:**
- Email: `test@mail.com`
- Password: not provided

**When:**
- A `POST` request is sent to `/api/auth/login`.

**Then:**
- HTTP Status: `400 Bad Request`
- Request is rejected due to invalid input.

---

## User – Current User

### TC-USER-001: Retrieve current user information

**Description:**  
Checks that the system returns the current user's profile information.

**Given:**
- User is considered authenticated.
- User data is mocked in the service layer.

**When:**
- A `GET` request is sent to `/api/users/me`.

**Then:**
- HTTP Status: `200 OK`
- Response Body contains the following fields:
  - `id`
  - `email`
  - `firstName`
  - `lastName`
  - `userNo`

**Postconditions:**
- User profile information is successfully returned to the client.

---

### TC-USER-002: Retrieve current user – user not found (future scenario)

**Description:**  
Defines expected system behavior when the current user cannot be found.
(This scenario is for future implementation when database integration is added.)

**Given:**
- User does not exist in the system.

**When:**
- A `GET` request is sent to `/api/users/me`.

**Then:**
- HTTP Status: `404 Not Found`

---

## Notes

- These test scenarios are based on mocked data.
- No database or persistent storage is currently implemented.
- Security mechanisms such as JWT or session management are not included yet.
