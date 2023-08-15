package project.personal.social.network.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PERMISSION")
@Data
public class PermissionEntity {

	@Id
	@Column(name = "ID")
	private UUID id;

	@Column(name = "PERMISSION")
	private String permission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE")
	private RoleEntity role;

}
