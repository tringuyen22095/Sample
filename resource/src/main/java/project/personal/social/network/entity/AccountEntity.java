package project.personal.social.network.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT")
@Where(clause = "IS_DELETED = 0")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

	@Id
	@Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
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
