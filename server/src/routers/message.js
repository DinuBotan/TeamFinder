const express = require("express");
const router = new express.Router();
const Message = require("../models/message");

router.post("/messages", async (req, res) => {
  const message = new Message(req.body);
  message.timestamp = Date.now();

  try {
    await message.save();
    res.status(201).send(message);
  } catch (e) {
    res.status(400).send(e);
  }
});

router.get("/messages", async (req, res) => {
  try {
    const messages = await Message.find({});
    const obj = {
      messages: messages,
    };
    res.send(obj);
  } catch (e) {
    res.status(500).send();
  }
});

const msg = [
  {
    id: "new ObjectId('6154be2a60e87e140d97c094')",
    authorId: "",
    content: "No messages here!",
    timestamp: Date.now(),
    chatRoomId: "",
  },
];

router.get("/messages/:teamId", async (req, res) => {
  const _id = req.params.teamId;

  try {
    const messages = await Message.find({ chatRoomId: _id });
    if (messages.length === 0) {
      console.log("No messages for: " + _id);
      msg[0].chatRoomId = _id;
      const obj = {
        messages: msg
      }
      res.send(obj);
    } else {
      const obj = {
        messages: messages,
      };
      res.send(obj);
    }
  } catch (e) {
    console.log("Error for message with id: " + _id);
    res.status(500).send();
  }
});

module.exports = router;
