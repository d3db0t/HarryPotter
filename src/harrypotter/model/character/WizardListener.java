package harrypotter.model.character;

import java.io.IOException;

import harrypotter.model.world.Direction;

public interface WizardListener 
{
   public void onGryffindorTrait();
   public void onSlytherinTrait(Direction d) throws IOException;
   public void onHufflepuffTrait();
   public Object onRavenclawTrait();
}
