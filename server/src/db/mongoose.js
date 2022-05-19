const mongoose = require('mongoose')

// We use mongoose to connect to the datbase and "team-finder-api" will be the name of the database.
mongoose.connect('mongodb://127.0.0.1:27017/team-finder-api', {
})

// mongoose.connect("mongodb://127.0.0.1:27017/team-finder-test-api", () => {
//     mongoose.connection.db.dropDatabase();
// });
