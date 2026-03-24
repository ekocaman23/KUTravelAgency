# KUTravelAgency

A Java desktop application for managing travel reservations, developed as a COMP132 course project at Koç University.

## Features

- User authentication with customer and admin roles
- Flight, hotel, and taxi search and reservation
- Admin panel for managing all reservations and customers
- Data persistence via CSV and text files
- GUI built with Java Swing

## Tech Stack

- **Language:** Java
- **GUI:** Java Swing
- **IDE:** Eclipse
- **Data:** CSV / TXT file-based storage

## Getting Started

### Prerequisites

- Java 8 or higher
- Eclipse IDE (recommended) or any Java IDE

### Run

1. Clone or download the repository
2. Open the project in Eclipse: `File → Import → Existing Projects into Workspace`
3. Run `Main.java` in the `main` package

## Project Structure

```
KUTravelAgency/
├── assets/
│   ├── FinalKU_Travel_Agency_Dataset_Flights.csv
│   ├── FinalKU_Travel_Agency_Dataset_Hotels.csv
│   ├── FinalKU_Travel_Agency_Dataset_Taxis.csv
│   ├── Customer_List.txt
│   └── Travel_List.txt
├── src/
│   ├── exceptions/
│   ├── login_page/
│   └── main/
└── bin/   ← compiled output, not tracked in git
```

## Author

Ertuğrul Recep Kocaman — ekocaman23@ku.edu.tr  
Koç University, COMP132 — Spring 2025
