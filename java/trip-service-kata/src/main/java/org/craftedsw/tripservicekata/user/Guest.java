package org.craftedsw.tripservicekata.user;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;

public class Guest extends User {

  @Override
  public List<Trip> getTripsIfIsFriendWith(User user) {
    throw new UserNotLoggedInException();
  }

}
