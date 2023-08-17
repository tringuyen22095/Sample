package project.personal.social.network.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERMISSION")
@Getter
@Setter
@NoArgsConstructor
public class PermissionEntity {

	@Id
	@Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@JsonProperty("id")
	private UUID id;

	@Column(name = "PERMISSION")
	@JsonProperty("permission")
	private String permission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE")
	@JsonProperty("role")
	private RoleEntity role;

}
