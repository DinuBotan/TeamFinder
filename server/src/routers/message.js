const express = require("express");
const router = new express.Router();
const Message = require("../models/message");

router.post("/messages", async (req, res) => {
  const message = new Message(req.body);

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

const msg = [{
  id: "new ObjectId('6154be2a60e87e140d97c094')",
  author: "",
  content: "No messages here!",
  timestamp: "",
  chatRoom: "",
  teamId: "",
}];

router.get("/messages/:teamId", async (req, res) => {
  console.log("Req params: " + JSON.stringify(req.params));
  const _id = req.params.teamId;

  try {
    const messages = await Message.find({ teamId: _id });
    if (messages.length === 0) {
      console.log("No messages for: " + _id);
      msg[0].teamId = _id;
      const obj = {
        messages: msg
      }
      res.send(obj);
    } else {
      const obj = {
        messages: messages,
      };
      console.log("Good message: " + messages);
      res.send(obj);
    }
  } catch (e) {
    console.log("Error for message with id: " + _id);
    res.status(500).send();
  }
});

module.exports = router;
