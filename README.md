# Mobile Application Development - Assignment #01

This repository contains the Android application developed for Assignment #01 of the Mobile Application Development course.

## Application Overview

This Android application demonstrates fundamental concepts of mobile application development including navigation between activities, form handling, data persistence, and UI design. The application includes various screens and functionalities as specified in the assignment requirements.

## Features

### 1. Splash Screen
- Displays a welcome screen with app branding
- Automatically navigates to Sign In screen after 5 seconds

### 2. Authentication Screens
- **Sign In**: Validates user credentials and navigates to Home screen
- **Sign Up**: Collects user information, saves it using SharedPreferences, and displays a success toast message

### 3. Home Screen
- Welcomes the user with their name retrieved from SharedPreferences
- Provides navigation cards to:
  - Tic-Tac-Toe Game
  - Admission Form

### 4. Game Activity
- Interactive Tic-Tac-Toe game implementation
- Tracks player turns and game state
- Displays win/draw messages and allows restarting

### 5. Admission Form
- Collects prospective student information
- Implements form validation for all fields
- Displays a success toast message upon successful submission

## Concepts Implemented

1. **Activity Lifecycle Management**
   - Proper handling of activity creation and navigation
   - State preservation during configuration changes

2. **User Interface Design**
   - Material Design components (TextInputLayout, CardView)
   - Responsive layouts with ConstraintLayout
   - Custom styling and themes

3. **Data Management**
   - Form validation and error handling
   - SharedPreferences for local data storage
   - Input sanitization and formatting

4. **User Experience**
   - Toast notifications for user feedback
   - Intuitive navigation between screens
   - Form field validation with helpful error messages

5. **Navigation**
   - Intent-based navigation between activities
   - Proper activity stack management

6. **Game Logic Implementation**
   - State tracking for the Tic-Tac-Toe game
   - Win condition checking algorithms
   - Interactive UI elements

## Setup Instructions

1. Clone the repository:
   ```
   git clone https://github.com/itxsamad1/MAD-Assignment1-App-Java.git
   ```

2. Open the project in Android Studio

3. Build and run the application on an emulator or physical device

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 35
- Target SDK: 35
- Java Development Kit 11 or newer

## Screenshots
*(Screenshots would be added here)*

## License
This project is part of academic coursework and is not licensed for commercial use. 