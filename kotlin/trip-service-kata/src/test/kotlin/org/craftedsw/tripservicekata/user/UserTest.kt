package org.craftedsw.tripservicekata.user

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `should return true if user is friends with other user`(){

        val user = User()
        val otherUser = User()

        user.addFriend(otherUser)

        assertTrue(user.isFriendsWith(otherUser))
    }

    @Test
    fun `should return false if user is not friends with other user`(){
        val user = User()
        val otherUser = User()

        assertFalse(user.isFriendsWith(otherUser))
    }

}
