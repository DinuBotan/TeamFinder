const path = require('path')
const http = require('http')
const express = require('express')
const socketio = require('socket.io')
require('./db/mongoose')
const User = require('./models/user')
const Team = require('./models/team')
const userRouter = require('./routers/user')
const teamRouter = require('./routers/team')
const messageRouter = require('./routers/message')
const chatRoomRouter = require('./routers/chatRoom')
const bodyParser = require('body-parser')

const app = express()
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json())
app.use(userRouter)
app.use(teamRouter)
app.use(messageRouter)
app.use(chatRoomRouter)

// We need to create the server (which express does behind the scenes), because it is required for running socket.io
const server = http.createServer(app)
const io = socketio(server)

const port = process.env.PORT || 3001
const directoryPath = path.join(__dirname)

app.use(express.static(directoryPath)) 

// The way to call an event that happens to our io connection
io.on('connection', (socket) => {
    console.log('New WebSocket Connection')

    // Will send the message to everyone except the person sending the message
    socket.broadcast.emit('message', 'A new user has joined!')

    socket.on('join', (user) => {
        // socket.join(user)
        var json = JSON.parse(user)
        console.log('User joined: ', json.username)
        console.log('In room: ', json.room)

        // This only sends data to a specific client
        socket.emit('message', 'Welcome to the chat')

        // socket.broadcast.to(room).emit('message', generateMessage(`${username} has joined!`))


    })

    // We want to emit to every connection:
    socket.on('sendMessage', (message) => {
        io.emit('message', message)
        console.log('Emitted message: ', message)
    })

    socket.on('message', () => {
        console.log('Received a message!')
    })

    socket.on('disconnect', () => {
        io.emit('message', 'A user has left')
        console.log("Disconnected!")
    })
})

server.listen(port, () => {
    console.log('Server is up on port ' + port)
    console.log(directoryPath)
})