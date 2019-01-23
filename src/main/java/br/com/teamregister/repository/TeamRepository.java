package br.com.teamregister.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.teamregister.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
