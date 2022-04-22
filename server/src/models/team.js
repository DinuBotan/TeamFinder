const mongoose = require('mongoose')
const validator = require('validator')

const Team = mongoose.model("Team", {
  name: {
    type: String,
    require: true,
    trim: true,
  },
  size: {
    type: Number,
    default: 10,
    min: 2,
    max: 100,
  },
  members: {
    type: Array,
    default: [],
  },
  category: {
    type: String,
    require: false,
    default: '',
  },
  creationDate: {
    type: Date,
    require: true,
  },
  location: {
    type: String,
    require: false,
    default: "Remote",
  },
  country: {
    type: String,
    require: false,
    default: '',
  },
  city: {
    type: String,
    require: false,
    default: '',
  },
  language: {
    type: String,
    require: false,
    default: "English",
  },
  chatRoomId: {
    type: String,
    require: false,
    default: '',
  },
  imageId: {
    type: Number,
    require: false,
    default: 1
  }
});

module.exports = Team