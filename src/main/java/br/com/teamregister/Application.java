package br.com.teamregister;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.teamregister.model.Player;
import br.com.teamregister.model.Team;

@SpringBootApplication
public class Application {

	@Autowired
	private TeamRepository teamRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		Team team = new Team();
		team.setName("Belmare");
		team.setLocation("Ceará");
		team.setPlayers(new ArrayList<>());
		
		Player player1 = new Player();
		player1.setName("Dida");
		player1.setPosition("Goalkeep");
		
		Player player2 = new Player();
		player2.setName("Ronaldo");
		player2.setPosition("Forward");
		
		team.getPlayers().add(player1);
		team.getPlayers().add(player2);
		
		teamRepository.save(team);
	}
}
