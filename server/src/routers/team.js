const express = require('express')
const router = new express.Router()
const Team = require('../models/team')

router.post('/teams', async (req, res) => {
    const team = new Team(req.body)

    try {
        await team.save()
        res.status(201).send(team)
    } catch (e) {
        res.status(400).send(e)
    }
})

router.get('/teams', async (req, res) => {
    try {
        const teams = await Team.find({})
        const obj = {
            teams: teams
        }
        res.send(obj)
    } catch (e) {
        res.status(500).send()
    }
})

router.get('/teams/:id', async (req, res) => {
    const _id = req.params.id
    
    try {
        const team = await Team.findById(_id)
        if(!team) {
            return res.status(404).send()
        }
        console.log('Good team: ' + team);
        res.send(team)
    } catch (e) {
        res.status(500).send()
    }
    })


router.patch('/teams/:id', async (req, res) => {
    const updates = Object.keys(req.body)
    const allowedUpdates = ['name', 'size']
    const isValidOperation = updates.every((update) => allowedUpdates.includes(update))
    
    if (!isValidOperation) {
        return res.status(400).send({ error: 'Invalid updates!' })
    }
    
    try {
        const team = await Team.findById(req.params.id)

        updates.forEach((update) => {
            team[update] = req.body[update]
        })

        await team.save()
    
        if (!team) {
            return res.status(404).send()
        }
    
        res.send(team)
    } catch (e) {
        res.status(400).send(e)
    }
}) 

router.delete('/teams/:id', async (req, res) => {
    try {
        const team = await Team.findByIdAndDelete(req.params.id)

        if (!team) {
            return res.status(404).send()
        }

        res.send(team)
    } catch (e) {
        res.status(500).send()
    }
})

module.exports = router