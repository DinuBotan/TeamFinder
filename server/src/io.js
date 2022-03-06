const Message = require("./models/message");
module.exports = function (socket, io, app) {
  console.log("New WebSocket Connection");

  // Will send the message to everyone except the person sending the message
  socket.broadcast.emit("message", "A new user has joined!");

  socket.on("join", (user) => {
    // socket.join(user)
    var json = JSON.parse(user);
    console.log("User joined: ", json.username);
    console.log("In room: ", json.room);

    // This only sends data to a specific client
    socket.emit("message", "Welcome to the chat");

    // socket.broadcast.to(room).emit('message', generateMessage(`${username} has joined!`))
  });

  // We want to emit to every connection:
  socket.on("sendMessage", (message) => {
    io.emit("message", message);
    console.log("Emitted message: ", message);

    // Persist message to database
    new Message({
      author: "Dinu",
      content: message,
      timestamp: "12:00",
      chatRoom: "Toom",
    }).save((err) => {
      if (err) {
        return console.log("error");
      }
    });
  });

  socket.on("message", () => {
    console.log("Received a message!");
  });

  socket.on("disconnect", () => {
    io.emit("message", "A user has left");
    console.log("Disconnected!");
  });
};
