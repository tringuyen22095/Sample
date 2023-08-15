package project.personal.social.network.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ACCOUNT")
@Data
public class AccountEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccountRoleEntity> accountRoles;

}
