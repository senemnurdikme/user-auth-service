## Sequence Diagram – Get Current User

### Actors
- User
- UserController
- UserService

### Flow
1. User sends GET request to `/api/users/me`
2. UserController receives the request
3. UserController calls UserService.getCurrentUser()
4. UserService returns User object
5. UserController returns User response to client
