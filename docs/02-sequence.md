# Sequence Diagram – User Login and Profile Retrieval

## Overview

This document describes the sequence of interactions between the client
and the internal components of the system during user authentication
and user profile retrieval operations.

The sequence explanation focuses on how HTTP requests are handled
by the controller and service layers of the application.

---

## Sequence 1: User Login

### Participants
- Client
- AuthController
- AuthService

### Sequence Flow

1. The client sends an HTTP POST request to `/api/auth/login`
   containing the user's email and password.
2. The AuthController receives the request and performs basic request handling.
3. The AuthController forwards the credentials to the AuthService.
4. The AuthService validates the provided credentials against mocked data.
5. The AuthService determines whether authentication is successful or not.
6. The AuthService returns the authentication result to the AuthController.
7. The AuthController generates an HTTP response:
    - `200 OK` if authentication is successful
    - `401 Unauthorized` if authentication fails
8. The client receives the authentication response.

---

## Sequence 2: View Current User Profile

### Participants
- Client
- UserController
- UserService

### Sequence Flow

1. The client sends an HTTP GET request to `/api/users/me`.
2. The UserController receives the request.
3. The UserController delegates the request to the UserService.
4. The UserService retrieves the current user information
   from the mocked data source.
5. The UserService returns the user data to the UserController.
6. The UserController sends the user data back to the client
   as a JSON response with HTTP status `200 OK`.
7. The client receives and processes the user profile information.

---

## Purpose of the Sequence Diagrams

These sequence flows demonstrate:
- The separation of concerns between controller and service layers
- The lifecycle of an HTTP request within the application
- How business logic is isolated from request handling logic

The sequence diagrams provide a clear understanding of how
user-related operations are processed internally by the system.

```mermaid
sequenceDiagram
    participant Client
    participant AuthController
    participant AuthService

    Client->>AuthController: POST /api/auth/login
    AuthController->>AuthService: validateCredentials(email, password)
    AuthService-->>AuthController: authentication result
    AuthController-->>Client: HTTP 200 OK / 401 Unauthorized

