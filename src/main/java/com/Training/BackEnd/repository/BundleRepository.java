package com.Training.BackEnd.repository;

import com.Training.BackEnd.dao.BundleDao;
import org.springframework.context.annotation.Scope;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends AerospikeRepository<BundleDao, Integer> {
}
