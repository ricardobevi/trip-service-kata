package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

open class TripService {

    fun getTripsByUser(user: User): List<Trip> {

        val loggedUser: User = getLoggedUser() ?: throw UserNotLoggedInException()

        if (user.isFriendsWith(loggedUser)) {
            return findTripsFor(user)
        }

        return ArrayList<Trip>()
    }

    open fun findTripsFor(user: User) = TripDAO.findTripsByUser(user)
    open fun getLoggedUser() = UserSession.instance.loggedUser

}
