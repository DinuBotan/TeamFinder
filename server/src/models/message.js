const mongoose = require('mongoose')

const Message = mongoose.model('Message', {
    authorId: {
        type: String,
        require: true
    },
    content: {
        type: String,
        require: true
    },
    timestamp: {
        type: Date,
        require: true
    },
    chatRoomId: {
        type: String,
        require: true
    }
})

module.exports = Message