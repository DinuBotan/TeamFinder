const express = require('express')
const router = new express.Router()
const ChatRoom = require('../models/chatRoom')
const Message = require('../models/message')
const Team = require('../models/team')

router.post('/chatRooms', async (req, res) => {
    const chatRoom = new ChatRoom(req.body)
    try {
        await chatRoom.save()
        res.status(201).send(chatRoom)
    } catch (e) {
        res.status(400).send(e)
    }
})

router.get('/chatRooms', async (req, res) => {
    try {
        const chatRooms = await ChatRoom.find({})
        const obj = {
            chatRooms: chatRooms
        }
        res.send(obj)
    } catch (e) {
        res.status(500).send()
    }
})

router.get('/chatRooms/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const chatRoom = await ChatRoom.findById(_id)
        if (!chatRoom) {
            return res.status(404).send()
        }
        res.send(chatRoom)
    } catch (e) {
        res.status(500).send()
    }
})

// Add new messages to the array of messages of a chat room.
router.patch('/chatRooms/:id', async (req, res) => {
        const message = new Message(req.body)
    try {
        const filter = { _id: req.params.id }
        const updateDoc = {
            $push: {
                messages: message
            },
        };

        const chatRoom = await ChatRoom.updateOne(filter, updateDoc)

        if (!chatRoom) {
            return res.status(404).send()
        }

        res.send(chatRoom)
    } catch (e) {
        res.status(400).send(e)
    }
})


module.exports = router