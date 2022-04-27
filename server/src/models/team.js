const mongoose = require('mongoose')
const { Schema } = require('mongoose')
const validator = require('validator')
const mongoose_fuzzy_searching = require("mongoose-fuzzy-searching");

const TeamSchema = new Schema({
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
      default: "",
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
      default: "",
    },
    city: {
      type: String,
      require: false,
      default: "",
    },
    language: {
      type: String,
      require: false,
      default: "English",
    },
    chatRoomId: {
      type: String,
      require: false,
      default: "",
    },
    imageId: {
      type: Number,
      require: false,
      default: 1,
    },
  });

  TeamSchema.plugin(mongoose_fuzzy_searching, {
    fields: ["name", "category"],
  })

const Team = mongoose.model(
  "Team", TeamSchema
);

module.exports = Team