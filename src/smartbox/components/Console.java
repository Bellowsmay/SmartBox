package smartbox.components;

import java.io.*;
import smartbox.*;
import smartbox.components.Component;

public class Console extends Component implements App {

    transient protected BufferedReader stdin;
    transient protected PrintWriter stdout;
    transient PrintWriter stderr;

    public CommandProcessor processor;

    public Console() {
        stdout = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.out)), true);
        stderr = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.err)), true);
        stdin = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public void repl() {
        while(true) {
            try {
                stdout.print("-> ");
                stdout.flush();
                String request = stdin.readLine();
                if (request == null) continue;
                if (request.equals("quit")) break;
                String response = processor.execute(request);
                stdout.println("result: " + response);
            } catch(Exception e) {
                stderr.println(e.getMessage());
            }
        }
        stdout.println("bye");
    }

    public void initComponent() {
        super.initComponent();
        stdout = new PrintWriter(
                new BufferedWriter(
                        stderr = new PrintWriter(
                                new BufferedWriter(
                                        new OutputStreamWriter(System.err)), true)));
        stdin = new BufferedReader(
                new InputStreamReader(System.in));
    }


    public void main() throws Exception {
        repl();
    }
}