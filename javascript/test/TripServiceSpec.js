"use strict";

let assert = require('assert');
let TripService = require('../src/TripService');
let User = require('../src/User');

describe('TripService', () => {

    it('should throw error when user is not logged', () => {
        let tripService = new TripService();

        tripService.getLoggedUser = () => {return null};

        assert.throws(() => tripService.getTripsByUser(),  /^Error: User not logged in.$/);
    });


    it('should return empty trip list when the user has no friends', () => {
        let tripService = new TripService;

        let user = new User;
        user.getFriends = () => {return []}

        tripService.getLoggedUser = () => {return user};

        assert.deepStrictEqual(tripService.getTripsByUser(user), []);
    });


    it('should return empty trip list when the user is not a friend with the logged user', () => {
        let tripService = new TripService;

        let user = new User;
        user.getFriends = () => {return [new User,new User,new User]}

        let loggedUser = new User;
        tripService.getLoggedUser = () => {return loggedUser};

        assert.deepStrictEqual(tripService.getTripsByUser(user), []);
    });

    it('should return user\'s trip list when the user is a friend with the logged user', () => {
        let tripService = new TripService;

        let user = new User;
        let loggedUser = new User;

        user.getFriends = () => {return [new User,new User,new User,loggedUser]}
        tripService.getLoggedUser = () => {return loggedUser};

        let userTripList = [{to: 'MADRID'}, {to: 'BUENOS_AIRES'}, {to: 'LONDON'}];
        tripService.findTripsByUser = () => {return userTripList};

        assert.deepStrictEqual(tripService.getTripsByUser(user), userTripList);
    });

});
