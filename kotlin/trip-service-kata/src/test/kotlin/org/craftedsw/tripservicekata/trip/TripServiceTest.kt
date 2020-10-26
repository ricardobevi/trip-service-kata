package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class TripServiceTest {

    val ANY_USER = User()

    private lateinit var tripService: TripServiceTestable

    private var user: User = User()
    private var loggedUser: User? = User()

    private var tripList: List<Trip> = ArrayList<Trip>()

    @Test
    fun `should throw user not logged exception when user is not logged`(){
        givenNotLoggedInUser()

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

    private fun givenNotLoggedInUser(){
        loggedUser = null
        tripService = TripServiceTestable(loggedUser)
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

    private fun whenGetTripsByUserIsCalled() {
        tripList = tripService.getTripsByUser(user)
    }

    private fun thenTripListShouldBeEmpty() {
        assertEquals(Collections.emptyList<Trip>(), tripList)
    }

    class TripServiceTestable(private var loggedUser: User?) : TripService(){
        override fun getLoggedUser() = loggedUser
    }

}
