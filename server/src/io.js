const Message = require("./models/message");
module.exports = function (socket, io, app) {
  console.log("New WebSocket Connection");

  // Will send the message to everyone except the person sending the message
  socket.broadcast.emit("message", "A new user has joined!");

  socket.on("join", async (user) => {
    user = JSON.parse(user);
    await socket.join(user.room);
    console.log(socket.id + " now in rooms ", socket.rooms);

    // This only sends data to a specific client
    socket.emit("message", "Welcome to the chat");

    // socket.broadcast.to(room).emit('message', generateMessage(`${username} has joined!`))
  });

  socket.on("leaveChat", async (user) => {
    user = JSON.parse(user);
    await socket.leave(user.room);
    console.log(socket.id + " After leaving now in rooms ", socket.rooms);
  })

  // We want to emit to every connection:
  socket.on("sendMessage", (message) => {
    message = JSON.parse(message);
    console.log("In sendMessage: " + socket.id + " now in rooms ", socket.rooms);
    socket.to(message.chatRoomID).emit("chatMessage", message);
    console.log("Emitted message: ", message);

    // Persist message to database
    new Message({
      author: message.author,
      content: message.content,
      timestamp: message.timestamp,
      chatRoom: message.chatRoom,
      teamId: message.chatRoomID
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
