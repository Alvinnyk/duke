package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

import java.util.ArrayList;

public class FindCommand implements Command{
    private String arguments;

    public FindCommand(String arguments){
        this.arguments = arguments;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        if(arguments == null){
            ui.printData("OOPS!!! The description of a find cannot be empty.");
        } else {
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            int i;
            for(i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getDescription().contains(arguments)){
                    indexes.add(i);
                }
            }
            String content = "";
            for(i = 0; i < indexes.size(); i++) {
                content = content.concat((i + 1) + ". ");
                content = content.concat("[" + tasks.get(indexes.get(i)).getSymbol() + "]");
                content = content.concat("[" + tasks.get(indexes.get(i)).getIsDoneSymbol() + "]");
                content = content.concat(" " + tasks.get(indexes.get(i)).getDescription());
                if(tasks.get(indexes.get(i)).getSymbol() == 'D'){
                    content = content.concat(" (by: " + tasks.get(indexes.get(i)).getDetails() + ")");
                } else if(tasks.get(indexes.get(i)).getSymbol() == 'E'){
                    content = content.concat(" (at: " + tasks.get(indexes.get(i)).getDetails() + ")");
                }
                content = content.concat("\n");
            }
            ui.printData(content);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
