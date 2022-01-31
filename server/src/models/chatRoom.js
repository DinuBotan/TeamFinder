const mongoose = require('mongoose')

const ChatRoom = mongoose.model('ChatRoom', {
    teamID: {
        type: String
    },
    messages: {
        type: Array,
        "default" : []
    }
})

module.exports = ChatRoom