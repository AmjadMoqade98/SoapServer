package com.Training.BackEnd.repository;

import com.Training.BackEnd.model.Bundle;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends AerospikeRepository<Bundle, Integer> {
}
