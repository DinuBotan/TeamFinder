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
    require('./io')(socket, io)
})

server.listen(port, () => {
    console.log('Server is up on port ' + port)
    console.log(directoryPath)
})