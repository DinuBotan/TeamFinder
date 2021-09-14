const mongoose = require('mongoose')
const validator = require('validator')
const bcrypt = require('bcryptjs')

// Mongoose schema that maps to the MongoDB User collection.
const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        trim: true
    },
    email: {
        type: String,
        unique: true,
        require: true,
        trim: true,
        lowercase: true,
        validate(value) {
            if (!validator.isEmail(value)) {
                throw new Error('Email is invalid!')
            }
        }
    },
    password: {
        type: String,
        require: true,
        trim: true,
        minlength: 7,
        validate(value) {
            if(validator.contains(value, 'password', {ignoreCase: true})) {
                throw new Error('Contains word password!')
            }
        }
    }
})

// Since we are passing a schema to the model:
// We can write a function that will be accessible by the User model
userSchema.statics.findByCredentials = async (email, password) => {
    const user = await User.findOne({ email })

    if (!user) {
        throw new Error('Unable to login')
    }

    const isMatch = await bcrypt.compare(password, user.password)

    if (!isMatch) {
        throw new Error('Unable to login')
    }

    return user
}

// Using this funciton to do something before a user is saved. 
// We need to return next when we are done, otherwise the program will hang waiting for it.
userSchema.pre('save', async function (next) {
    const user = this

    console.log('just before saving!')

    if (user.isModified('password')) {
        user.password = await bcrypt.hash(user.password, 8)
    }

    next()
})

const User = mongoose.model('User', userSchema)

module.exports = User