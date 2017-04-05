package harrypotter.model.character;

import java.io.IOException;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.world.Direction;

public interface WizardListener 
{
   public void onGryffindorTrait() throws InCooldownException;
   public void onSlytherinTrait(Direction d) throws IOException, InCooldownException, OutOfBordersException;
   public void onHufflepuffTrait() throws InCooldownException;
   public Object onRavenclawTrait() throws InCooldownException;
}
