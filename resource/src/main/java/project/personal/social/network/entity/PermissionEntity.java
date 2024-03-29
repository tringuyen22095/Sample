package project.personal.social.network.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
