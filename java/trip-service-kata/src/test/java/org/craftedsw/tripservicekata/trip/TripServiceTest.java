package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.Guest;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

  private static final Trip MADRID = new Trip();
  private static final Trip BARCELONA = new Trip();
  private static final Trip BUENOS_AIRES = new Trip();

  TripService tripService;

  User loggedUser;
  User anotherUser;

  List<Trip> anotherUserTrips = new ArrayList<>();

  List<Trip> returnedTrips = new ArrayList<>();

  @BeforeEach
  void beforeEach(){
    this.tripService = new TestableTripService();
  }

  @Test
  void forNotLoggedUserThrowException(){
    givenGuestIsLogged();
    givenAnotherUserWithNoFriends();

    assertThrows(
        UserNotLoggedInException.class,
        this::whenGettingTripsByUser
    );
  }

  @Test
  void forLoggedUserNotAFriendWithOtherCantSeeTrips(){
    givenLoggedUserWithNoFriends();
    givenAnotherUserWithNoFriends();

    whenGettingTripsByUser();

    thenShouldGetNumberOfTips(0);
  }

  @Test
  void forLoggedUserFriendedWithOtherCanSeeTrips(){
    givenLoggedUserWithNoFriends();
    givenAnotherUserWithLoggedFriend();
    givenAnotherUserHasTripsTo(MADRID, BARCELONA, BUENOS_AIRES);

    whenGettingTripsByUser();

    thenShouldGetNumberOfTips(2);
  }

  private void givenAnotherUserWithNoFriends() {
    this.anotherUser = new User();
  }

  private void givenAnotherUserWithLoggedFriend() {
    this.anotherUser = new User();
    this.anotherUser.addFriend(loggedUser);
  }

  private void givenLoggedUserWithNoFriends() {
    loggedUser = new User();
  }

  private void givenGuestIsLogged() {
    loggedUser = new Guest();
  }

  private void givenAnotherUserHasTripsTo(Trip... trips) {
    anotherUserTrips.addAll(Arrays.asList(trips));
    anotherUserTrips.forEach(trip -> anotherUser.addTrip(trip));
  }

  private void whenGettingTripsByUser() {
    returnedTrips = this.tripService.getTripsByUser(anotherUser);
  }

  private void thenShouldGetNumberOfTips(int numberOfTrips) {
    assertEquals(
        numberOfTrips, returnedTrips.size(),
        "Number of trips should be " + numberOfTrips 
    );
  } 


  private class TestableTripService extends TripService {

    @Override
    protected User loggedUser() {
      return loggedUser;
    }

  }
}
