package assignment.application.command;

public class UndoAdd implements ApplicationCommand
{
    String checkbox;
    String value;
 
    public UndoAdd(String value, String checkbox)
    {
        this.checkbox = checkbox;
        this.value = value;
    }
   

    public String getCheckBox() {
        return this.checkbox;
    }
    
    public String getValue() {
        return this.value;
    }
    
    @Override
    public void undo()
    {
        
    }

}
