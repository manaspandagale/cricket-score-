# Live Cricket Score Platform

## 1. Project Title
Live Cricket Score Simulation Platform

## 2. Project Description
A complete Spring Boot simulation for live cricket matches. It supports creating teams, players, and matches, and provides a continuous live score calculation mechanism with a responsive dashboard polling for real-time updates.

## 3. Features
- **Team Management**: Create, view, and manage cricket teams.
- **Player Management**: Assign players with roles (BATSMAN, BOWLER, etc.) to teams.
- **Match Management**: Schedule matches and track their status (UPCOMING, LIVE, COMPLETED).
- **Live Event Tracking**: Record ball-by-ball events (runs, wickets, extras).
- **Automated Calculations**: Calculates total score, overs (e.g., 0.1 to 0.6), run rates, economy, and striker/bowler statistics.

## 4. Technologies Used
- Java 17
- Spring Boot 3.x (Web, Data JPA)
- Hibernate
- MySQL
- HTML/CSS/JavaScript (Frontend)
- Maven

## 5. Architecture
The application follows a standard MVC layered architecture:
`Controller -> Service -> Repository -> Database`
Data passes seamlessly between layers using standard DTOs.

## 6. Database Design
- `Team`: Stores country/team information.
- `Player`: Belongs to a team, holds batting/bowling statistics.
- `Match`: Holds the schedule and teams playing.
- *(Additional tables like Innings, BallEvent, and PlayerStats can be built similarly).*

## 7. Installation Requirements
- JDK 17
- MySQL 8.x
- Maven 3.8+
- An IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 8. MySQL Setup
1. Open MySQL workbench or terminal.
2. Run: `CREATE DATABASE cricket_score_db;`
3. Configure `application.properties` with your database credentials.

## 9. How to run backend
1. Open terminal in the project directory.
2. Run `./mvnw spring-boot:run` (Mac/Linux) or `mvnw.cmd spring-boot:run` (Windows).

## 10. Sample Credentials
Database:
`username: root`
`password: root` (Please change this in `application.properties`)

## 11. API List (Sample)
- `GET /api/teams` - Get all teams
- `POST /api/teams` - Create a new team
- *(Extend this for Matches and Players!)*

## 12. Testing Instructions
1. Setup DB.
2. Start the App.
3. Call `http://localhost:8080/api/teams` using Postman.
4. Verify sample seeded data (India, Australia) is returned.
