const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

let notes = [];
let nextId = 1;

//CREATE
app.post("/notes", (req, res) => {
    const {title, content} = req.body;
    const note = {
        id: nextId++,
        title,
        content
    };

    notes.push(note);
    res.status(201).json(note);
});

//READ ALL
app.get("/notes", (req, res) => {
    res.json(notes);
});

//READ SINGLE
app.get("/notes/:id", (req, res) => {
    const id = parseInt(req.params.id);
    const note = notes.find(n => n.id === id);

    if (!note)
        return res.status(404).json({message:"Not Found"});

    res.json(note);
});

//UPDATE
app.put("/notes/:id", (req, res) => {
    const id = parseInt(req.params.id);
    const note = notes.find(n => n.id === id);

    if(!note)
        return res.status(404).json({message:"Not Found"});

    note.title = req.body.title;
    note.content = req.body.content;
    res.json(note);
});

//DELETE
app.delete("/notes/:id", (req,res)=>{
    const id = parseInt(req.params.id);
    notes = notes.filter(n=>n.id!=id);

    res.json({
        message:"Deleted"
    });

});

app.listen(3000,()=>{
    console.log("Server running on port 3000");
});