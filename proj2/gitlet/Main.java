package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.initialize();
                break;
            case "add":
                if(args.length > 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.addFile(args[1]);
                break;
            case "commit":
                Repository.makeCommit(args[1]);
                break;
            default:
                System.out.println("No command with that name exists.");
        }
    }
}
