package br.com.teamregister.repository;

import br.com.teamregister.model.Player;
import br.com.teamregister.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}
