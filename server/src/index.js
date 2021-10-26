const express = require('express')
require('./db/mongoose')
const User = require('./models/user')
const Team = require('./models/team')
const userRouter = require('./routers/user')
const teamRouter = require('./routers/team')
const messageRouter = require('./routers/message')

const app = express()
const port = process.env.PORT || 3001

app.use(express.json())
app.use(userRouter)
app.use(teamRouter)
app.use(messageRouter)

app.listen(port, () => {
    console.log('Server is up on port ' + port)
})