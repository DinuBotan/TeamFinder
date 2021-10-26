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
    }
})

module.exports = Message