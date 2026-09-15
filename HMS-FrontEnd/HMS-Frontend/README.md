# MediCare HMS – Java Full Stack Hospital Management System

## Frontend
Vanilla HTML, CSS and JavaScript frontend integrated with the Spring Boot REST backend.

## Architecture
Frontend → REST API → Spring Boot Controller → Service → Spring Data JPA/Hibernate → PostgreSQL

## Modules
Patients, Departments, Doctors, Appointments, Check-In, Consultations, Diagnoses, Prescriptions, Lab Tests, Lab Results, Medicines, Bills and Payments.

## Run
1. Start PostgreSQL and the Spring Boot backend on `http://localhost:8080`.
2. Serve this folder using VS Code Live Server (port 5500/5501 is supported by the backend CORS configuration).
3. Open `index.html`.
4. Create a user or sign in with an existing backend user.

## Notes
- The frontend uses `js/api.js` as the central HTTP integration layer.
- `js/module.js` provides the shared CRUD UI for the business modules.
- Lab Tests select an Appointment; the backend derives the related Patient from that Appointment.
- Payment creation is handled by the backend and marks the linked Bill as `PAID`.
- This is a B.Tech project/demo application; authentication currently uses the backend's simple user login flow rather than production JWT/Spring Security.
