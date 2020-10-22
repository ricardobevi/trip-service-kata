"use strict";

let UserSession = require('./UserSession');
let TripDAO = require('./TripDAO');

class TripService {

    getTripsByUser(user) {        
        let loggedUser = this.getLoggedUser();

        if (loggedUser == null) {
            throw new Error('User not logged in.');
        }

        if (user.isFriendsWith(loggedUser)) {
            return this.findTripsByUser(user);
        }

        return [];
    }


    getLoggedUser(){
        return UserSession.getLoggedUser();
    }


    findTripsByUser(user){
        return TripDAO.findTripsByUser(user);
    }

}

module.exports = TripService
