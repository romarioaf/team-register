package br.com.teamregister.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Getter @Setter
public class Team {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String location;
	private String emblemUrl;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="teamId")
	private List<Player> players;

}
