const express = require('express')
const router = new express.Router()
const User = require('../models/user')

router.post('/users', async (req, res) => {
    const user = new User(req.body)
    try {
        await user.save()
        res.status(201).send(user)
    } catch (e) {
        res.status(400).send(e)
    }
})

const invalidUser = {
    name: '',
    email: '',
    password: ''
}

router.post('/users/login', async (req, res) => {
    try {
        const user = await User.findByCredentials(req.body.userEmail, req.body.userPassword)
        console.log('Found user: ' + user);
        res.send(user)
    } catch (e) {
        res.status(200).send(invalidUser)
    }
})

router.get('/users', async (req, res) => {
    try {
        const users = await User.find({})
        res.send(users)
    } catch (e) {
        res.status(500).send()
    }
})

router.get('/users/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const user = await User.findById(_id)
        if(!user) {
            return res.status(404).send()
        }
        res.send(user)
    } catch (e) {
        res.status(500).send()
    }
})

router.get("/users/email/:email", async (req, res) => {
  const email = req.params.email;

  try {
    const user = await User.findById(email);
    if (!user) {
      return res.status(404).send();
    }
    res.send(user);
  } catch (e) {
    res.status(500).send();
  }
});

router.patch('/users/:id', async (req, res) => {
    console.log('Trying to patch user: ' + JSON.stringify(req.body))
    const updates = Object.keys(req.body)
    const allowedUpdates = ['name', 'email', 'password', 'teams', 'location', 'country', 'city', 'interests']
    const isValidOperation = updates.every((update) => allowedUpdates.includes(update))

    //TODO: fix error here
    // if (!isValidOperation) {
    //     console.log('Invalid updates')
    //     return res.status(400).send({ error: 'Invalid updates!' })
    // }

    try {
        const user = await User.findById(req.params.id)

        updates.forEach((update) => {
            user[update] = req.body[update]
        })

        await user.save()

        if (!user) {
            return res.status(404).send()
        }

        res.send(user)
    } catch (e) {
        console.log('Error updating user')
        res.status(400).send(e)
    }
}) 

router.delete('/users/:id', async (req, res) => {
    try {
        const user = await User.findByIdAndDelete(req.params.id)

        if (!user) {
            return res.status(404).send()
        }

        res.send(user)
    } catch (e) {
        res.status(500).send()
    }
})

module.exports = router