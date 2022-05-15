const express = require("express");
const request = require("supertest");
const app = express();

describe("POST team", () => {
  it("Should return a new team", (done) => {
    request("http://localhost:3001")
      .post("/teams")
      .send({
        name: "test team",
        size: 99,
        category: "test"
      })
      .set("Accept", "*/*")
      .expect((response) => {
        if (response.body.size !== 99)
          throw new Error("Team size not equal!");
      })
      .expect(201, done);
  });
});

describe("GET teams", () => {
  it("Should return an array of teams", (done) => {
    request("http://localhost:3001")
      .get("/teams")
      .expect((response) => {
        if (response.body.teams.length !== 1) throw new Error("Wrong body length");
        if (response.body.teams[0].size !== 99) throw new Error("Wrong team size");
      })
      .expect(200, done);
  });
});