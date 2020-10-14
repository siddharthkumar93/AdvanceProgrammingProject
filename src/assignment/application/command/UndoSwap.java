package assignment.application.command;

import java.util.ArrayList;

public class UndoSwap implements ApplicationCommand
{

    ArrayList<String> checkBox = new ArrayList<String>();
    ArrayList<String> value = new ArrayList<String>();
    
    public UndoSwap( ArrayList<String> checkBox, String value1, String value2)
    {
       this.checkBox = checkBox;
       this.value.add(value1);
       this.value.add(value2);
    }
    
    @Override
    public void undo()
    {
        
        
    }

    public ArrayList<String> getCheckBox()
    {
        return this.checkBox;
    }
    
    public String getValue(int i)
    {
        return this.value.get(i);
    }

}
