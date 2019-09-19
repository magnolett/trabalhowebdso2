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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.service.LoginServiceImpl;

@Component
public class LoginUI extends JFrame {
	private static final long serialVersionUID = -2724673603903644962L;

	private JLabel jcomp1;
    private JTextField jcomp2;
    private JLabel jcomp3;
    private JPasswordField jcomp4;
    private JButton jcomp5;
    private JLabel jcomp6;
    private JButton jcomp7;

    @Autowired
    private LoginServiceImpl loginServiceImpl;
    
    @Autowired
    private DecisionUI decisionUI;
    
    @Autowired
    private RegisterUI registerUI;
    
    @PostConstruct
    public void createLoginUI() {

    	jcomp1 = new JLabel ("Email: ");
        jcomp2 = new JTextField (5);
        jcomp3 = new JLabel ("Senha: ");
        jcomp4 = new JPasswordField (5);
        jcomp5 = new JButton ("Login");
        jcomp6 = new JLabel ("Não possui login?");
        jcomp7 = new JButton ("Cadastrar-se");

        setPreferredSize (new Dimension (387, 300));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);

        jcomp1.setBounds (125, 60, 40, 25);
        jcomp2.setBounds (185, 60, 100, 25);
        jcomp3.setBounds (125, 90, 45, 25);
        jcomp4.setBounds (185, 90, 100, 25);
        jcomp5.setBounds (165, 130, 100, 25);
        jcomp6.setBounds (165, 170, 110, 25);
        jcomp7.setBounds (160, 210, 110, 25);
        
        jcomp5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fazLogin();
			}
		});
        
        jcomp7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				procedeCadastro();
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
    }
    
    public void fazLogin() {
    	String email = jcomp2.getText();
    	String password = Arrays.toString(jcomp4.getPassword());
    	
    	if(loginServiceImpl.findByEmailAndPassword(email, password) != null) {
    		decisionUI.criaTela(loginServiceImpl.findByEmailAndPassword(email, password));
    		this.setVisible(false);
    	} else {
    		JOptionPane.showMessageDialog(null, "Credenciais inválidas! Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    public void procedeCadastro() {
    	registerUI.criaTela();
    }

//    public void buildFrame() {
//        JFrame frame = new JFrame ("Início");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add(createLoginUI());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible (true);
//    }
    
    public void criaTela() {
    	setVisible(true);
    }
}
