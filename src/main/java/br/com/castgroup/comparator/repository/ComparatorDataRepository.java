package br.com.castgroup.comparator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.castgroup.comparator.entity.ComparatorData;

@Repository
public interface ComparatorDataRepository extends CrudRepository<ComparatorData, Long> {

}
