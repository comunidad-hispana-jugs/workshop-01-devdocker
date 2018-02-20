package org.thetechoddbug.devdocker;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "reservations", path = "reservations")
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByReservationOwner(@Param("reservationOwner") String reservationOwner);

	Collection<Reservation> findByReservationOwnerContainsIgnoreCase(String filterText);

	Reservation findOne(Long id);

}