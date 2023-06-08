package com.devstack.posapp.repo;

import com.devstack.posapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByPublicId(long id);
    public Optional<Customer> deleteByPublicId(long id);
}
