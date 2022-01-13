package io.github.firetamer.dbb.api.registry;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Registers the {@link io.github.firetamer.dbb.api.player_data.PlayerStatType} that is represented by the field that has this
 * annotation. For the stat type to be registered the class in which the field is has
 * to be annotated with {@link RegistryHolder}
 *
 * @author matyrobbrt
 *
 */
@Documented
@Retention(RUNTIME)
@Target({
		FIELD
})
public @interface RegisterPlayerStatType {

	/**
	 * The registry name of the stat type (the modid is specified by the
	 * {@link RegistryHolder} on the class the field is in)
	 *
	 * @return
	 */
	String value();
}
