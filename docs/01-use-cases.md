# User & Authentication Use Cases

## Overview

This document describes the main use cases of the system.
The application is a Spring Boot based REST API that provides
basic authentication and user-related operations.

At the current stage, the system does not use a database.
All data is mocked in the service layer for demonstration purposes.

The purpose of this document is to clearly explain how users
interact with the system and what functionality is provided.

---

## UC-01: User Login

**Actor:** Unauthenticated User

**Description:**  
This use case describes the authentication process of a user
attempting to access the system. The user submits login credentials,
which are validated by the application using mocked authentication
logic implemented in the service layer.

**Preconditions:**
- The application is running successfully.
- The authentication service is available.
- Valid credentials are predefined in the service layer for demonstration purposes.

**Main Flow:**
1. The user sends an HTTP POST request to `/api/auth/login`.
2. The request body contains the user's email and password.
3. The controller forwards the request to the authentication service.
4. The authentication service validates the credentials against mocked data.
5. The system determines whether the credentials are valid or invalid.
6. The system generates an appropriate HTTP response.

**Response Data:**
- HTTP Status:
    - `200 OK` if authentication is successful
    - `401 Unauthorized` if authentication fails
- Response Body: Authentication result message.

**Postconditions:**
- On success, the user is considered authenticated for the current interaction.
- On failure, access to protected resources is denied.

---

## UC-02: View Current User Profile

**Actor:** Authenticated User

**Description:**  
This use case allows an authenticated user to retrieve their own
profile information from the system. The user data is provided
by the service layer as mock data, simulating a real user profile.

**Preconditions:**
- The application is running.
- The user has successfully completed the login process.

**Main Flow:**
1. The user sends an HTTP GET request to `/api/users/me`.
2. The UserController receives the request.
3. The controller delegates the request to the UserService.
4. The UserService returns the current user information.
5. The controller sends the user data back to the client in JSON format.

**Response Data:**
- `id`: Unique identifier of the user
- `email`: User's registered email address
- `firstName`: User's first name
- `lastName`: User's last name
- `userNo`: Internal system reference number

**Postconditions:**
- The client successfully receives and displays the user profile information.

---

## System Limitations

- **Data Source:**  
  The application does not use a database. All user and authentication
  data is mocked within the service layer.

- **Security:**  
  Authentication is implemented for demonstration purposes only.
  No JWT, session management, or persistent security mechanism is used.

- **Validation:**  
  Basic input validation is performed to ensure required fields
  are present in the request payload.
