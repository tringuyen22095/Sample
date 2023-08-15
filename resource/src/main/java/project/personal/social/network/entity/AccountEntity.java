package project.personal.social.network.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Table(name = "ACCOUNT")
@Data
@Where(clause = "IS_DELETED == 1")
public class AccountEntity {

	@Id
	@Column(name = "ID")
	private UUID id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccountRoleEntity> accountRoles;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ROOM_ACCOUNT", joinColumns = @JoinColumn(name = "ACCOUNT_ID"), inverseJoinColumns = @JoinColumn(name = "ROOM_ID"))
	private List<RoomEntity> rooms;

}
