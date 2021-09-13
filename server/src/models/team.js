const mongoose = require('mongoose')
const validator = require('validator')

const Team = mongoose.model('Team', {
    name: {
        type: String,
        required: true,
        trim: true
    },
    size: {
        type: Number,
        default: 10,
        min: 2,
        max: 100
    }
})

module.exports = Team