const mongoose = require('mongoose')

const Message = mongoose.model('Message', {
    author: {
        type: String
    },
    content: {
        type: String
    },
    timestamp: {
        type: String
    },
    chatRoom: {
        type: String
    },
    teamId: {
        type: String
    }
})

module.exports = Message