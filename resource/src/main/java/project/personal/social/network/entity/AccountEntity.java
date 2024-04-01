package project.personal.social.network.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT")
@SQLRestriction("IS_DELETED = 'false'")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.UUID)
	@JsonProperty("id")
	private UUID id;

	@Column(name = "USERNAME")
	@JsonProperty("username")
	private String username;

	@Column(name = "PASSWORD")
	@JsonProperty("password")
	private String password;

	@Column(name = "IS_DELETED")
	@JsonProperty("isDeleted")
	private boolean isDeleted = false;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ACCOUNT_ROLE", joinColumns = @JoinColumn(name = "ACCOUNT_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE"))
	@JsonProperty("roles")
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	@ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty("rooms")
	private List<RoomEntity> rooms = new ArrayList<RoomEntity>();

}
