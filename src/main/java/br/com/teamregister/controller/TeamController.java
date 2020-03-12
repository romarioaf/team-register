package br.com.teamregister.controller;

import br.com.teamregister.model.Player;
import br.com.teamregister.model.Team;
import br.com.teamregister.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    @GetMapping("/teams/{id}")
    public Team getByTeam(@PathVariable Long id) {
        Optional<Team> team = teamRepository.findById(id);

        if(team.isPresent())
            return team.get();

        return null;
    }

    @GetMapping("/teams/{id}/players")
    public List<Player> getPlayers(@PathVariable Long id) {
        Optional<Team> team = teamRepository.findById(id);

        if(team.isPresent())
            return team.get().getPlayers();

        return null;
    }

    @PostMapping("/teams")
    public Team save(@RequestBody Team team) {
       return teamRepository.save(team);
    }

    @PutMapping("/teams/{id}")
    public Team update(@RequestBody Team team, @PathVariable Long id) {
        team.setId(id);
        return teamRepository.save(team);
    }
}