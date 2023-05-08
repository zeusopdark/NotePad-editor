/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 * @author Deepak
 */
public class Notepad implements ActionListener
{
    JFrame jf, replaceframe, font_frame;
    
    JMenuBar menubar;
    JMenu file, edit, format, help;
    JMenuItem neww, open, save, saveas, exit, page_setup, print;
    JMenuItem cut, copy, paste, replace, date_time;
    JMenuItem open_font_menu, font_color, textarea_color;
    JCheckBoxMenuItem word_wrap;
    
    JTextArea textarea;
    
    String title="Untitled - Notepad";
    
    File filee;
    JFileChooser filechooser;
    
    JTextField jt1, jt2;
    JButton jb1, ok;
    
    JComboBox cb_font_family, cb_font_style, cb_font_size;
    
    public Notepad()
    {
        try
        {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        jf=new JFrame(title);
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deepak\\Documents\\NetBeansProjects\\NotepadEditor\\src\\notepadeditor\\notepad_icon.png");    
        jf.setIconImage(icon);
        
        menubar=new JMenuBar();
        
        file=new JMenu("File");
        
        neww=new JMenuItem("New");
        neww.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        neww.addActionListener(this);
        file.add(neww);
        
        open=new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        file.add(open);
        
        save=new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);
        file.add(save);
        
        saveas=new JMenuItem("Save As");
        saveas.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.SHIFT_DOWN_MASK));
        saveas.addActionListener(this);
        file.add(saveas);
        
        file.addSeparator();
        
        page_setup=new JMenuItem("Page Setup");
        page_setup.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
        page_setup.addActionListener(this);
        file.add(page_setup);
        
        print=new JMenuItem("Print...");
        print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.SHIFT_DOWN_MASK));
        print.addActionListener(this);
        file.add(print);
        
        file.addSeparator();
        
        exit=new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(this);
        file.add(exit);
        
        menubar.add(file);
        
        edit=new JMenu("Edit");
        
        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);
        
        paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);
        
        edit.addSeparator();
        
        replace=new JMenuItem("Replace");
        replace.addActionListener(this);
        edit.add(replace);
        
        edit.addSeparator();
        
        date_time=new JMenuItem("Date/Time");
        date_time.addActionListener(this);
        edit.add(date_time);
        
        menubar.add(edit);
        
        format=new JMenu("Format");
        
        word_wrap=new JCheckBoxMenuItem("Word Wrap");
        word_wrap.addActionListener(this);
        format.add(word_wrap);
        
        format.addSeparator();
        
        open_font_menu=new JMenuItem("Font...");
        open_font_menu.addActionListener(this);
        format.add(open_font_menu);
        
        format.addSeparator();
        
        font_color=new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);
        
        textarea_color=new JMenuItem("Textarea Color");
        textarea_color.addActionListener(this);
        format.add(textarea_color);
        
        menubar.add(format);
        
        help=new JMenu("Help");
        help.addActionListener(this);
        menubar.add(help);
        
        jf.setJMenuBar(menubar);
        
        textarea=new JTextArea();
        JScrollPane scollpane=new JScrollPane(textarea);
        scollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jf.add(scollpane);
        
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        filechooser=new JFileChooser();
        
        if(e.getSource()==neww)
        {
            newNotepad();
        }
        if(e.getSource()==exit)
        {
            System.exit(0);
        }
        if(e.getSource()==save)
        {
            save();
        }
        if(e.getSource()==open)
        {
            open();
        }
        if(e.getSource()==saveas)
        {
            saveAs();
        }
        if(e.getSource()==page_setup)
        {
            pageSetup();
        }
        if(e.getSource()==print)
        {
            printPage();
        }
        if(e.getSource()==cut)
        {
            textarea.cut();
        }
        if(e.getSource()==copy)
        {
            textarea.copy();
        }
        if(e.getSource()==paste)
        {
            textarea.paste();
        }
        if(e.getSource()==replace)
        {
            replaceFrame();
        }
        if(e.getSource()==jb1)
        {
            replace();
        }
        if(e.getSource()==date_time)
        {
            setDateTime();
        }
        if(e.getSource()==font_color)
        {
            setFontColor();
        }
        if(e.getSource()==textarea_color)
        {
            setTextareaColor();
        }
        if(e.getSource()==open_font_menu)
        {
            openFontFrame();
        }
        if(e.getSource()==ok)
        {
            setFontToNotepad();
        }
        if(e.getSource()==word_wrap)
        {
            boolean b=word_wrap.getState();
            textarea.setLineWrap(b);
        }
    }
    
    public void newNotepad()
    {
        String text=textarea.getText();
        if(!text.equals(""))
        {
            int i=JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
            if(i==0)
            {
                saveAs();
                if(!jf.getTitle().equals(title))
                {
                    setTitle(title);
                    textarea.setText("");
                }
            }
            else if(i==1)
            {
                textarea.setText("");
            }
        }
    }
    public void save()
    {
        if(jf.getTitle().equals(title))
        {
            saveAs();
        }
        else
        {
            try
            {
                String text=textarea.getText();
                byte[] b=text.getBytes();
                FileOutputStream fos=new FileOutputStream(filee);
                fos.write(b);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    public void saveAs()
    {
        try
        {
            int i=filechooser.showSaveDialog(jf);
            
            if(i==0)
            {
                filee=filechooser.getSelectedFile();
            
                String text=textarea.getText();
                byte[] b=text.getBytes();
                FileOutputStream fos=new FileOutputStream(filee);
                fos.write(b);
                
                setTitle(filee.getName());
                
                fos.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void setTitle(String title)
    {
        jf.setTitle(title);
    }
    
    void open()
    {
        try
        {
            int i=filechooser.showOpenDialog(jf);
            if(i==0)
            {
                textarea.setText("");
                
                FileInputStream fis=new FileInputStream(filechooser.getSelectedFile());
                int ii;
                while((ii=fis.read()) != -1)
                {
                    textarea.append(String.valueOf((char)ii));
                }
                fis.close();
                
                setTitle(filechooser.getSelectedFile().getName());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void pageSetup()
    {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
        //page format code
    }
    public void printPage()
    {
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog())
        {
            try 
            {
                pj.print();
            }
            catch (PrinterException exc) 
            {
                System.out.println(exc);
            }
        }  
    }
    
    public void replaceFrame()
    {
        replaceframe=new JFrame("Replace");
        replaceframe.setSize(500,300);
        replaceframe.setLayout(null);
        
        JLabel jl1=new JLabel("Find What : ");
        jl1.setBounds(50,50,80,40);
        replaceframe.add(jl1);
        
        jt1=new JTextField();
        jt1.setBounds(170, 50, 200, 40);
        replaceframe.add(jt1);
        
        JLabel jl2=new JLabel("Replace With : ");
        jl2.setBounds(50,100,100,40);
        replaceframe.add(jl2);
        
        jt2=new JTextField();
        jt2.setBounds(170, 100, 200, 40);
        replaceframe.add(jt2);
        
        jb1=new JButton("Replace");
        jb1.addActionListener(this);
        jb1.setBounds(200, 150, 100, 40);
        replaceframe.add(jb1);
        
        replaceframe.setVisible(true);
    }
    
    public void replace()
    {
        String find_what=jt1.getText();
        String replace_with=jt2.getText();
        String text=textarea.getText();
        String new_text=text.replace(find_what, replace_with);
        textarea.setText(new_text);
        
        replaceframe.setVisible(false);
    }
    
    public void setDateTime()
    {
        LocalDateTime ldt=LocalDateTime.now();
        String datetime=ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        textarea.append(datetime);
    }
    
    public void setFontColor()
    {
        Color c=JColorChooser.showDialog(jf, "Select Font Color", Color.black);
        textarea.setForeground(c);
    }
    
    public void setTextareaColor()
    {
        Color c=JColorChooser.showDialog(jf, "Select Textarea Color", Color.white);
        textarea.setBackground(c);
    }
    
    public void openFontFrame()
    {
        font_frame=new JFrame("Fonts...");
        font_frame.setSize(650,300);
        font_frame.setLayout(null);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();
        cb_font_family=new JComboBox(fontFamilies);
        cb_font_family.setBounds(50, 50, 200, 40);
        font_frame.add(cb_font_family);
        
        String[] font_style={"Plain", "Bold", "Italic"};
        cb_font_style=new JComboBox(font_style);
        cb_font_style.setBounds(300, 50, 100, 40);
        font_frame.add(cb_font_style);
        
        Integer[] font_size={10,16,20,25,30,35,40,50,60};
        cb_font_size=new JComboBox(font_size);
        cb_font_size.setBounds(450, 50, 80, 40);
        font_frame.add(cb_font_size);
        
        ok=new JButton("OK");
        ok.setBounds(250, 150, 80, 50);
        ok.addActionListener(this);
        font_frame.add(ok);
        
        font_frame.setVisible(true);
    }
    
    public void setFontToNotepad()
    {
        String font_family=(String) cb_font_family.getSelectedItem();
        String font_style=(String) cb_font_style.getSelectedItem();
        Integer font_size=(Integer) cb_font_size.getSelectedItem();
        
        int font_stylee=0;
        if(font_style.equals("Plain"))
        {
            font_stylee=Font.PLAIN;
        }
        else if(font_style.equals("Bold"))
        {
            font_stylee=Font.BOLD;
        }
        else if(font_style.equals("Italic"))
        {
            font_stylee=Font.ITALIC;
        }
        
        Font f=new Font(font_family, font_stylee, font_size);
        textarea.setFont(f);
        
        font_frame.setVisible(false);
    }
}
