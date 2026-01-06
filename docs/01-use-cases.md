## User Use Cases

### UC-01: View Current User Profile

**Actor:** Authenticated User

**Description:**  
The user requests their own profile information after logging in.

**Preconditions:**
- User is authenticated

**Main Flow:**
1. User sends GET request to `/api/users/me`
2. System retrieves current user information
3. System returns user details

**Response Data:**
- id
- email
- firstName
- lastName
- userNo

**Postconditions:**
- User profile information is displayed
