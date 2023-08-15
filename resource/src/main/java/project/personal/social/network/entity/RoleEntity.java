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
@Table(name = "ROLE")
@Data
public class RoleEntity {

	@Id
	@Column(name = "ROLE")
	private String role;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PermissionEntity> permissions;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<AccountRoleEntity> accountRoles;

}
