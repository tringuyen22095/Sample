package project.personal.social.network.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {

	@Id
	@Column(name = "ROLE")
	@JsonProperty("role")
	private String role;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonProperty("permissions")
	private List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty("accounts")
	private List<AccountEntity> accounts = new ArrayList<AccountEntity>();

}
