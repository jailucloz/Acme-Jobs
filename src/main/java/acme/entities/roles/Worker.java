
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Worker extends UserRole {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@NotNull
	@Min(0)
	@Max(10)
	private Integer				qualifications;

	@NotBlank
	private String				skills;

}
