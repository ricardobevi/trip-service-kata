"use strict";

class User {

    #friends;

    constructor(friends = []){
        this.#friends = friends;
    }

    isFriendsWith(otherUser){
        return this.#friends.some(friend => friend === otherUser);
    }

}


module.exports = User