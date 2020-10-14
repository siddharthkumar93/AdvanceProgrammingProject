package assignment.application.command;

class ApplicationCommandManager
{
    private static ApplicationCommandManager instance = null;
    private ApplicationCommandStack<ApplicationCommand> stackNormal;
    private ApplicationCommandStack<ApplicationCommand> stackReverse;

    private ApplicationCommandManager()
    {
        stackNormal = new ApplicationCommandStack<>();
        stackReverse = new ApplicationCommandStack<>();
    }

    static ApplicationCommandManager getInstance()
    {
        if (instance != null) return instance;
        return new ApplicationCommandManager();
    }

    void undo()
    {
        ApplicationCommand command;
        if (stackNormal.size() > 0)
        {
            command = stackNormal.pop();
            command.undo();
            stackReverse.push(command);
        }
    }

    void clearNormal()
    {
        stackNormal.clear();
    }

    void clearReverse()
    {
        stackReverse.clear();
    }

}