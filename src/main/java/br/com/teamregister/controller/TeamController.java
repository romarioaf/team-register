package br.com.teamregister.controller;

import br.com.teamregister.model.Player;
import br.com.teamregister.model.Team;
import br.com.teamregister.repository.TeamRepository;
import br.com.teamregister.service.S3ClientFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private S3ClientFacade s3ClientFacade;

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

    @PostMapping(value = "/teams/image", consumes = "multipart/form-data")
    public S3FileWrapper updateImage(@RequestParam("file") MultipartFile file) throws IOException {

        Path path = Paths.get("/tmp/myfile.txt");
        Files.write(path, file.getBytes());

        return new S3FileWrapper(s3ClientFacade.saveImage(path.toFile()));
    }

    @Getter @Setter
    class S3FileWrapper {
        private String key;

        S3FileWrapper(String key) {
            this.key = key;
        }
    }
}