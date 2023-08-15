package project.personal.social.network.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ACCOUNT_ROLE")
@Data
public class AccountRoleEntity {

	@Id
	@Column(name = "ID")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private AccountEntity account;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private RoleEntity role;

}
