package harrypotter.model.character;

import java.io.IOException;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.OutOfBordersException;

public interface Champion {
	
	public void useTrait() throws IOException, InCooldownException, OutOfBordersException ;

}
