package br.com.trabalhoweb.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.entities.User;

@Component
public class DecisionUI extends JFrame {
	private static final long serialVersionUID = -5322703420649463637L;

	private JLabel jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    
    @Autowired
    private ProductUI productUI;

    @Autowired
    private ListProductsUI listProductsUI;
    
    private User user;

    @PostConstruct
    public void decisionUI() {

    	jcomp1 = new JLabel ("Você deseja?");
        jcomp2 = new JButton ("Cadastrar Produto");
        jcomp3 = new JButton ("Comprar Produto");

        setPreferredSize (new Dimension (528, 160));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);

        jcomp1.setBounds (230, 25, 100, 25);
        jcomp2.setBounds (90, 70, 145, 25);
        jcomp3.setBounds (300, 70, 140, 25);
        
        jcomp2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				productUI.criaTela(user);
			}
		});
        
        jcomp3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listProductsUI.criaTela(user);
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);

    }

//    public void buildFrames(User user) {
//    	
//    	JFrame frame = new JFrame ("Escolha sua operação");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (decisionUI());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible (true);
//    }
    
    public void criaTela(User user) {
    	this.user = user;
    	setVisible(true);
    }
}
