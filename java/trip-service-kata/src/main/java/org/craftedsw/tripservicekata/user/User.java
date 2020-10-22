package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;

public class User {

	private List<Trip> trips = new ArrayList<>();
	private List<User> friends = new ArrayList<>();

	public void addFriend(User user) {
		friends.add(user);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> getTripsIfIsFriendWith(User user) {
		return isFriendWith(user) ? new ArrayList<>(user.trips) : new ArrayList<>();
	}

	private boolean isFriendWith(User otherUser) {
		return otherUser.friends.contains(this);
	}
}
