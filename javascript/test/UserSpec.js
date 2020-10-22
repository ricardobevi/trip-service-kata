"use strict";

let assert = require('assert');
let User = require('../src/User');

describe('User', () => {

    it('should throw error when user is not logged', () => {
        let tripService = new TripService();

        tripService.getLoggedUser = () => {return null};

        assert.throws(() => tripService.getTripsByUser(),  /^Error: User not logged in.$/);
    });

});