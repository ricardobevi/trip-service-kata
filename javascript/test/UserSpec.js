"use strict";

let assert = require('assert');
let User = require('../src/User');

describe('User', () => {

    it('should return false if not friends with other user', () => {
        let user = new User();
        let otherUser = new User();

        assert.strictEqual(user.isFriendsWith(otherUser), false);
    });

    it('should return true if friends with other user', () => {
        let otherUser = new User();
        let user = new User([otherUser]);

        assert.strictEqual(user.isFriendsWith(otherUser), true);
    });

});