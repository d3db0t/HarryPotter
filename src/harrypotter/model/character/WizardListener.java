package harrypotter.model.character;

import harrypotter.model.world.Direction;

public interface WizardListener 
{
   public void onGryffindorTrait();
   public void onSlytherinTrait(Direction d);
   public void onHufflepuffTrait();
   public Object onRavenclawTrait();
}
