package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.Address;

/**
 * @author Khmel Anton
 */
public interface AddressRepository extends JpaRepository<Address, Long>,
    QuerydslPredicateExecutor<Address> {
}
