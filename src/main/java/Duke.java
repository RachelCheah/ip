import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        ArrayList<Task> aList = new ArrayList<>();  // Initialize list
        String filename = "\\tasklist.txt";


        System.out.println("Hello! I'm Duke\n" +   // Welcome Message
                "What can I do for you?");

        String input = scanner.nextLine();  // Read user input
        while (!input.equals("bye")){
            switch(input.split(" ")[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= aList.size(); i++){
                    System.out.println(String.valueOf(i) + ". " + aList.get(i - 1));
                    }
                    break;
                case "done":
                    System.out.println("Nice! I've marked this task as done");
                    Task tempTask = aList.get(Integer.parseInt(input.split(" ")[1])-1);
                    aList.set(Integer.parseInt(input.split(" ")[1])-1, tempTask.markDone());
                    System.out.println(aList.get(Integer.parseInt(input.split(" ")[1])-1));
                    Task.saveTaskList(aList,filename);
                    break;
                case "delete":
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + aList.get(Integer.parseInt(input.split(" ")[1])-1));
                    aList.remove(Integer.parseInt(input.split(" ")[1])-1);
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    Task.saveTaskList(aList,filename);
                    break;
                case "todo":
                    try {
                        ToDos.isLegitInput(input);
                    } catch (NotEnoughInfoException ex) {
                        System.err.print(ex);
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    aList.add(new ToDos(ToDos.getNameInput(input), false));
                    System.out.println(" " + aList.get(aList.size() - 1));
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    Task.saveTaskList(aList,filename);
                    break;
                case "deadline":
                    try {
                        Deadlines.isLegitInput(input);
                    } catch (NotEnoughInfoException ex) {
                        System.err.print(ex);
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    aList.add(new Deadlines(Deadlines.getNameInput(input), false, Deadlines.getDeadlineInput(input)));
                    System.out.println(" " + aList.get(aList.size() - 1));
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    Task.saveTaskList(aList,filename);
                    break;
                case "event":
                    try {
                        Events.isLegitInput(input);
                    } catch (NotEnoughInfoException ex) {
                        System.err.print(ex);
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    aList.add(new Events(Events.getNameInput(input), false, Events.getDeadlineInput(input)));
                    System.out.println(" " + aList.get(aList.size() - 1));
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    Task.saveTaskList(aList,filename);
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            input = scanner.nextLine();  // Read user input
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
