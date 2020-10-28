package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class TripServiceTest {

    val ANY_USER = User()
    val LONDON = Trip()

    private lateinit var tripService: TripServiceTestable

    private var user: User = User()
    private var loggedUser: User = User()

    private var usersTripList: MutableList<Trip> = mutableListOf()
    private var tripList: List<Trip> = emptyList()

    @Test
    fun `should throw user not logged exception when user is a guest`(){
        givenGuestUser()

        assertThrows<UserNotLoggedInException> {
            whenGetTripsByUserIsCalled()
        }
    }

    @Test
    fun `should return empty trip list when user has no friends`(){
        givenLoggedUser()
        givenUserHasNoFriends()

        whenGetTripsByUserIsCalled()

        thenTripListShouldBeEmpty()
    }

    @Test
    fun `should return empty trip list when user is not friends with logged user`(){
        givenLoggedUser()
        givenUserIsFriendsWith(ANY_USER)

        whenGetTripsByUserIsCalled()

        thenTripListShouldBeEmpty()
    }

    @Test
    fun `should return user's trip list when user is friends with logged user`(){
        givenLoggedUser()
        givenUserIsFriendsWith(loggedUser)
        givenUserHasATripTo(LONDON)

        whenGetTripsByUserIsCalled()

        thenTripListShouldHaveTripsTo(listOf(LONDON))
    }

    private fun givenGuestUser(){
        tripService = TripServiceTestable(null)
    }

    private fun givenLoggedUser(){
        loggedUser = User()
        tripService = TripServiceTestable(loggedUser)
    }

    private fun givenUserHasNoFriends() {
        user = User()
    }

    private fun givenUserIsFriendsWith(anyUser: User) {
        user.addFriend(anyUser)
    }

    private fun givenUserHasATripTo(trip: Trip) {
        usersTripList.add(trip)
        tripService = TripServiceTestable(loggedUser, usersTripList)
    }

    private fun whenGetTripsByUserIsCalled() {
        tripList = tripService.getTripsByUser(user)
    }

    private fun thenTripListShouldBeEmpty() {
        assertEquals(Collections.emptyList<Trip>(), tripList)
    }

    private fun thenTripListShouldHaveTripsTo(trips: List<Trip>) {
        assertEquals(trips, tripList)
    }

    class TripServiceTestable(
            private var loggedUser: User? = User(),
            private val trips: List<Trip> = ArrayList()) : TripService(){

        override fun getLoggedUser() = loggedUser
        override fun findTripsFor(user: User) = trips
    }

}
