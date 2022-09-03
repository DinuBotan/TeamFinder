# TeamFinder
TeamFinder is a full-stack application that is meant to provide users with a tool for effective team finding and management. The application is Android-based since Android is a leading mobile operating system worldwide. The backend-server was mainly built in the form of an API that provides application persistence and scalability.

### Demo
Just a part of the application features are presented in the demo below. In addition to these, users can authenticate and will have to log in with their credentials before using the app, they have the options to join existing teams, leave teams they are part of or even create new teams.
##### Teams browsing, searching and filtering functionality
<div>
<img width="250" alt="Screenshot 2021-08-26 at 12 08 28" src="https://user-images.githubusercontent.com/61206601/187696746-390414ce-7aab-49c5-8380-16431493a649.gif">
<img width="250" alt="Screenshot 2021-08-26 at 12 08 28" src="https://user-images.githubusercontent.com/61206601/187696770-1adbdc1f-2a34-4533-b90a-5ff73f4e1a7a.gif">
<img width="250" alt="Screenshot 2021-08-26 at 12 08 28" src="https://user-images.githubusercontent.com/61206601/187696788-94d7c871-8772-4e22-80bf-a5daa4bf8e2a.gif">
<div>

##### Chatting functionality within teams

<img width="450" alt="Screenshot 2021-08-26 at 12 08 28" src="https://user-images.githubusercontent.com/61206601/187696807-e4b3cd0b-45a9-4504-8d52-d6b52b33951b.gif">

### Tech stack
- Jetpack Compose
- Node.js
- MongoDB
- Socket.IO
- Express.js
- Mongoose
- Supertest

### Client architecture
The android application is structured in multiple screens. Each screen is a composable function and the logic for switching between screens is contained in a navigation component in the main activity. The following diagram contains the main application components and some of the most important actions that can be performed from each, with the specific data flow that results from them.
<img width="684" alt="Screenshot 2022-08-31 at 15 51 37" src="https://user-images.githubusercontent.com/61206601/187695189-9e8bcd30-4628-4343-ad19-8b472fb1f3c5.png">

### Server structure
The following diagram presents the structure of the server side of the project with its main components and the connections between them. The API routes and data models are separated and depicted as some of the most important parts of the server.
Besides providing data flow between the client and the database, the server contains important logic for data manipulation. One example is hashing the password users provide when creating an account using the NPM (Node Package Manager) package “bcrypt”. This is done right before saving a new user into the database, or when a user modifies their password.
<img width="663" alt="Screenshot 2022-08-31 at 15 53 32" src="https://user-images.githubusercontent.com/61206601/187695535-773ef93f-82d8-4a68-85de-a0070157e86e.png">

### App Data Model
The data model represents an abstract structure of the database and shows the relations between the different data objects. A well-defined data model provides a great support in the development of an information system. The following diagram represents an illustration of the data model.
![DataModel](https://user-images.githubusercontent.com/61206601/156941377-a76c2f8f-b44b-470f-a2d9-274e951f69de.png)

### Run it on your machine
Make sure you have installed Node, MongoDB, Android Studio with Jetpack Compose support
- Clone the project
- After installing MongoDB, make sure to create a data folder where the project's data will be stored and point monngodb to that folder. The command should look something like this with your paths in it: "/Users/dinubotan/DinuBotan/Programming/mongodb/bin/mongod --dbpath=/Users/dinubotan/DinuBotan/Programming/mongodb-teamFinder" This will start the mongodb server.
- Get the backedn server started by running: "npm install" and then "npm run dev"
- Build and run the Android frontend in Android Studio
- Make sure to configure an android emulator (I used Pixel 5 API 30) or connect to your android device.
- In the Android project, TeamsApi.kt file,  make sure to change the IP address in the baseUrl with your own IP address. That is needed for the connection between the client and the server since everything is running on local.
- Enjoy :)
