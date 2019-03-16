package ui;

import dto.BookDto;
import dao.BookDao;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewManager {
    private JTextField newBookName = new JTextField();
    private JTextField newBookAuthor = new JTextField();
    private JTextField newBookType = new JTextField();
    private JTextField newBookAmountOfPages = new JTextField();
    private JTextField newBookEditionNumber = new JTextField();
    private JTextField newBookAmountOFBooks = new JTextField();

    private JTextField findBookSearchInput = new JTextField(10);

    private Screen screen;
    private Font font = new Font("Arial", Font.BOLD, 18);
    private static JPanel addBookPanel;
    private static JPanel findBookPanel;
    private static JScrollPane pane;


    public ViewManager () {
        if (addBookPanel == null)
            CreateAddBookPanel();

        if (findBookPanel == null){
            CreateFindBookPanel();
        }
    }

    public ViewManager (Screen screen){
        this();
        this.screen = screen;
    }


    public JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        JMenu bookMenu = new JMenu("Книги");

        JMenuItem findBook = new JMenuItem("Пошук");
        JMenuItem addBook = new JMenuItem("Додати");

        findBook.addActionListener((e) ->{
            if (pane != null)
                screen.removeSearchedBooks(pane);

            screen.AddPanel(getFindBookPanel());
        });
        addBook.addActionListener((e -> {
            if (pane != null)
                screen.removeSearchedBooks(pane);

            screen.AddPanel(GetAddBookPanel());
        }));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit application");

        exitMenuItem.addActionListener((event) -> System.exit(0));

        bookMenu.add(addBook);
        bookMenu.add(findBook);
        bookMenu.addSeparator();;
        bookMenu.add(exitMenuItem);

        menubar.add(bookMenu);
        return menubar;
    }

    private void CreateAddBookPanel(){
        addBookPanel = GetDefaultPanel();

        addLabelAndTextField("Назва:",  newBookName, 0, 0, addBookPanel);
        addLabelAndTextField("Автор:",  newBookAuthor, 1, 0,  addBookPanel);
        addLabelAndTextField("Тип:", newBookType, 2, 0, addBookPanel);
        addLabelAndTextField("К-сть сторінок:", newBookAmountOfPages, 3, 0, addBookPanel);
        addLabelAndTextField("№ видання:", newBookEditionNumber,4, 0, addBookPanel);
        addLabelAndTextField("Екземпляри:", newBookAmountOFBooks,4, 3, addBookPanel);

        addButton("Очистити", 6, 0, Color.WHITE, addBookPanel, (e) -> {
            ClearNewBookAddingFields();
        });

        addButton("Відмінити", 9, 0, Color.RED, addBookPanel, (e) -> {addBookPanel.setVisible(false);});
        addButton("Створити", 9, 4, Color.GREEN, addBookPanel, (e) -> {

            String name = newBookName.getText();
            String author = newBookAuthor.getText();
            String type = newBookType.getText();
            int amountOfPages, editionNumber, amountOfBooks;

            try{
                amountOfPages = Integer.parseInt(newBookAmountOfPages.getText());
                editionNumber = Integer.parseInt(newBookEditionNumber.getText());
                amountOfBooks = Integer.parseInt(newBookAmountOFBooks.getText());
            }catch (java.lang.NumberFormatException ex){
                ShowMessage("Введіть коректні дані!");
                return;
            }

            if (name.equals("") || type.equals("") || author.equals("")){
                    ShowMessage("Поля не можуть бути пустими");
                return;
            }

            if (amountOfPages == 0 || editionNumber == 0 || amountOfBooks == 0){
                    ShowMessage("Значення не можуть бути 0");
                return;
            }

            BookDto bookDto = new BookDto(name, author, type, amountOfPages, editionNumber, amountOfBooks);
            if(BookDao.addBook(bookDto)){
                ShowMessage("Book was added successfully");
                addBookPanel.setVisible(false);
                displaySearchResults( BookDao.searchBooks(""));
            }else{
                ShowMessage("Error occurred while adding new book");
                addBookPanel.setVisible(false);
            }


        });
    }

    public JPanel GetAddBookPanel(){
        findBookPanel.setVisible(false);
        ClearNewBookAddingFields();
        addBookPanel.setVisible(true);
        return addBookPanel;
    }

    private void CreateFindBookPanel (){
        findBookPanel = GetDefaultPanel();

        JLabel label = new JLabel("Введіть назву, автора або тип");
        JLabel label2 = new JLabel("(залиште пустим, щоб вибрати всі)");
        findBookSearchInput.setFont(font);
        findBookSearchInput.setForeground(Color.LIGHT_GRAY);
        findBookPanel.add(label, getGridBagConstraints(0,0));
        findBookPanel.add(label2, getGridBagConstraints(1,0));
        findBookPanel.add(findBookSearchInput, getGridBagConstraints(2, 0));

        addButton("Пошук", 1, 4, Color.white, findBookPanel, (e) -> {
            displaySearchResults( BookDao.searchBooks(findBookSearchInput.getText()));

        });
        addButton("Відмінити", 10, 0, Color.RED, findBookPanel, (e) -> {findBookPanel.setVisible(false);});
    }

    public JPanel getFindBookPanel() {
        addBookPanel.setVisible(false);
        findBookPanel.setVisible(true);
        return findBookPanel;
    }

    private void displaySearchResults(ArrayList<ArrayList<String>> bookValues){
        findBookPanel.setVisible(false);

        String[] columns = new String[] {
                "Назва", "Автор", "Тип", "К-сть сторінок", "№ видання", "Екземпляри"
        };

        String[][] data = new String[bookValues.size()][];
        for (int i = 0; i < bookValues.size(); i++) {
            ArrayList<String> row = bookValues.get(i);
            data[i] = row.toArray(new String[row.size()]);
        }

        JTable table = new JTable(data, columns);

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        pane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        screen.displaySearchedBooks(pane);
    }

    private void ShowMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    private void ClearNewBookAddingFields(){
        newBookName.setText("");
        newBookAuthor.setText("");
        newBookType.setText("");
        newBookAmountOfPages.setText("");
        newBookEditionNumber.setText("");
        newBookAmountOFBooks.setText("");
    }

    private JPanel GetDefaultPanel(){
        JPanel panel = new JPanel();
        Border border = panel.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        panel.setBorder(new CompoundBorder(border, margin));

        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
        panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
        panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel.setLayout(panelGridBagLayout);

        return panel;
    }

    private void addLabelAndTextField(String labelText, JTextField textField, int yPos, int xPos,Container containingPanel) {
        JLabel label = new JLabel(labelText);
        containingPanel.add(label, getGridBagConstraints(yPos, xPos));

        containingPanel.add(textField, getGridBagConstraints(yPos, xPos+1));

        textField.setFont(font);
    }

    private void addButton(String buttonText, int yPos, int gridX, Color color,
                           Container containingPanel, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.addActionListener(actionListener);

        button.setBackground(color);
        containingPanel.add(button, getGridBagConstraints(yPos, gridX));
    }

    private GridBagConstraints getGridBagConstraints(int yPos, int gridX){
        GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
        gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
        gridBagConstraintForTextField.insets = new Insets(0, 2, 4, 2);
        gridBagConstraintForTextField.gridx = gridX;
        gridBagConstraintForTextField.gridy = yPos;

        return gridBagConstraintForTextField;
    }

}
