package br.com.trabalhoweb.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.entities.Product;
import br.com.trabalhoweb.entities.User;
import br.com.trabalhoweb.repository.ProductRepository;

@Component
public class ListProductsUI extends JFrame {
	private static final long serialVersionUID = 8191235567538858682L;

	private JLabel jcomp1;
    private JComboBox<Product> jcomp2;
    private JButton jcomp3;
    private JLabel jcomp4;
    private User user;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUI productUI;
    
    @Autowired
    private DecisionUI decisionUI;
    
    
    
    @PostConstruct
    public void listProductsUI() {
        jcomp1 = new JLabel ("Produtos à venda: ");
        jcomp2 = new JComboBox<>();
        jcomp3 = new JButton ("Voltar");
        jcomp4 = new JLabel ("Duplo clique em um anúncio para entrar");

        setPreferredSize (new Dimension (528, 370));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);

        jcomp1.setBounds (215, 25, 110, 25);
        jcomp2.setBounds (135, 60, 260, 150);
        jcomp3.setBounds (225, 290, 100, 25);
        jcomp4.setBounds (155, 225, 240, 35);
        
        if(!productRepository.findAll().isEmpty() || productRepository.findAll() != null) {
        	List<Product> findAll = productRepository.findAll();
        	findAll.forEach(product -> {
        		jcomp2.addItem(product);
        	});
        }
        
        jcomp2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Product selectedItem = (Product) jcomp2.getSelectedItem();
				productUI.buildFrameIfViewing(selectedItem);				
			}
		});
        
        jcomp3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				decisionUI.criaTela(user);
			}
		});
        
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
    }

//    public void buildFrames() {
//    	JFrame frame = new JFrame ("Listagem de produtos");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (listProductsUI());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible (true);
//    }
    
    public void criaTela(User user) {
    	setVisible(true);
    }
}
