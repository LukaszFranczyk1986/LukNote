package com.company;

        import java.awt.Menu;
        import java.awt.MenuBar;
        import java.awt.MenuItem;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.BufferedWriter;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.util.Scanner;

        import javax.swing.JFileChooser;
        import javax.swing.JFrame;
        import javax.swing.JTextArea;

public class LukNote extends JFrame{
    int fileToOpen;
    int fileToSave;
    JFileChooser fileOpen;
    JFileChooser fileSave;
    LukNote(){
        MenuBar menuBar = new MenuBar();
        MenuItem menutem = new MenuItem();
        final JTextArea textArea = new JTextArea();
        setMenuBar(menuBar);
        Menu file = new Menu("Plik");
        menuBar.add(file);
        MenuItem newOption = new MenuItem("Nowy");
        MenuItem open = new MenuItem("Otwórz");
        MenuItem save = new MenuItem("Zapisz");
        MenuItem close = new MenuItem("Wyjście");
        file.add(newOption);
        file.add(open);
        file.add(save);
        file.add(close);
        getContentPane().add(textArea);

        newOption.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
            }
        });

        open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                openFile();
                if (fileToOpen == JFileChooser.APPROVE_OPTION){
                    textArea.setText("");
                    try{
                        Scanner scan = new Scanner(new FileReader(fileOpen.getSelectedFile().getPath()));
                        while (scan.hasNext())
                            textArea.append(scan.nextLine());
                    } catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                saveFile();
                if (fileToSave == JFileChooser.APPROVE_OPTION){
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(fileSave.getSelectedFile().getPath()));
                        out.write(textArea.getText());
                        out.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }

    public void openFile(){
        JFileChooser open = new JFileChooser();
        int option = open.showOpenDialog(this);
        fileToOpen = option;
        fileOpen = open;
    }

    public void saveFile(){
        JFileChooser save = new JFileChooser();
        int option = save.showOpenDialog(this);
        fileToSave = option;
        fileSave = save;
    }
}