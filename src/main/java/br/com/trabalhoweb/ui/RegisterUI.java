package br.com.trabalhoweb.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.entities.User;
import br.com.trabalhoweb.service.LoginServiceImpl;

@Component
public class RegisterUI extends JFrame {
	
	private static final long serialVersionUID = 6739099178671929817L;

	private JLabel jcomp1;
    private JTextField jcomp2;
    private JLabel jcomp3;
    private JButton jcomp4;
    private JLabel jcomp5;
    private JTextField jcomp6;
    private JPasswordField jcomp7;
    private JLabel jcomp8;
    private JTextField jcomp9;
    
    @Autowired
    private LoginUI loginUI;
    
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @PostConstruct
    public void registerUI() {

    	jcomp1 = new JLabel ("Email: ");
        jcomp2 = new JTextField (5);
        jcomp3 = new JLabel ("Senha: ");
        jcomp4 = new JButton ("Cadastrar");
        jcomp5 = new JLabel ("Nome: ");
        jcomp6 = new JTextField (5);
        jcomp7 = new JPasswordField (5);
        jcomp8 = new JLabel ("Telefone: ");
        jcomp9 = new JTextField (5);

        setPreferredSize (new Dimension (387, 290));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);

        jcomp1.setBounds (125, 30, 40, 25);
        jcomp2.setBounds (190, 30, 100, 25);
        jcomp3.setBounds (125, 65, 45, 25);
        jcomp4.setBounds (160, 180, 100, 25);
        jcomp5.setBounds (125, 100, 50, 25);
        jcomp6.setBounds (190, 100, 100, 25);
        jcomp7.setBounds (190, 65, 100, 25);
        jcomp8.setBounds (125, 135, 60, 25);
        jcomp9.setBounds (190, 135, 100, 25);
        
        jcomp4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastraUsuario();	
				setVisible(false);
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
    }

    public void cadastraUsuario() {
    	User user = new User();
    	user.setCellphone(jcomp9.getText());
    	user.setEmail(jcomp2.getText());
    	user.setName(jcomp6.getText());
    	user.setPassword(Arrays.toString(jcomp7.getPassword()));
    	if(loginServiceImpl.findByEmail(user.getEmail()) == null) {
    		JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
    		loginServiceImpl.save(user);
//    		loginUI.buildFrame();
    		loginUI.criaTela();
    		loginUI.requestFocus();
    	} else {
    		JOptionPane.showMessageDialog(null, "Email já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
    		loginUI.criaTela();
    		
//    		loginUI.buildFrame();
    	}
    	
    }
    
//    public void buildFrame() {
//    	JFrame frame = new JFrame ("Registro");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add(registerUI());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible (true);
//    }
    
    public void criaTela() {
    	setVisible(true);
    }
}
