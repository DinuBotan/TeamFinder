const mongoose = require('mongoose')

const ChatRoom = mongoose.model('ChatRoom', {
    teamID: {
        type: String,
        require: true
    },
    messages: {
        type: Array,
        default : []
    }
})

module.exports = ChatRoom